/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.forum;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class ForumTopicCreated {

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
