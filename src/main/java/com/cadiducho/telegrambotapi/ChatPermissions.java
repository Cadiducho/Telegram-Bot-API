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
     * 	True, if the user is allowed to send audios
     */
    @Json(name = "can_send_audios") private Boolean canSendAudios;

    /**
     * True, if the user is allowed to send documents
     */
    @Json(name = "can_send_documents") private Boolean canSendDocuments;

    /**
     * True, if the user is allowed to send photos
     */
    @Json(name = "can_send_photos") private Boolean canSendPhotos;

    /**
     * True, if the user is allowed to send videos
     */
    @Json(name = "can_send_videos") private Boolean canSendVideos;

    /**
     * True, if the user is allowed to send video notes
     */
    @Json(name = "can_send_video_notes") private Boolean canSendVideoNotes;

    /**
     * True, if the user is allowed to send voice notes
     */
    @Json(name = "can_send_voice_notes") private Boolean canSendVoiceNotes;

    /**
     * Optional. True, if the user is allowed to send polls, implies can_send_messages
     */
    @Json(name = "can_send_polls") private Boolean canSendPolls;

    /**
     * Optional. True, if the user is allowed to send animations, games, stickers and use inline bots
     */
    @Json(name = "can_send_other_messages") private Boolean canSendOtherMessages;

    /**
     * Optional. True, if the user is allowed to add web page previews to their messages
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
