/*
 * The MIT License
 *
 * Copyright 2017 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */
package com.cadiducho.telegrambotapi.handlers;

import com.cadiducho.telegrambotapi.Update;

/**
 * Interface ready to be overrided by other Telegram Bot API implementations
 * @author Cadiducho
 */
public interface LongPollingHandler {
    
    /**
     * Called when a general update is recieved by the bot
     * @param update 
     */
    public void handleUpdate(Update update);
    
    /**
     * Called when the bot recieve a command
     * @param update 
     */
    public void handleCommand(Update update);
}
