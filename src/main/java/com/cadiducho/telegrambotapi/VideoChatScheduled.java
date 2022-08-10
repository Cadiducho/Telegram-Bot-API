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
 * This object represents a service message about a video chat scheduled in the chat.
 */
@ToString
@Getter
@Setter
public class VideoChatScheduled {

    /**
     * Point in time (Unix timestamp) when the video chat is supposed to be started by a chat administrator
     */
    @Json(name = "start_date") private Integer startDate;
}
