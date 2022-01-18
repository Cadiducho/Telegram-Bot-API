/*
 * The MIT License
 *
 * Copyright 2021 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.botcommandscope;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents the scope to which bot commands are applied. Currently, the following 7 scopes are supported:
 */
@Getter @Setter
@ToString
public abstract class BotCommandScope {

    /**
     * Score type
     */
    private String type;
}
