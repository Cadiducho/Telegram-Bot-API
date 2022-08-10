/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This object describes the bot's menu button in a private chat.
 * It should be one of
 * <ul>
 * <li>{@link MenuButtonCommands}</li>
 * <li>{@link MenuButtonWebApp}</li>
 * <li>{@link MenuButtonDefault}</li>
 * </ul>
 * If a menu button other than {@link MenuButtonDefault} is set for a private chat, then it is applied in the chat.
 * Otherwise the default menu button is applied. By default, the menu button opens the list of bot commands.
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class MenuButton {

    /**
     * Type of the button. Should be commands {@link MenuButtonCommands}, web_app {@link MenuButtonWebApp} or default {@link MenuButtonDefault}
     */
    protected String type;
}
