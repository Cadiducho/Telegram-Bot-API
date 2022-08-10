/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object Describes data sent from a <a href="https://core.telegram.org/bots/webapps">Web App</a> to the bot.
 */
@ToString
@Getter @Setter
public class WebAppData {

    /**
     * The data. Be aware that a bad client can send arbitrary data in this field.
     */
    private String data;

    /**
     * Text of the web_app keyboard button from which the Web App was opened. Be aware that a bad client can send arbitrary data in this field.
     */
    @Json(name = "button_text")  private String buttonText;
}
