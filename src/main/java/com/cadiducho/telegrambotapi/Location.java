/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a point on the map.
 */
@ToString
@Getter @Setter
public class Location {

    /**
     * Longitude as defined by sender
     */
    private Float longitude;
    
    /**
     * Latitude as defined by sender
     */
    private Float latitude;
}
