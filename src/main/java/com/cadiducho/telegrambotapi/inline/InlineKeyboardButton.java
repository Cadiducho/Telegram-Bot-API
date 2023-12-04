/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

import com.cadiducho.telegrambotapi.LoginUrl;
import com.cadiducho.telegrambotapi.WebAppInfo;
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
     * Optional. An HTTP URL used to automatically authorize the user. Can be used as a replacement for the Telegram Login Widget.
     */
    @Json(name = "login_url") private LoginUrl loginUrl;
    
    /**
     * 	Optional. Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes
     */
    @Json(name = "callback_data") private String callbackData;

    /**
     * Optional. If specified, the described Web App will be launched when the button is pressed.
     * The Web App will be able to send a “web_app_data” service message. Available in private chats only.
     */
    @Json(name = "web_app") private WebAppInfo webApp;
    
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
     * 	Optional. If set, pressing the button will prompt the user to select one of their chats of the specified type, open that chat and insert the bot's username and the specified inline query in the input field
     */
    @Json(name = "switch_inline_query_chosen_chat") private String switchInlineQueryChosenChat;

    /**
     * Optional. Specify True, to send a Pay button.
     * NOTE: This type of button must always be the first button in the first row.
     */
    private Boolean pay;
}
