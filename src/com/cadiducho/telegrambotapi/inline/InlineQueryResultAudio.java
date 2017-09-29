/*
 * The MIT License
 *
 * Copyright 2017 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

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
    private String audio_url;

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
    private Integer audio_duration;

    public InlineQueryResultAudio() {
        super("audio");
    }

    /**
     *
     * @param audio_url A valid URL for the audio file
     * @param title Title
     */
    public InlineQueryResultAudio(String audio_url, String title) {
        this();
        this.audio_url = audio_url;
        this.title = title;
    }

    /**
     *
     * @param audio_url A valid URL for the audio file
     * @param title Title
     * @param caption Optional. Caption, 0-200 characters
     * @param performer Optional. Performer
     * @param audio_duration Optional. Audio duration in seconds
     * @param reply_markup Optional. Inline keyboard attached to the message
     * @param input_message_content Optional. Content of the message to be sent
     */
    public InlineQueryResultAudio(String audio_url, String title, String caption, String performer,
                                  Integer audio_duration, InlineKeyboardMarkup reply_markup, InputMessageContent input_message_content) {
        this();
        this.audio_url = audio_url;
        this.title = title;
        this.caption = caption;
        this.performer = performer;
        this.audio_duration = audio_duration;
        this.reply_markup = reply_markup;
        this.input_message_content = input_message_content;
    }
    
}
