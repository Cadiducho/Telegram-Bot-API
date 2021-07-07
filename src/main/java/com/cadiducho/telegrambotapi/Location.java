/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
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

    /**
     * Optional. The radius of uncertainty for the location, measured in meters; 0-1500
     */
    @Json(name = "horizontal_accuracy") private Float horizontalAccuracy;

    /**
     * 	Optional. Time relative to the message sending date, during which the location can be updated, in seconds. For active live locations only.
     */
    @Json(name = "live_period") private Integer livePeriod;

    /**
     * Optional. The direction in which user is moving, in degrees; 1-360. For active live locations only.
     */
    private Integer heading;

    /**
     * Optional. Maximum distance for proximity alerts about approaching another chat member, in meters. For sent live locations only.
     */
    @Json(name = "proximity_alert_radius") private Integer proximityAlertRadius;
}
