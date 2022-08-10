/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Describes an inline message sent by a <a href="https://core.telegram.org/bots/webapps">Web App</a> on behalf of a user.
 */
@ToString
@Getter @Setter
public class SentWebAppMessage {

    /**
     * Optional. Identifier of the sent inline message. Available only if there is an inline keyboard attached to the message.
     */
    @Json(name = "inline_message_id") private String inlineMessageId;
}
