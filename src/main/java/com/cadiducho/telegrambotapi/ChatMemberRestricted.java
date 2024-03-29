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
 * Represents a {@link ChatMember} that is under certain restrictions in the chat. Supergroups only.
 */
@Getter @Setter
public class ChatMemberRestricted extends ChatMember {

    public ChatMemberRestricted(String status, User user) {
        super(status, user);
    }

    /**
     * True, if the user is a member of the chat at the moment of the request
     */
    @Json(name = "is_member") private Boolean isMember;

    /**
     * True, if the user is allowed to change the chat title, photo and other settings
     */
    @Json(name = "can_change_info") private Boolean canChangeInfo;

    /**
     * True, if the user is allowed to invite new users to the chat
     */
    @Json(name = "can_invite_users") private Boolean canInviteUsers;

    /**
     * True, if the user is allowed to pin messages
     */
    @Json(name = "can_pin_messages") private Boolean canPinMessages;

    /**
     * True, if the user is allowed to create forum topics
     */
    @Json(name = "can_manage_topics") private Boolean canManageTopics;

    /**
     * True, if the user is allowed to send text messages, contacts, locations and venues
     */
    @Json(name = "can_send_messages") private Boolean canSendMessages;

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
     * True, if the user is allowed to send polls
     */
    @Json(name = "can_send_polls") private Boolean canSendPolls;

    /**
     * True, if the user is allowed to send animations, games, stickers and use inline bots
     */
    @Json(name = "can_send_other_messages") private Boolean canSendOtherMessages;

    /**
     * True, if the user is allowed to add web page previews to their messages
     */
    @Json(name = "can_add_web_page_previews") private Boolean canAddWebPagePreviews;

    /**
     * Date when restrictions will be lifted for this user; unix time.
     * If 0, then the user is banned forever
     */
    @Json(name = "until_date") private Integer untilDate;

}
