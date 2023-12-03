/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents the bot's short description.
 */
@ToString
@Getter @Setter
public class BotShortDescription {

    /**
     * 	The bot's short description
     */
    @Json(name = "short_description") private String shortDescription;
}
