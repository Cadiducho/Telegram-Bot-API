/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
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
     * Called when an update is recieved by the bot
     * @param update The update
     */
    void handleUpdate(Update update);
}
