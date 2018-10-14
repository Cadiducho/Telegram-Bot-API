/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the content of a venue message to be sent as the result of an inline query.
 */
@ToString
@Getter @Setter
public class InputVenueMessageContent extends InputMessageContent {
    
    /**
     * Latitude of the location in degrees
     */
    private Float latitude;
    
    /**
     * Longitude of the location in degrees
     */
    private Float longitude;
    
    /**
     * Name of the venue
     */
    private String title;
    
    /**
     * Address of the venue
     */
    private String address;
    
    /**
     * 	Optional. Foursquare identifier of the venue, if known
     */
    @Json(name = "foursquare_id") private String foursquareId;
}
