/*
 * The MIT License
 *
 * Copyright 2017 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */
package com.cadiducho.telegrambotapi.handlers;

import com.cadiducho.telegrambotapi.TelegramBot;
import com.cadiducho.telegrambotapi.Message;
import com.cadiducho.telegrambotapi.Update;
import com.cadiducho.telegrambotapi.exception.TelegramException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdatesPoller {

    private final ReaderThread readerThread;
    private final TelegramBot bot;
    private final ExecutorService executorService;
    private LongPollingHandler handler;
    
    public UpdatesPoller(TelegramBot instance) {
        executorService = Executors.newCachedThreadPool();     
        readerThread = new ReaderThread();
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
                    System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + " An exception occurred while polling Telegram.");
                    e.printStackTrace();
                    try {
                        bot.sendMessage(8609873, "He petado loco");
                        bot.sendMessage(8609873, e.getMessage());
                    } catch (TelegramException ex) {
                        Logger.getLogger(UpdatesPoller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        private void poll() throws TelegramException {
            List<Update> updates = bot.getUpdates(lastUpdateId + 1, 0, 3, null);
            if (updates.size() > 0) {
                updates.stream().forEach((update) -> {
                    if (update.getUpdate_id() > lastUpdateId) {
                        lastUpdateId = update.getUpdate_id();
                        executorService.submit(() -> {
                            shortUpdates(update);
                        });
                    }
                });
            }    
        }
        
        private void shortUpdates(Update update) {
            if (update.getMessage().getType().equals(Message.Type.TEXT) && update.getMessage().getText().startsWith("/")) {
                handler.handleCommand(update); //It's a command
                //plugin.getServer().getPluginManager().callEvent(new TelegramCommandEvent(update)); 
                return;
            }
            
            handler.handleUpdate(update); //default response event
            //plugin.getServer().getPluginManager().callEvent(new TelegramUpdateEvent(update)); 
        }
    }
}