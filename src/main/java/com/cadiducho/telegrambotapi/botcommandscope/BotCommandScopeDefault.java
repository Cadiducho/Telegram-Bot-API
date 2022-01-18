/*
 * The MIT License
 *
 * Copyright 2021 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.botcommandscope;

/**
 * Represents the default scope of bot commands. Default commands are used if no commands with a narrower scope are specified for the user.
 */
public class BotCommandScopeDefault extends BotCommandScope {

    public BotCommandScopeDefault() {
        setType("default");
    }
}
