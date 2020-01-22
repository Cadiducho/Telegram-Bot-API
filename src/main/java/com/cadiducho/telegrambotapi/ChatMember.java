/*
 * The MIT License
 *
 * Copyright 2019 Cadiducho.
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
public class ChatMember {
    
    /**
     * 	Information about the user
     */
    private User user;
    
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
     * The member's status in the chat.
     */
    private ChatMemberStatus chatMemberStatus;
    
    /**
     * The member's status in the chat.
     * @return {@link ChatMemberStatus}
     */
    public ChatMemberStatus getChatMemberStatus() {
        if (chatMemberStatus == null) 
            determineStatus();
        return chatMemberStatus;
    }

    /**
     * 	Optional. Owner and administrators only. Custom title for this user
     */
    @Json(name = "custom_title") private String customTitle;

    /**
     * Optional. Restictred and kicked only.
     * Date when restrictions will be lifted for this user, unix time
     */
    @Json(name = "until_date") private Integer untilDate;

    /**
     * Optional. Administrators only. True, if the bot is allowed to edit administrator privileges of that user
     */
    @Json(name = "can_be_edited") private Boolean canBeEdited;

    /**
     * Optional. Administrators only. True, if the administrator can change the chat title, photo and other settings
     */
    @Json(name = "can_change_info") private Boolean canChangeInfo;

    /**
     * Optional. Administrators only. True, if the administrator can post in the channel, channels only
     */
    @Json(name = "can_post_messages") private Boolean canPostMessages;

    /**
     * Optional. Administrators only. True, if the administrator can edit messages of other users, channels only
     */
    @Json(name = "can_edit_messages") private Boolean canEditMessages;

    /**
     * Optional. Administrators only. True, if the administrator can delete messages of other users
     */
    @Json(name = "can_delete_messages") private Boolean canDeleteMessages;

    /**
     * Optional. Administrators only. True, if the administrator can invite new users to the chat
     */
    @Json(name = "can_invite_users") private Boolean canInviteUsers;

    /**
     * Optional. Administrators only. True, if the administrator can restrict, ban or unban chat members
     */
    @Json(name = "can_restrict_members") private Boolean canRestrictMembers;

    /**
     * Optional. Administrators only. True, if the administrator can pin messages, supergroups only
     */
    @Json(name = "can_pin_messages") private Boolean canPinMessages;

    /**
     * Optional. Administrators only. True, if the administrator can add new administrators with a subset of his own privileges or demote administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by the user)
     */
    @Json(name = "can_promote_members") private Boolean canPromoteMembers;

    /**
     * Optional. Restricted only. True, if the user is a member of the chat at the moment of the request
     */
    @Json(name = "is_member") private Boolean isMember;
    
    /**
     * Optional. Restricted only. True, if the user can send text messages, contacts, locations and venues
     */
    @Json(name = "can_send_messages") private Boolean canSendMessages;

    /**
     * Optional. Restricted only. True, if the user can send audios, documents, photos, videos, video notes and voice notes, implies can_send_messages
     */
    @Json(name = "can_send_media_messages") private Boolean canSendMediaMessages;

    /**
     * Optional. Restricted only. True, if the user is allowed to send polls
     */
    @Json(name = "can_send_polls") private Boolean canSendPolls;
    
    /**
     * Optional. Restricted only. True, if the user can send animations, games, stickers and use inline bots, implies can_send_media_messages
     */
    @Json(name = "can_send_other_messages") private Boolean canSendOtherMessages;

    /**
     * Optional. Restricted only. True, if user may add web page previews to his messages, implies can_send_media_messages
     */
    @Json(name = "can_add_web_page_previews") private Boolean canAddWebPagePreviews;
}
