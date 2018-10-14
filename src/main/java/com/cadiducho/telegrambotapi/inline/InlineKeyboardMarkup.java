/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

import java.util.List;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents an inline keyboard that appears right next to the message it belongs to.
 */
@ToString
@Getter @Setter
public class InlineKeyboardMarkup {

    /**
     * Array of button rows, each represented by an Array of {@link InlineKeyboardButton} objects
     */
    @Json(name = "inline_keyboard") private List<List<InlineKeyboardButton>> inlineKeyboard;
}
