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
 * Represents a link to a file. By default, this file will be sent by the user with an optional caption.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the file.
 * Currently, only .PDF and .ZIP files can be sent using this method.
 */
@ToString
@Getter @Setter
public class InlineQueryResultDocument extends InlineQueryResult {

    /**
     * Title of the result
     */
    private String title;

    /**
     * A valid URL for the file
     */
    @Json(name = "document_url") private String documentUrl;
    
    /**
     * Mime type of the content of the file, either “application/pdf” or “application/zip”
     */
    @Json(name = "mime_type") private String mimeType;
    
    /**
     * Optional. Caption, 0-200 characters
     */
    private String caption;
    
    /**
     * Optional. Short description of the result
     */
    private String description;
    
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

    public InlineQueryResultDocument() {
        super("document");
    }

    /**
     *
     * @param documentUrl A valid URL for the file
     * @param mimeType Mime type of the content of the file, either “application/pdf” or “application/zip”
     * @param title Title for the result
     */
    public InlineQueryResultDocument(String documentUrl, String title, String mimeType) {
        this();
        this.documentUrl = documentUrl;
        this.title = title;
        this.mimeType = mimeType;
    }

    /**
     *
     * @param documentUrl A valid URL for the file
     * @param mimeType Mime type of the content of the file, either “application/pdf” or “application/zip”
     * @param title Title for the result
     * @param caption Optional. Caption of the document to be sent, 0-200 characters
     * @param description Optional. Short description of the result
     * @param thumbnailUrl Optional. URL of the thumbnail (jpeg only) for the file
     * @param thumbnailWidth Optional. Thumbnail width
     * @param thumbnailHeight Optional. Thumbnail height
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultDocument(String documentUrl, String title, String mimeType, String caption,
                        String description, String thumbnailUrl, Integer thumbnailWidth, Integer thumbnailHeight,
                        InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.documentUrl = documentUrl;
        this.title = title;
        this.mimeType = mimeType;
        this.caption = caption;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
