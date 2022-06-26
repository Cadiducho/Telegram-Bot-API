package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a join request sent to a chat.
 */
@Data
public class ChatJoinRequest {

    /**
     * Chat to which the request was sent
     */
    private Chat chat;

    /**
     * User that sent the join request
     */
    private User from;

    /**
     * Date the request was sent in Unix time
     */
    private Integer date;

    /**
     * Optional. Bio of the user.
     */
    private String bio;

    /**
     * 	Optional. Chat invite link that was used by the user to send the join request
     */
    @Json(name = "invite_link") private ChatInviteLink inviteLink;


    /**
     * Optional. Invite link name
     */
    private String name;
}
