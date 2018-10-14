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
 * Represents a link to a video file stored on the Telegram servers.
 * By default, this video file will be sent by the user with an optional caption.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the video.
 */
@ToString
@Getter @Setter
public class InlineQueryResultCachedVideo extends InlineQueryResult {

    /**
     * A valid file identifier for the file
     */
    @Json(name = "video_file_id") private String videoFileId;

    /**
     * Title for the result
     */
    private String title;

    /**
     * Optional. Short description of the result
     */
    private String description;

    public InlineQueryResultCachedVideo() {
        super("video");
    }

    /**
     *
     * @param videoFileId A valid file identifier for the file
     * @param title Title for the result
     */
    public InlineQueryResultCachedVideo(String videoFileId, String title) {
        this();
        this.videoFileId = videoFileId;
        this.title = title;
    }

    /**
     *
     * @param videoFileId A valid file identifier for the file
     * @param title Title for the result
     * @param description Optional. Short description of the result
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultCachedVideo(String videoFileId, String title, String description, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.videoFileId = videoFileId;
        this.title = title;
        this.description = description;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
