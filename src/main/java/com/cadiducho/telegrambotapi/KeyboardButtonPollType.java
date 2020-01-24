/*
 * The MIT License
 *
 * Copyright 2020 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Data;

/**
 * This object represents type of a poll, which is allowed to be created and sent when the corresponding button is pressed.
 */
@Data
public class KeyboardButtonPollType {

    /**
     * 	Optional. If quiz is passed, the user will be allowed to create only polls in the quiz mode.
     * 	If regular is passed, only regular polls will be allowed.
     * 	Otherwise, the user will be allowed to create a poll of any type.
     */
    private String type;
}
