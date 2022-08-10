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

@Getter @Setter
public class MenuButtonWebApp extends MenuButton {

    public MenuButtonWebApp(String text, WebAppInfo webApp) {
        super("web_app");
        this.text = text;
        this.webApp = webApp;
    }

    private String text;

    @Json(name = "web_app") private WebAppInfo webApp;
}

