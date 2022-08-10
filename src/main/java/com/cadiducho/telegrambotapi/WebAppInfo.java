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
import lombok.ToString;

/**
 * This object describes a Web App. <a href="https://core.telegram.org/bots/webapps">https://core.telegram.org/bots/webapps</a>
 */
@ToString
@Getter @Setter
@AllArgsConstructor
public class WebAppInfo {

    /**
     * An HTTPS URL of a Web App to be opened with additional data as specified in Initializing Web Apps
     */
    private String url;
}
