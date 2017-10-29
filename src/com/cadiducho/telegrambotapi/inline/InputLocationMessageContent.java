/*
 * The MIT License
 *
 * Copyright 2017 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the content of a location message to be sent as the result of an inline query.
 */
@ToString
@Getter @Setter
public class InputLocationMessageContent extends InputMessageContent {
    
    /**
     * Latitude of the location in degrees
     */
    private Float latitude;
    
    /**
     * Longitude of the location in degrees
     */
    private Float longitude;
    
    /**
     * Optional. Period in seconds for which the location can be updated, should be between 60 and 86400.
     */
    private Integer live_period;
    
}
