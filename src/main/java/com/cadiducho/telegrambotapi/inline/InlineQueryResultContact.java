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
public class InlineQueryResultContact extends InlineQueryResult {

    /**
     * Contact's phone number
     */
    @Json(name = "phone_number") private String phoneNumber;
    
    /**
     * 	Contact's first name
     */
    @Json(name = "first_name") private String firstName;
    
    /**
     * Optional. Contact's last name
     */
    @Json(name = "last_name") private String lastName;
    
    /**
     * Optional. URL of the thumbnail (jpeg only) for the file
     */
    @Json(name = "thumbUrl") private String thumbUrl;
    
    /**
     * Optional. Thumbnail width
     */
    @Json(name = "thumb_width") private Integer thumbWidth;
    
    /**
     * Optional. Thumbnail height
     */
    @Json(name = "thumb_height") private Integer thumbHeight;

    public InlineQueryResultContact() {
        super("contact");
    }

    /**
     *
     * @param phoneNumber Contact's phone number
     * @param firstName Contact's first name
     */
    public InlineQueryResultContact(String phoneNumber, String firstName) {
        this();
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
    }

    /**
     *
     * @param phoneNumber Contact's phone number
     * @param firstName Contact's first name
     * @param lastName Optional. Contact's last name
     * @param thumbUrl Optional. URL of the thumbnail (jpeg only) for the file
     * @param thumbWidth Optional. Thumbnail width
     * @param thumbHeight Optional. Thumbnail height
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultContact(String phoneNumber, String firstName, String lastName,
                                    String thumbUrl, Integer thumbWidth, Integer thumbHeight,
                                    InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thumbUrl = thumbUrl;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
