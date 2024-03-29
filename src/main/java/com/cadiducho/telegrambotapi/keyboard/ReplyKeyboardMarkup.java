/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.keyboard;

import java.util.List;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a custom keyboard with reply options (see Introduction to bots for details and examples).
 */
@ToString
@Getter @Setter
public class ReplyKeyboardMarkup {
    
    /**
     * Array of button rows, each represented by an Array of Strings
     */
    private List<List<KeyboardButton>> keyboard;

    /**
     * Optional. Requests clients to always show the keyboard when the regular keyboard is hidden.
     * Defaults to false, in which case the custom keyboard can be hidden and opened with a keyboard icon.
     */
    private Boolean is_persistent;

    /**
     * Optional. Requests clients to resize the keyboard vertically for optimal fit (e.g., make the keyboard smaller if there are just two rows of buttons). 
     * Defaults to false, in which case the custom keyboard is always of the same height as the app's standard keyboard.
     */
    @Json(name = "resize_keyboard") private Boolean resizeKeyboard;
    
    /**
     * Optional. Requests clients to hide the keyboard as soon as it's been used. Defaults to false.
     */
    @Json(name = "one_time_keyboard") private Boolean oneTimeKeyboard;
    
    /**
     * Optional. Use this parameter if you want to show the keyboard to specific users only. Targets: 1) users that are @mentioned 
     * in the text of the Message object; 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
     * 
     * Example: A user requests to change the bot‘s language, bot replies to the request with a keyboard to select the new language. 
     * Other users in the group don’t see the keyboard.
     */
    private Boolean selective;

}
