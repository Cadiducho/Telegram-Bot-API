package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Data;

/**
 * Represents an invite link for a chat.
 */
@Data
public class ChatInviteLink {

    /**
     * The invite link. If the link was created by another chat administrator, then the second part of the link will be replaced with “…”.
     */
    @Json(name = "invite_link") private String inviteLink;

    /**
     * Creator of the link
     */
    private User creator;

    /**
     * True, if users joining the chat via the link need to be approved by chat administrators
     */
    @Json(name = "creates_join_request") private Boolean createsJoinRequest;

    /**
     * True, if the link is primary
     */
    @Json(name = "is_primary") private Boolean isPrimary;

    /**
     * True, if the link is revoked
     */
    @Json(name = "is_revoked") private Boolean isRevoked;

    /**
     * Optional. Invite link name
     */
    private String name;

    /**
     *	Optional. Point in time (Unix timestamp) when the link will expire or has been expired
     */
    @Json(name = "expire_date") private Integer expireDate;

    /**
     *Optional. Maximum number of users that can be members of the chat simultaneously after joining the chat via this invite link; 1-99999
     */
    @Json(name = "member_limit") private Integer memberLimit;

    /**
     * Optional. Number of pending join requests created using this link
     */
    @Json(name = "pending_join_request_count") private Integer pendingJoinRequestCount;
}
