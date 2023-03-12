/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.forum;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a service message about an edited forum topic.
 */
@ToString
@Getter @Setter
public class ForumTopicEdited {

    /**
     * Optional. Optional. New name of the topic, if it was edited
     */
    private String name;

    /**
     * 	Optional. New identifier of the custom emoji shown as the topic icon, if it was edited; an empty string if the icon was removed
     */
    @Json(name = "icon_custom_emoji_id") private Integer iconCustomEmojiId;
}
