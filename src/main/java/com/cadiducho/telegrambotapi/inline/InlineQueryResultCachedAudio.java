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
 * Represents a link to an mp3 audio file stored on the Telegram servers.
 * By default, this audio file will be sent by the user. 
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the audio.
 */
@ToString
@Getter @Setter
public class InlineQueryResultCachedAudio extends InlineQueryResult {
    
    /**
     * A valid file identifier for the audio file
     */
    @Json(name = "audio_file_id") private String audioFileId;

    /**
     * Optional. Caption, 0-200 characters
     */
    private String caption;

    public InlineQueryResultCachedAudio() {
        super("audio");
    }

    /**
     *
     * @param audioFileId A valid file identifier for the audio file
     */
    public InlineQueryResultCachedAudio(String audioFileId) {
        this();
        this.audioFileId = audioFileId;
    }

    /**
     *
     * @param audioFileId A valid file identifier for the audio file
     * @param caption Optional. Caption, 0-200 characters
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultCachedAudio(String audioFileId, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.audioFileId = audioFileId;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
