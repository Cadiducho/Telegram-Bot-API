/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a {@link ChatMember} that owns the chat and has all administrator privileges.
 */
@Getter @Setter
public class ChatMemberOwner extends ChatMember {

    public ChatMemberOwner(String status, User user) {
        super(status, user);
    }

    /**
     *	True, if the user's presence in the chat is hidden
     */
    @Json(name = "is_anonymous") private Boolean isAnonymous;

    /**
     *	Optional. Custom title for this user
     */
    @Json(name = "custom_title") private String customTitle;

}
