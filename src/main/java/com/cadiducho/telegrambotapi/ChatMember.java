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
 * This object contains information about one member of the chat.
 * See {@link ChatMember#getChatMemberStatus()}
 */
@ToString
@Getter @Setter
public abstract class ChatMember {
    
    /**
     * The member's status in the chat. Can be “creator”, “administrator”, “member”, “restricted”, “left” or “kicked”
     */
    private String status;

    private void determineStatus() {
        switch (status) {
            case "creator": chatMemberStatus = ChatMemberStatus.CREATOR;
            case "administrator": chatMemberStatus = ChatMemberStatus.ADMINISTRATOR;
            case "restricted": chatMemberStatus = ChatMemberStatus.RESTRICTED;
            case "kicked": chatMemberStatus = ChatMemberStatus.KICKED;
            case "left": chatMemberStatus = ChatMemberStatus.LEFT;
            default: chatMemberStatus = ChatMemberStatus.MEMBER;
        }
    }

    public enum ChatMemberStatus {
        CREATOR,
        ADMINISTRATOR,
        MEMBER,
        RESTRICTED,
        LEFT,
        KICKED
    }

    /**
     * The member's status in the chat. {@link ChatMemberStatus}
     */
    private ChatMemberStatus chatMemberStatus;

    /**
     * 	Information about the user
     */
    private User user;

    public ChatMember(String status, User user) {
        this.status = status;
        determineStatus();
        this.user = user;
    }










}
