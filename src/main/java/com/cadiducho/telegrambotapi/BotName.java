/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents the bot's name.
 */
@ToString
@Getter @Setter
public class BotName {

    /**
     * The bot's name
     */
    private String name;
}
