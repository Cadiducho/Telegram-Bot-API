/*
 * The MIT License
 *
 * Copyright 2019 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */
package com.cadiducho.telegrambotapi.handlers;

import com.cadiducho.telegrambotapi.BotAPI;
import com.cadiducho.telegrambotapi.Update;
import com.cadiducho.telegrambotapi.exception.TelegramException;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;
import lombok.RequiredArgsConstructor;

@Log
@RequiredArgsConstructor
public class DefaultBotUpdatesPoller implements BotUpdatesPoller {

    private final BotAPI bot;
    @Setter private LongPollingHandler handler;
    @Setter private UpdatesSupplier updatesSupplier;
    @Setter private ExceptionHandler exceptionHandler;
    private ReaderThread readerThread;
    private HandlerThread handlerThread;
    private int lastReceivedUpdate = 0;
    private volatile boolean running = false;

    private final ConcurrentLinkedDeque<Update> receivedUpdates = new ConcurrentLinkedDeque<>();
    
    @Override
    public synchronized void start() {
        if (running) {
            throw new IllegalStateException("Session already running");
        }

        running = true;

        lastReceivedUpdate = 0;

        readerThread = new ReaderThread(updatesSupplier, this);
        readerThread.setName("Bot Telegram Connection");
        readerThread.start();

        handlerThread = new HandlerThread();
        handlerThread.setName("Bot Telegram Updates Consumer");
        handlerThread.start();
    }

    @Override
    public synchronized void stop() {
        if (!running) {
            throw new IllegalStateException("Session already stopped");
        }

        running = false;

        if (readerThread != null) {
            readerThread.interrupt();
        }

        if (handlerThread != null) {
            handlerThread.interrupt();
        }
    }


    @Override
    public synchronized boolean running() {
        return running;
    }

    private class ReaderThread extends Thread {

        private ExponentialBackOff exponentialBackOff;
        private final UpdatesSupplier updatesSupplier;
        private final Object lock;

        public ReaderThread(UpdatesSupplier updatesSupplier, Object lock) {
            this.updatesSupplier = Optional.ofNullable(updatesSupplier).orElse(this::getUpdates);
            this.lock = lock;
        }

        @Override
        public synchronized void start() {
            if (exponentialBackOff == null) {
                exponentialBackOff = new ExponentialBackOff();
            }
            super.start();
        }

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (running) {
                synchronized (lock) {
                    if (running) {
                        try {
                            List<Update> updates = updatesSupplier.getUpdates();
                            if (updates.isEmpty()) {
                                lock.wait(500);
                            } else {
                                updates.removeIf(x -> x.getUpdateId() < lastReceivedUpdate);
                                lastReceivedUpdate = updates.parallelStream()
                                        .map(Update::getUpdateId)
                                        .max(Integer::compareTo)
                                        .orElse(0);
                                receivedUpdates.addAll(updates);

                                synchronized (receivedUpdates) {
                                    receivedUpdates.notifyAll();
                                }
                            }
                        } catch (InterruptedException e) {
                            if (!running) {
                                receivedUpdates.clear();
                            }
                            //log.severe(e.getMessage());
                            interrupt();
                        } catch (Exception global) {
                            //log.severe(global.getMessage());
                            try {
                                synchronized (lock) {
                                    lock.wait(exponentialBackOff.nextBackOffMillis());
                                }
                            } catch (InterruptedException e) {
                                if (!running) {
                                    receivedUpdates.clear();
                                }
                                //log.severe(e.getMessage());
                                interrupt();
                            }
                        }
                    }
                }
            }
            log.info("Reader thread has being closed");
        }

        private List<Update> getUpdates() {
            try {
                List<Update> updates = bot.getUpdates(lastReceivedUpdate + 1, 0, 3, null);
                exponentialBackOff.reset();
                return updates;
            } catch (TelegramException ex) {
                Optional.ofNullable(exceptionHandler).ifPresent(exHandler -> exHandler.handle(ex));
                return new ArrayList<>();
            }
        }
    }

    private List<Update> getUpdateList() {
        List<Update> updates = new ArrayList<>();
        for (Iterator<Update> it = receivedUpdates.iterator(); it.hasNext();) {
            updates.add(it.next());
            it.remove();
        }
        return updates;
    }

    private class HandlerThread extends Thread {

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (running) {
                try {
                    List<Update> updates = getUpdateList();
                    if (updates.isEmpty()) {
                        synchronized (receivedUpdates) {
                            receivedUpdates.wait();
                            updates = getUpdateList();
                            if (updates.isEmpty()) {
                                continue;
                            }
                        }
                    }
                    updates.forEach(handler::handleUpdate);
                } catch (InterruptedException e) {
                    log.severe("Procesamiendo de una update interrumpida: ");
                    log.severe(Arrays.toString(e.getStackTrace()));
                    interrupt();
                } catch (Exception e) {
                    log.severe("Error procesando una update: ");
                    log.severe(Arrays.toString(e.getStackTrace()));
                }
            }
            log.info("Handler thread has being closed");
        }
    }
}