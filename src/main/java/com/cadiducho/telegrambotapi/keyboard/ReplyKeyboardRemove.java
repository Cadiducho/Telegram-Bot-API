/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.keyboard;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Upon receiving a message with this object, Telegram clients will hide the current custom keyboard and display the default letter-keyboard. 
 * By default, custom keyboards are displayed until a new keyboard is sent by a bot. 
 * An exception is made for one-time keyboards that are hidden immediately after the user presses a button (see ReplyKeyboardMarkup).
 */
@ToString
@Getter @Setter
public class ReplyKeyboardRemove {
    
    /**
     * Requests clients to remove the custom keyboard (user will not be able to summon this keyboard; 
     * if you want to hide the keyboard from sight but keep it accessible, use one_time_keyboard in {@link ReplyKeyboardMarkup})
     */
    @Json(name = "remove_keyboard") private Boolean removeKeyboard;

    /**
     * Optional. Use this parameter if you want to hide keyboard for specific users only. Targets: 1) users that are @mentioned
     * in the text of the Message object; 2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
     * 
     * Example: A user votes in a poll, bot returns confirmation message in reply to the vote and hides keyboard for that user, 
     * while still showing the keyboard with poll options to users who haven't voted yet.
     */
    private Boolean selective;
}
