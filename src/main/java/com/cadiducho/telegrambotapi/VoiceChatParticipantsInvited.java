/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * This object represents a service message about new members invited to a video chat.
 */
@ToString
@Getter
@Setter
public class VoiceChatParticipantsInvited {

    /**
     * Optional. New members that were invited to the video chat
     */
    List<User> users;
}
