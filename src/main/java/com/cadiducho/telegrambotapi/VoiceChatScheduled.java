package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a service message about a voice chat scheduled in the chat.
 */
@ToString
@Getter
@Setter
public class VoiceChatScheduled {

    /**
     * Point in time (Unix timestamp) when the voice chat is supposed to be started by a chat administrator
     */
    @Json(name = "start_date") private Integer startDate;
}
