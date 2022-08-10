/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a service message about a video chat ended in the chat.
 */
@ToString
@Getter
@Setter
public class VideoChatEnded {

    /**
     * Video chat duration in seconds
     */
    private Integer duration;
}
