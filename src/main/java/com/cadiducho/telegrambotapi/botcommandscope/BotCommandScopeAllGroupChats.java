/*
 * The MIT License
 *
 * Copyright 2021 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.botcommandscope;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the scope of bot commands, covering all group and supergroup chats.
 */
@Getter
@Setter
@ToString
public class BotCommandScopeAllGroupChats extends BotCommandScope {

    public BotCommandScopeAllGroupChats() {
        setType("all_group_chats");
    }
}
