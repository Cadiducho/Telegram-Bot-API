/*
 * The MIT License
 *
 * Copyright 2020 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */
package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Data;

import java.util.List;

/**
 * This object represents an answer of a user in a non-anonymous poll.
 */
@Data
public class PollAnswer {

    /**
     * Unique poll identifier
     */
    @Json(name = "poll_id") private String pollId;

    /**
     * The user, who changed the answer to the poll
     */
    private User user;

    /**
     * 0-based identifiers of answer options, chosen by the user.
     * May be empty if the user retracted their vote.
     */
    @Json(name = "options_ids") private List<Integer> optionsIds;
}
