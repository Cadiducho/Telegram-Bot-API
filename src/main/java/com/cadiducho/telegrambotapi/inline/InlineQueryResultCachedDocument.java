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
public class InlineQueryResultCachedDocument extends InlineQueryResult {

    /**
     * Title of the result
     */
    private String title;

    /**
     * A valid file identifier for the file
     */
    @Json(name = "document_file_id") private String documentFileId;
    
    /**
     * Optional. Caption, 0-200 characters
     */
    private String caption;
    
    /**
     * Optional. Short description of the result
     */
    private String description;
    

    public InlineQueryResultCachedDocument() {
        super("document");
    }

    /**
     *
     * @param documentFileId A valid file identifier for the file
     * @param title Title for the result
     */
    public InlineQueryResultCachedDocument(String documentFileId, String title) {
        this();
        this.documentFileId = documentFileId;
        this.title = title;
    }

    /**
     *
     * @param documentFileId A valid file identifier for the file
     * @param title Title for the result
     * @param caption Optional. Caption of the document to be sent, 0-200 characters
     * @param description Optional. Short description of the result
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultCachedDocument(String documentFileId, String title, String caption, String description,
                                            InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.documentFileId = documentFileId;
        this.title = title;
        this.caption = caption;
        this.description = description;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
