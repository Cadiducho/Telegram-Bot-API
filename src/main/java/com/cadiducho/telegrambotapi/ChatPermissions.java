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
 * Describes actions that a non-administrator user is allowed to take in a chat.
 */
@ToString
@Getter @Setter
public class ChatPermissions {
    
    /**
     * Optional. True, if the user is allowed to send text messages, contacts, locations and venues
     */
    @Json(name = "can_send_messages") private Integer canSendMessages;

    /**
     * Optional. True, if the user is allowed to send audios, documents, photos, videos, video notes and voice notes, implies can_send_messages
     */
    @Json(name = "can_send_media_messages") private Boolean canSendMediaMessages;

    /**
     * Optional. True, if the user is allowed to send polls, implies can_send_messages
     */
    @Json(name = "can_send_polls") private Boolean canSendPolls;

    /**
     * 	Optional. True, if the user is allowed to send animations, games, stickers and use inline bots, implies can_send_media_messages
     */
    @Json(name = "can_send_other_messages") private Boolean canSendOtherMessages;

    /**
     * Optional. True, if the user is allowed to add web page previews to their messages, implies can_send_media_messages
     */
    @Json(name = "can_add_web_page_previews") private Boolean canAddWebPagePreviews;

    /**
     * Optional. True, if the user is allowed to change the chat title, photo and other settings. Ignored in public supergroups
     */
    @Json(name = "can_change_info") private Boolean canChangeInfo;

    /**
     * Optional. True, if the user is allowed to invite new users to the chat
     */
    @Json(name = "can_invite_users") private Boolean canInviteUsers;

    /**
     * Optional. True, if the user is allowed to pin messages. Ignored in public supergroups
     */
    @Json(name = "can_pin_messages") private Boolean canPinMessages;

    /**
     * Optional. True, if the user is allowed to create forum topics. If omitted defaults to the value of can_pin_messages
     */
    @Json(name = "can_manage_topics") private Boolean canManageTopics;
    
}
