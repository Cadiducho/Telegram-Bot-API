/*
 * The MIT License
 *
 * Copyright 2019 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import java.util.List;
import lombok.Data;

/**
 * This object contains information about a poll.
 */
@Data
public class Poll {

    /**
     * Unique poll identifier
     */
    private String id;
    
    /**
     * Poll question, 1-255 characters
     */
    private String question;
    
    /**
     * List of poll options
     */
    private List<PollOption> options;
    
    /**
     * True, if the poll is closed
     */
    @Json(name = "is_closed") private Boolean isClosed;
}
