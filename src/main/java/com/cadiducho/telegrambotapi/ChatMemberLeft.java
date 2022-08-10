/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a {@link ChatMember} that isn't currently a member of the chat, but may join it themselves.
 */
@Getter @Setter
public class ChatMemberLeft extends ChatMember {

    public ChatMemberLeft(String status, User user) {
        super(status, user);
    }
}
