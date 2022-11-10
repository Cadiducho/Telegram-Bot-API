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
 * This object represents a forum topic.
 */
@ToString
@Getter @Setter
public class ForumTopic {

    /**
     * Unique identifier of the forum topic
     */
    @Json(name = "message_thread_id") private Integer messageThreadId;

    /**
     * Name of the topic
     */
    private String name;

    /**
     * Color of the topic icon in RGB format
     */
    @Json(name = "icon_color") private Integer iconColor;

    /**
     * Optional. Unique identifier of the custom emoji shown as the topic icon
     */
    @Json(name = "icon_custom_emoji_id") private String iconCustomEmojiId;
}
