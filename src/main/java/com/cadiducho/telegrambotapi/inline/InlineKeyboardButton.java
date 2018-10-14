/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents one button of an inline keyboard. You must use exactly one of the optional fields.
 */
@ToString
@Getter @Setter
public class InlineKeyboardButton {

    /**
     * Label text on the button
     */
    private String text;
    
    /**
     * Optional. HTTP url to be opened when button is pressed
     */
    private String url;
    
    /**
     * 	Optional. Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes
     */
    @Json(name = "callback_data") private String callbackData;
    
    /**
     * Optional. If set, pressing the button will prompt the user to select one of their chats, 
     * open that chat and insert the bot‘s username and the specified inline query in the input field. 
     * Can be empty, in which case just the bot’s username will be inserted.
     * 
     * Note: This offers an easy way for users to start using your bot in inline mode when they are currently in a private chat with it. 
     * Especially useful when combined with switch_pm… actions – in this case the user will be automatically returned to the chat they switched from, 
     * skipping the chat selection screen.
     */
    @Json(name = "switch_inline_query") private String switchInlineQuery;
    
    /**
     * Optional. If set, pressing the button will insert the bot‘s username and the specified inline query in the current chat's input field. 
     * Can be empty, in which case only the bot’s username will be inserted.
     */
    @Json(name = "switch_inline_query_current_chat") private String switchInlineQueryCurrentChat;
    
    /**
     * Optional. Specify True, to send a Pay button.
     * NOTE: This type of button must always be the first button in the first row.
     */
    private Boolean pay;
}
