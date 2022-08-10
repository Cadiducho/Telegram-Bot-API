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
import lombok.ToString;

/**
 * This object represents changes in the status of a chat member.
 */
@ToString
@Getter
@Setter
public class ChatMemberUpdated {

    /**
     * Chat the user belongs to
     */
    private Chat chat;

    /**
     * Performer of the action, which resulted in the change
     */
    private User from;

    /**
     * Date the change was done in Unix time
     */
    private Integer date;

    /**
     * Previous information about the chat member
     */
    @Json(name = "old_chat_member") private ChatMember oldChatMember;

    /**
     * New information about the chat member
     */
    @Json(name = "new_chat_member") private ChatMember newChatMember;

    /**
     * Optional. Chat invite link, which was used by the user to join the chat; for joining by invite link events only.
     */
    @Json(name = "invite_link") private ChatInviteLink inviteLink;
}
