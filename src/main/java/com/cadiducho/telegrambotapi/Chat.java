/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a chat.
 * This might be a chat with a {@link User} or a GroupChat
 * See {@link ChatType} by Chat#getChatType()
 */
@ToString
@Getter @Setter
public class Chat {
    
    /**
     * Unique identifier for this chat
     */
    private Long id;
    
    /**
     * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    private String type;
    
    /**
     * Optional. Title, for channels and group chats
     */
    private String title;
    
    /**
     * Optional. Username, for private chats and channels if available
     */
    private String username;
    
    /**
     * Optional. First name of the other party in a private chat
     */
    @Json(name = "first_name") private String firstName;
    
    /**
     * Optional. Last name of the other party in a private chat
     */
    @Json(name = "last_name") private String lastName;
    
    /**
     * Optional. Chat photo. Returned only in getChat.
     */
    private ChatPhoto photo;

    /**
     * Optional. Bio of the other party in a private chat. Returned only in getChat.
     */
    private String bio;

    /**
     * Optional. Description, for supergroups and channel chats. Returned only in getChat.
     */
    private String description;
    
    /**
     * Optional. Chat invite link, for supergroups and channel chats. Returned only in getChat.
     */
    @Json(name = "invite_link") private String inviteLink;
    
    /**
     * Optional. Pinned message, for supergroups. Returned only in getChat.
     */
    @Json(name = "pinned_message") private Message pinnedMessage;
    
    /**
     * Optional. Default chat member permissions, for groups and supergroups. Returned only in getChat.
     */
    private ChatPermissions permissions;

    /**
     * Optional. For supergroups, the minimum allowed delay between consecutive messages sent by each unpriviledged user.
     * Returned only in getChat.
     */
    @Json(name = "slow_mode_delay") private Integer slowModeDelay;
    
    /**
     * Optional. For supergroups, name of group sticker set. Returned only in getChat.
     */
    @Json(name = "sticker_set_name") private String stickerSetName;
    
    /**
     * Optional. True, if the bot can change the group sticker set. Returned only in getChat.
     */
    @Json(name = "can_set_sticker_set") private Boolean canSetStickerSet;

    /**
     * Optional. Unique identifier for the linked chat, i.e. the discussion group identifier for a channel and vice versa; for supergroups and channel chats.
     */
    @Json(name = "linked_chat_id") private Long linkedChatId;

    /**
     * 	Optional. For supergroups, the location to which the supergroup is connected. Returned only in getChat.
     */
    private ChatLocation location;

    /**
     * Type of chat, can be either private, group, supergroup or channel
     */
    private ChatType chatType;
    
    public ChatType getChatType() {
        if (chatType == null) 
            determineChatType();
        return chatType;
    }
    
    private void determineChatType() {
        switch (type) {
            case "private": chatType = ChatType.PRIVATE;
            case "group": chatType = ChatType.GROUP;
            case "supergroup": chatType = ChatType.SUPERGROUP;
            case "channel": chatType = ChatType.CHANNEL;
            default: chatType = ChatType.UNKNOWN;
        }
    }
    
    public enum ChatType {
        PRIVATE,
        GROUP,
        SUPERGROUP,
        CHANNEL,
        UNKNOWN
    }
    
    /**
     * @return Whether this is a chat with a {@link User}
     */
    public boolean isUser() {
        return title == null;
    }
    
    /**
     * @return Whether this is a GroupChat
     */
    public boolean isGroupChat() {
        return !isUser();
    }
    
    /**
     * Returns this chat as a {@link User}.
     * Before invoking, check whether this chat is actually a chat with a user
     * by calling {@link Chat#isUser()}.
     *
     * @return This chat as a {@link User} object
     */
    public User asUser() {
        return new User(Integer.parseInt(id), firstName, lastName, username);
    }
}
