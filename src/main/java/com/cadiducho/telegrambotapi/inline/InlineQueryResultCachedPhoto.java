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
 * Represents a link to a photo stored on the Telegram servers.
 * By default, this photo will be sent by the user with optional caption.
 * Alternatively, you can provide <i>message_text</i> to send it instead of photo.
 */
@ToString
@Getter @Setter
public class InlineQueryResultCachedPhoto extends InlineQueryResult {

    /**
     * A valid file identifier of the photo
     */
    @Json(name = "photo_file_id") private String photoFileId;

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

    public InlineQueryResultCachedPhoto() {
        super("photo");
    }

    /**
     *
     * @param photoFileId A valid file identifier of the photo
     */
    public InlineQueryResultCachedPhoto(String photoFileId) {
        this();
        this.photoFileId = photoFileId;
    }

    /**
     *
     * @param photoFileId A valid file identifier of the photo
     * @param title Optional. Title for the result
     * @param description Optional. Short description of the result
     * @param caption Optional. Caption of the photo to be sent, 0-200 characters
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultCachedPhoto(String photoFileId, String title, String description, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.photoFileId = photoFileId;
        this.title = title;
        this.description = description;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
