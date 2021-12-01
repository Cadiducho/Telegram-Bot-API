package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a service message about a voice chat ended in the chat.
 */
@ToString
@Getter
@Setter
public class VoiceChatEnded {

    /**
     * Voice chat duration in seconds
     */
    private Integer duration;
}
