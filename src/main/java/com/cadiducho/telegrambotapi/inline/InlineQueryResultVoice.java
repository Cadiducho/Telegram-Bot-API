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
 * Represents a link to a voice recording in an .ogg container encoded with OPUS.
 * By default, this voice recording will be sent by the user.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the the voice message.
 */
@ToString
@Getter @Setter
public class InlineQueryResultVoice extends InlineQueryResult {

    /**
     * 	A valid URL for the voice recording
     */
    @Json(name = "voice_url") private String voiceUrl;

    /**
     * Recording title
     */
    private String title;

    /**
     * Optional. Caption, 0-200 characters
     */
    private String caption;

    /**
     * Optional. Recording duration in seconds
     */
    @Json(name = "voice_duration") private Integer voiceDuration;

    public InlineQueryResultVoice() {
        super("voice");
    }

    /**
     *
     * @param voiceUrl A valid URL for the voice recording
     * @param title Title
     */
    public InlineQueryResultVoice(String voiceUrl, String title) {
        this();
        this.voiceUrl = voiceUrl;
        this.title = title;
    }

    /**
     *
     * @param voiceUrl A valid URL for the voice recording
     * @param title Title
     * @param caption Optional. Caption, 0-200 characters
     * @param voiceDuration Optional. Recording duration in seconds
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultVoice(String voiceUrl, String title, String caption, Integer voiceDuration,
                                    InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.voiceUrl = voiceUrl;
        this.title = title;
        this.caption = caption;
        this.voiceDuration = voiceDuration;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
