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
 * Represents a {@link ChatMember}  that was banned in the chat and can't return to the chat or view chat messages.
 */
@Getter @Setter
public class ChatMemberBanned extends ChatMember {

    public ChatMemberBanned(String status, User user) {
        super(status, user);
    }

    /**
     * Date when restrictions will be lifted for this user; unix time.
     * If 0, then the user is banned forever
     */
    @Json(name = "until_date") private Integer untilDate;
}
