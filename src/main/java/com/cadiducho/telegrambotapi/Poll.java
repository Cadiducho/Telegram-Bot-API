/*
 * The MIT License
 *
 * Copyright 2020 Cadiducho.
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
     * Total number of users that voted in the poll
     */
    @Json(name = "total_voter_count") private Integer totalVoterCount;
    
    /**
     * True, if the poll is closed
     */
    @Json(name = "is_closed") private Boolean isClosed;

    /**
     * True, if the poll is anonymous
     */
    @Json(name = "is_anonymous") private Boolean isAnonymous;

    /**
     * Poll type, currently can be “regular” or “quiz”
     */
    private String type;

    /**
     * True, if the poll allows multiple answers
     */
    @Json(name = "allows_multiple_answers") private Boolean allowsMultipleAnswers;

    /**
     * 	Optional. 0-based identifier of the correct answer option.
     * 	Available only for polls in the quiz mode, which are closed, or was sent (not forwarded) by the bot or to the private chat with the bot.
     */
    @Json(name = "correct_option_id") private Integer correctOptionId;

    /**
     * Optional. Text that is shown when a user chooses an incorrect answer or taps on the lamp icon in a quiz-style poll, 0-200 characters
     */
    private String explanation;

    /**
     * Optional. Special entities like usernames, URLs, bot commands, etc. that appear in the explanation
     */
    @Json(name = "explanation_entities") private  List<MessageEntity> explanationEntitites;

    /**
     * Optional. Amount of time in seconds the poll will be active after creation
     */
    @Json(name = "open_period") private Integer openPeriod;

    /**
     * Optional. Point in time (Unix timestamp) when the poll will be automatically closed
     */
    @Json(name = "close_date") private Integer closePeriod;
}
