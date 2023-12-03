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
     * Optional. Additional data about the contact in the form of a vCard, 0-2048 bytes
     */
    private String vcard;
    
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
     * @param thumbnailUrl Optional. URL of the thumbnail (jpeg only) for the file
     * @param thumbnailWidth Optional. Thumbnail width
     * @param thumbnailHeight Optional. Thumbnail height
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultContact(String phoneNumber, String firstName, String lastName,
                                    String thumbnailUrl, Integer thumbnailWidth, Integer thumbnailHeight,
                                    InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
