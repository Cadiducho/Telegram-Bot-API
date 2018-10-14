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
 * Represents a link to an mp3 audio file.
 * By default, this audio file will be sent by the user.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the audio.
 */
@ToString
@Getter @Setter
public class InlineQueryResultAudio extends InlineQueryResult {

    /**
     * 	A valid URL for the audio file
     */
    @Json(name = "audio_url") private String audioUrl;

    /**
     * Title
     */
    private String title;

    /**
     * Optional. Caption, 0-200 characters
     */
    private String caption;

    /**
     * Optional. Performer
     */
    private String performer;

    /**
     * Optional. Audio duration in seconds
     */
    @Json(name = "audio_duration") private Integer audioDuration;

    public InlineQueryResultAudio() {
        super("audio");
    }

    /**
     *
     * @param audioUrl A valid URL for the audio file
     * @param title Title
     */
    public InlineQueryResultAudio(String audioUrl, String title) {
        this();
        this.audioUrl = audioUrl;
        this.title = title;
    }

    /**
     *
     * @param audioUrl A valid URL for the audio file
     * @param title Title
     * @param caption Optional. Caption, 0-200 characters
     * @param performer Optional. Performer
     * @param audioDuration Optional. Audio duration in seconds
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message to be sent
     */
    public InlineQueryResultAudio(String audioUrl, String title, String caption, String performer,
                                  Integer audioDuration, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.audioUrl = audioUrl;
        this.title = title;
        this.caption = caption;
        this.performer = performer;
        this.audioDuration = audioDuration;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
