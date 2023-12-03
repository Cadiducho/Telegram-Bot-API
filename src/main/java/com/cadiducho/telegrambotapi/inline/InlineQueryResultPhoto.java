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
 * Represents a link to a photo.
 * By default, this photo will be sent by the user with optional caption.
 * Alternatively, you can provide <i>message_text</i> to send it instead of photo.
 */
@ToString
@Getter @Setter
public class InlineQueryResultPhoto extends InlineQueryResult {

    /**
     * A valid URL of the photo. Photo must be in <b>jpeg</b> format. Photo size must not exceed 5MB
     */
    @Json(name = "photo_url") private String photoUrl;

    /**
     * Optional. Width of the photo
     */
    @Json(name = "photo_width") private Integer photoWidth;

    /**
     * Optional. Height of the photo
     */
    @Json(name = "photo_height") private Integer photoHeight;

    /**
     * URL of the thumbnail for the photo
     */
    @Json(name = "thumbnail_url") private String thumbnailUrl;

    /**
     * Optional. Title for the result
     */
    private String title;

    /**
     * Optional. Short description of the result
     */
    private String description;

    /**
     * Optional. Caption of the photo to be sent, 0-200 characters
     */
    private String caption;

    public InlineQueryResultPhoto() {
        super("photo");
    }

    /**
     *
     * @param photoUrl A valid URL of the photo. Photo must be in <b>jpeg</b> format. Photo size must not exceed 5MB
     * @param thumbnailUrl URL of the thumbnail for the photo
     */
    public InlineQueryResultPhoto(String photoUrl, String thumbnailUrl) {
        this();
        this.photoUrl = photoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     *
     * @param photoUrl A valid URL of the photo. Photo must be in <b>jpeg</b> format. Photo size must not exceed 5MB
     * @param thumbnailUrl URL of the thumbnail for the photo
     * @param photoWidth Optional. Width of the photo
     * @param photoHeight Optional. Height of the photo
     * @param title Optional. Title for the result
     * @param description Optional. Short description of the result
     * @param caption Optional. Caption of the photo to be sent, 0-200 characters
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultPhoto(String photoUrl, String thumbnailUrl, Integer photoWidth, Integer photoHeight,
                                  String title, String description, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.photoUrl = photoUrl;
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeight;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
