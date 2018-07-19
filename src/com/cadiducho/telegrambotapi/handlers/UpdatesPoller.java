/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */
package com.cadiducho.telegrambotapi.handlers;

import com.cadiducho.telegrambotapi.TelegramBot;
import com.cadiducho.telegrambotapi.Update;
import com.cadiducho.telegrambotapi.exception.TelegramException;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UpdatesPoller {

    private final TelegramBot bot;
    private final ExecutorService executorService;
    private LongPollingHandler handler;

    /**
     * Owner id to send the exception through Telegram
     */
    @Setter private Long ownerId;
    
    public UpdatesPoller(TelegramBot instance) {
        executorService = Executors.newCachedThreadPool();
        ReaderThread readerThread = new ReaderThread();
        readerThread.start();
        bot = instance;
    }
    
    private int lastUpdateId = 0;
    
    public void setHandler(LongPollingHandler handler) {
        this.handler = handler;
    }

    private class ReaderThread extends Thread {

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (bot.getUpdatesPolling()) {
                try {
                    poll();
                } catch (TelegramException e) {
                    if (e.getMessage().toLowerCase().contains("timed out") || e.getMessage().toLowerCase().contains("bad gateway")) { //network problems with Telegram
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException ignored) {
                        }
                    }
                    System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + " An exception occurred while polling Telegram.");
                    e.printStackTrace();
                    if (ownerId != null) {
                        try {
                            bot.sendMessage(ownerId, e.toString());
                        } catch (TelegramException ignored) {
                        }
                    }
                }
            }
        }

        private void poll() throws TelegramException {
            List<Update> updates = bot.getUpdates(lastUpdateId + 1, 0, 3, null);
            if (updates.size() > 0) {
                updates.forEach(update -> {
                    if (update.getUpdate_id() > lastUpdateId) {
                        lastUpdateId = update.getUpdate_id();
                        executorService.submit(() -> shortUpdates(update));
                    }
                });
            }    
        }
        
        private void shortUpdates(Update update) {
            handler.handleUpdate(update);
        }
    }
}