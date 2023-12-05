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
     * Optional. The chat that changed the answer to the poll, if the voter is anonymous
     */
    @Json(name = "voter_chat") private Chat voterChat;

    /**
     * Optional. The user that changed the answer to the poll, if the voter isn't anonymous
     */
    private User user;

    /**
     * 0-based identifiers of chosen answer options.
     * May be empty if the vote was retracted.
     */
    @Json(name = "options_ids") private List<Integer> optionsIds;
}
