/*
 * The MIT License
 *
 * Copyright 2021 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a unique message identifier.
 */
@ToString
@Getter @Setter
public class MessageId {

    /**
     * Unique message identifier
     */
    @Json(name = "message_id") private Integer messageId;

}
