/*
 * The MIT License
 *
 * Copyright 2021 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.botcommandscope;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the scope of bot commands, covering a specific member of a group or supergroup chat.
 */
@Getter
@Setter
@ToString
public class BotCommandScopeChatMember extends BotCommandScope {

    public BotCommandScopeChatMember() {
        setType("chat_member");
    }

    /**
     * Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     */
    @Json(name = "chat_id") private Object chatId;

    /**
     * Unique identifier of the target user
     */
    @Json(name = "user_id") private Integer userId;
}
