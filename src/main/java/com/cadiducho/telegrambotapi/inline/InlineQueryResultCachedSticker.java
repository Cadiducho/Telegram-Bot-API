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
 * Represents a link to a sticker stored on the Telegram servers.
 * By default, this sticker will be sent by the user.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the sticker.
 */
@ToString
@Getter @Setter
public class InlineQueryResultCachedSticker extends InlineQueryResult {
    
    /**
     * A valid file identifier of the sticker
     */
    @Json(name  = "sticker_file_id") private String stickerFileId;

    public InlineQueryResultCachedSticker() {
        super("sticker");
    }

    /**
     *
     * @param stickerFileId A valid file identifier of the sticker
     */
    public InlineQueryResultCachedSticker(String stickerFileId) {
        this();
        this.stickerFileId = stickerFileId;
    }
    
    /**
     *
     * @param stickerFileId A valid file identifier of the sticker
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultCachedSticker(String stickerFileId, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.stickerFileId = stickerFileId;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
}
