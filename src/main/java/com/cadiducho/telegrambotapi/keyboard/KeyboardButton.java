/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.keyboard;

import com.cadiducho.telegrambotapi.WebAppInfo;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents one button of the reply keyboard. For simple text buttons,
 * String can be used instead of this object to specify the button text.
 * The optional fields web_app, request_user, request_chat, request_contact, request_location, and request_poll are mutually exclusive.
 */
@ToString
@Getter @Setter
public class KeyboardButton {
    
    /**
     * Text of the button. If none of the optional fields are used, it will be sent to the bot as a message when the button is pressed
     */
    private String text;

    @Json(name = "request_user") private KeyboardButtonRequestUser requestUser;

    @Json(name = "request_chat") private KeyboardButtonRequestChat requestChat;

    /**
     * Optional. If True, the user's phone number will be sent as a contact when the button is pressed. Available in private chats only
     */
    @Json(name = "request_contact") private Boolean requestContact;
    
    /**
     * Optional. If True, the user's current location will be sent when the button is pressed. Available in private chats only
     */
    @Json(name = "request_location") private Boolean requestLocation;

    /**
     * Optional. If specified, the user will be asked to create a poll and send it to the bot when the button is pressed.
     * Available in private chats only
     */
    @Json(name = "request_poll") private KeyboardButtonPollType requestPoll;

    /**
     * Optional. If specified, the described Web App will be launched when the button is pressed.
     * The Web App will be able to send a “web_app_data” service message. Available in private chats only.
     */
    @Json(name = "web_app") private WebAppInfo webApp;
}
