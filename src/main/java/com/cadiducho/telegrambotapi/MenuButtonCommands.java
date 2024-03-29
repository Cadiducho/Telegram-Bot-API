/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuButtonCommands extends MenuButton {

    public MenuButtonCommands() {
        super("commands");
    }
}
