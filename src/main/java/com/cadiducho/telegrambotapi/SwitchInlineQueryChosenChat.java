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

/**
 * This object represents an inline button that switches the current user to inline mode in a chosen chat, with an optional default inline query.
 */
@ToString
@Getter @Setter
public class SwitchInlineQueryChosenChat {

    /**
     * Optional. The default inline query to be inserted in the input field. If left empty, only the bot's username will be inserted
     */
    private String query;

    /**
     * Optional. True, if private chats with users can be chosen
     */
    @Json(name = "allow_user_chats") private Boolean allowUserChats;

    /**
     * Optional. True, if private chats with bots can be chosen
     */
    @Json(name = "allow_bot_chats") private Boolean allowBotChats;

    /**
     * Optional. True, if group and supergroup chats can be chosen
     */
    @Json(name = "allow_group_chats") private Boolean allowGroupChats;

    /**
     * Optional. True, if channel chats can be chosen
     */
    @Json(name = "allow_channel_chats") private Boolean allowChannelChats;
}
