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
 * Represents a {@link ChatMember} that has no additional privileges or restrictions.
 */
@Getter @Setter
public class ChatMemberMember extends ChatMember {

    public ChatMemberMember(String status, User user) {
        super(status, user);
    }
}
