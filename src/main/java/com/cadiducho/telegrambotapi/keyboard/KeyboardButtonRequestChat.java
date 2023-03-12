/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.keyboard;

import com.cadiducho.telegrambotapi.ChatAdministratorRights;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object defines the criteria used to request a suitable chat.
 * The identifier of the selected chat will be shared with the bot when the corresponding button is pressed.
 */
@ToString
@Getter @Setter
public class KeyboardButtonRequestChat {

    /**
     * Signed 32-bit identifier of the request, which will be received back in the UserShared object. Must be unique within the message
     */
    @Json(name = "request_id") private Integer requestId;

    /**
     * Pass True to request a channel chat, pass False to request a group or a supergroup chat.
     */
    @Json(name = "chat_is_channel") private Boolean chatIsChannel;

    /**
     * Optional. Pass True to request a forum supergroup, pass False to request a non-forum chat. If not specified, no additional restrictions are applied.
     */
    @Json(name = "chat_is_forum") private Boolean chatIsForum;

    /**
     * Optional. Pass True to request a supergroup or a channel with a username, pass False to request a chat without a username. If not specified, no additional restrictions are applied.
     */
    @Json(name = "chat_has_username") private Boolean chatHasUsername;

    /**
     * Optional. Pass True to request a chat owned by the user. Otherwise, no additional restrictions are applied.
     */
    @Json(name = "chat_is_created") private Boolean chatIsCreated;

    /**
     * Optional. A JSON-serialized object listing the required administrator rights of the user in the chat.
     * The rights must be a superset of bot_administrator_rights. If not specified, no additional restrictions are applied.
     */
    @Json(name = "user_administrator_rights") private ChatAdministratorRights userAdministratorRights;

    /**
     * Optional. A JSON-serialized object listing the required administrator rights of the bot in the chat.
     * The rights must be a subset of user_administrator_rights. If not specified, no additional restrictions are applied.
     */
    @Json(name = "bot_administrator_rights") private ChatAdministratorRights botAdministratorRights;

    /**
     * Optional. Pass True to request a chat with the bot as a member. Otherwise, no additional restrictions are applied.
     */
    @Json(name = "bot_is_member") private Boolean botIsMember;
}
