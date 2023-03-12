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
 * This object defines the criteria used to request a suitable user.
 * The identifier of the selected user will be shared with the bot when the corresponding button is pressed.
 */
@ToString
@Getter @Setter
public class KeyboardButtonRequestUser {

    /**
     * Signed 32-bit identifier of the request, which will be received back in the UserShared object. Must be unique within the message
     */
    @Json(name = "request_id") private Integer requestId;

    /**
     * Optional. Pass True to request a bot, pass False to request a regular user. If not specified, no additional restrictions are applied.
     */
    @Json(name = "user_is_bot") private Integer userIsBot;

    /**
     * Optional. Pass True to request a premium user, pass False to request a non-premium user. If not specified, no additional restrictions are applied.
     */
    @Json(name = "user_is_premium") private Integer userIsPremium;
}
