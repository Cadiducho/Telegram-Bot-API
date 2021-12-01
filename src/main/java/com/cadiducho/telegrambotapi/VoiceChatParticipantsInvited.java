package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * This object represents a service message about new members invited to a voice chat.
 */
@ToString
@Getter
@Setter
public class VoiceChatParticipantsInvited {

    /**
     * Optional. New members that were invited to the voice chat
     */
    List<User> users;
}
