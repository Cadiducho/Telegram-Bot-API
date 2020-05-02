/*
 * The MIT License
 *
 * Copyright 2020 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a dice with random value from 1 to 6.
 */
@ToString
@Getter @Setter
public class Dice {

    /**
     * Emoji on which the dice throw animation is based
     */
    private String emoji;

    /**
     * Value of the dice, 1-6 for currently supported base emoji
     */
    private Integer value;

}
