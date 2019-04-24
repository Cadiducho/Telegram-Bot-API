/*
 * The MIT License
 *
 * Copyright 2019 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Data;

/**
 * This object contains information about a poll.
 */
@Data
public class PollOption {

    /**
     * Option text, 1-100 characters
     */
    private String text;
    
    /**
     * Number of users that voted for this option
     */
    @Json(name = "voter_count") private Integer voterCount;
}
