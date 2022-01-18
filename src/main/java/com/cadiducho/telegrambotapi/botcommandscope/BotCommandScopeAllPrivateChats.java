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
 * Represents the scope of bot commands, covering all private chats.
 */
@Getter
@Setter
@ToString
public class BotCommandScopeAllPrivateChats extends BotCommandScope {

    public BotCommandScopeAllPrivateChats() {
        setType("all_private_chats");
    }
}
