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
 * Represents a venue. By default, the venue will be sent by the user.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the venue.
 */
@ToString
@Getter @Setter
public class InlineQueryResultVenue extends InlineQueryResult {

    /**
     * Location title
     */
    private String title;

    /**
     * Address of the venue
     */
    private String address;
    
    /**
     * Location latitude in degrees
     */
    private Float latitude;
    
    /**
     * 	Location longitude in degrees
     */
    private Float longitude;

    /**
     * Optional. Foursquare identifier of the venue if known
     */
    @Json(name = "foursquare_id") private String foursquareId;

    /**
     * Optional. Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     */
    @Json(name = "foursquare_type") private String foursquareType;
    
    /**
     * Optional. URL of the thumbnail (jpeg only) for the file
     */
    @Json(name = "thumbnail_url") private String thumbnailUrl;
    
    /**
     * Optional. Thumbnail width
     */
    @Json(name = "thumbnail_width") private Integer thumbnailWidth;
    
    /**
     * Optional. Thumbnail height
     */
    @Json(name = "thumbnail_height") private Integer thumbnailHeight;

    public InlineQueryResultVenue() {
        super("venue");
    }

    /**
     *
     * @param title Location title
     * @param address Address of the venue
     * @param latitude Location latitude in degrees
     * @param longitude Location longitude in degrees
     */
    public InlineQueryResultVenue(String title, String address, Float latitude, Float longitude) {
        this();
        this.title = title;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param title Location title
     * @param address Address of the venue
     * @param foursquareId Optional. Foursquare identifier of the venue if known
     * @param foursquareType Optional. Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     * @param latitude Location latitude in degrees
     * @param longitude Location longitude in degrees
     * @param thumbnailUrl Optional. URL of the thumbnail (jpeg only) for the file
     * @param thumbnailWidth Optional. Thumbnail width
     * @param thumbnailHeight Optional. Thumbnail height
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultVenue(String title, String address, String foursquareId, String foursquareType, Float latitude, Float longitude,
                                    String thumbnailUrl, Integer thumbnailWidth, Integer thumbnailHeight,
                                    InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.title = title;
        this.address = address;
        this.foursquareId = foursquareId;
        this.foursquareType = foursquareType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
