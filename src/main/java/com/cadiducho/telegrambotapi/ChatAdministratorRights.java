/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class ChatAdministratorRights {

    /**
     * True, if the user's presence in the chat is hidden
     */
    @Json(name = "is_anonymous") private Boolean isAnonymous;

    /**
     * 	True, if the administrator can access the chat event log, chat statistics, message statistics in channels,
     * 	see channel members, see anonymous administrators in supergroups and ignore slow mode.
     * 	Implied by any other administrator privilege
     */
    @Json(name = "can_manage_chat") private Boolean canManageChat;

    /**
     * True, if the administrator can delete messages of other users
     */
    @Json(name = "can_delete_messages") private Boolean canDeleteMessages;

    /**
     * True, if the administrator can manage video chats
     */
    @Json(name = "can_manage_video_chats") private Boolean canManageVideoChats;

    /**
     * True, if the administrator can restrict, ban or unban chat members
     */
    @Json(name = "can_restrict_members") private Boolean canRestrictMembers;

    /**
     * True, if the administrator can add new administrators with a subset of their own privileges or demote administrators
     * that he has promoted, directly or indirectly (promoted by administrators that were appointed by the user)
     */
    @Json(name = "can_promote_members") private Boolean canPromoteMembers;

    /**
     * True, if the user is allowed to change the chat title, photo and other settings
     */
    @Json(name = "can_change_info") private Boolean canChangeInfo;

    /**
     * True, if the user is allowed to invite new users to the chat
     */
    @Json(name = "can_invite_users") private Boolean canInviteUsers;

    /**
     * Optional. True, if the administrator can post in the channel; channels only
     */
    @Json(name = "can_post_messages") private Boolean canPostMessages;

    /**
     * Optional. True, if the administrator can edit messages of other users and can pin messages; channels only
     */
    @Json(name = "can_edit_messages") private Boolean canEditMessages;

    /**
     * Optional. True, if the user is allowed to pin messages; groups and supergroups only
     */
    @Json(name = "can_pin_messages") private Boolean canPinMessages;

    /**
     * Optional. True, if the administrator can post stories in the channel; channels only
     */
    @Json(name = "can_post_stories") private Boolean canPostStories;

    /**
     * Optional. True, if the administrator can edit stories posted by other users; channels only
     */
    @Json(name = "can_edit_stories") private Boolean canEditStories;

    /**
     * Optional. True, if the administrator can delete stories posted by other users; channels only
     */
    @Json(name = "can_delete_stories") private Boolean canDeleteStories;

    /**
     * Optional. True, if the user is allowed to create, rename, close, and reopen forum topics; supergroups only
     */
    @Json(name = "can_manage_topics") private Boolean canManageTopics;
}
