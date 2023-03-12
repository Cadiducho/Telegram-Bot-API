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
 * This object contains information about the user whose identifier was shared with the bot using a KeyboardButtonRequestUser button.
 */
@ToString
@Getter @Setter
public class UserShared {

    /**
     * Identifier of the request
     */
    @Json(name = "request_id") private Integer requestId;

    /**
     * Identifier of the shared user
     */
    @Json(name = "user_id") private Long userId;
}
