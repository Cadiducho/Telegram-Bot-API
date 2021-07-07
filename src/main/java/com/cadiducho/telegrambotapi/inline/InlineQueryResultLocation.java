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
 * Represents a location on a map. By default, the location will be sent by the user.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the location.
 */
@ToString
@Getter @Setter
public class InlineQueryResultLocation extends InlineQueryResult {

    /**
     * Location latitude in degrees
     */
    private Float latitude;

    /**
     * 	Location longitude in degrees
     */
    private Float longitude;

    /**
     * Location title
     */
    private String title;

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
     * Optional. For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
     */
    @Json(name = "proximity_alert_radius") private Integer proximityAlertRadius;

    /**
     * Optional. URL of the thumbnail (jpeg only) for the file
     */
    @Json(name = "thumb_url") private String thumbUrl;

    /**
     * Optional. Thumbnail width
     */
    @Json(name = "thumb_width") private Integer thumbWidth;

    /**
     * Optional. Thumbnail height
     */
    @Json(name = "thumb_height") private Integer thumbHeight;

    public InlineQueryResultLocation() {
        super("location");
    }

    /**
     *
     * @param title Location title
     * @param latitude Location latitude in degrees
     * @param longitude Location longitude in degrees
     */
    public InlineQueryResultLocation(String title, Float latitude, Float longitude) {
        this();
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param title Location title
     * @param latitude Location latitude in degrees
     * @param longitude Location longitude in degrees
     * @param thumbUrl Optional. URL of the thumbnail (jpeg only) for the file
     * @param thumbWidth Optional. Thumbnail width
     * @param thumbHeight Optional. Thumbnail height
     * @param livePeriod Optional. Period in seconds for which the location can be updated, should be between 60 and 86400.
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultLocation(String title, Float latitude, Float longitude, String thumbUrl, Integer thumbWidth, Integer thumbHeight, Integer livePeriod,
                                    InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.thumbUrl = thumbUrl;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
        this.livePeriod = livePeriod;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
