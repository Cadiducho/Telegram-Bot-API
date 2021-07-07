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
     * Optional. The radius of uncertainty for the location, measured in meters; 0-1500
     */
    @Json(name = "horizontal_accuracy") private Integer horizontalAccuracy;

    /**
     * Optional. Period in seconds for which the location can be updated, should be between 60 and 86400.
     */
    @Json(name = "live_period") private Integer livePeriod;

    /**
     * Optional. For live locations, a direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
     */
    private Integer heading;

    /**
     * 	Optional. For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
     */
    @Json(name = "proximity_alert_radius") private Integer proximityAlertRadius;
    
}
