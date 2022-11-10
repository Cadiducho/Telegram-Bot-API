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

import java.util.List;

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
     * Optional. True, if the supergroup chat is a forum (has topics enabled)
     */
    @Json(name = "is_forum") private String isForum;
    
    /**
     * Optional. Chat photo. Returned only in getChat.
     */
    private ChatPhoto photo;

    /**
     * Optional. If non-empty, the list of all active chat usernames; for private chats, supergroups and channels. Returned only in getChat.
     */
    @Json(name = "active_usernames") private List<String> activeUsernames;

    /**
     * Optional. Custom emoji identifier of emoji status of the other party in a private chat. Returned only in getChat.
     */
    @Json(name = "emoji_status_custom_emoji_id") private String emojiStatusCustomEmojiId;

    /**
     * Optional. Bio of the other party in a private chat. Returned only in getChat.
     */
    private String bio;

    /**
     * Optional. True, if privacy settings of the other party in the private chat allows to use tg://user?id=<user_id> links only in chats with the user. Returned only in getChat.
     */
    @Json(name = "has_private_forwards") private Boolean hasPrivateForwards;

    /**
     * Optional. True, if the privacy settings of the other party restrict sending voice and video note messages in the private chat. Returned only in getChat.
     */
    @Json(name = "has_restricted_voice_and_video_messages") private Boolean hasRestrictedVoiceAndVideoMessages;

    /**
     * Optional. True, if users need to join the supergroup before they can send messages. Returned only in getChat.
     */
    @Json(name = "join_to_send_messages") private Boolean joinToSendMessages;

    /**
     * Optional. True, if all users directly joining the supergroup need to be approved by supergroup administrators. Returned only in getChat.
     */
    @Json(name = "join_by_request") private Boolean joinByRequest;

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
     * Optional. True, if messages from the chat can't be forwarded to other chats. Returned only in getChat.
     */
    @Json(name = "has_protected_content") private Boolean hasProtectedContent;
    
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
        return new User(id, firstName, lastName, username);
    }
}
