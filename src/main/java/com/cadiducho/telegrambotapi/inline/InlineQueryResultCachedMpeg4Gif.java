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
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound) stored on the Telegram servers. By default,
 * this animated MPEG-4 file will be sent by the user with optional caption. Alternatively,
 * you can provide message_text to send it instead of the animation.
 */
@ToString
@Getter @Setter
public class InlineQueryResultCachedMpeg4Gif extends InlineQueryResult {

    /**
     * A valid file identifier for the MP4 file
     */
    @Json(name = "mpeg4_file_id") private String mpeg4FileId;

    /**
     * Optional. Title for the result
     */
    private String title;

    /**
     * Optional. Caption of the MPEG-4 file to be sent, 0-200 characters
     */
    private String caption;

    public InlineQueryResultCachedMpeg4Gif() {
        super("mpeg4_gif");
    }

    /**
     *
     * @param mpeg4FileId A valid file identifier for the MP4 file
     */
    public InlineQueryResultCachedMpeg4Gif(String mpeg4FileId) {
        this();
        this.mpeg4FileId = mpeg4FileId;
    }

    /**
     *
     * @param mpeg4FileId A valid URL for the MP4 file. File size must not exceed 1MB
     * @param title Optional. Title for the result
     * @param caption Optional. Caption of the MPEG-4 file to be sent, 0-200 characters
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultCachedMpeg4Gif(String mpeg4FileId, String title, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.mpeg4FileId = mpeg4FileId;
        this.title = title;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
