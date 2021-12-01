package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a service message about a change in auto-delete timer settings.
 */
@ToString
@Getter
@Setter
public class MessageAutoDeleteTimerChanged {

    /**
     * New auto-delete time for messages in the chat; in seconds
     */
    @Json(name = "message_auto_delete_time") Integer messageAutoDeleteTime;
}
