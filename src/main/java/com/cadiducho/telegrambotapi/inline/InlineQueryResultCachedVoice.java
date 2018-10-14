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
 * Represents a link to a voice message stored on the Telegram servers
 * By default, this voice recording will be sent by the user.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the the voice message.
 */
@ToString
@Getter @Setter
public class InlineQueryResultCachedVoice extends InlineQueryResult {

    /**
     * A valid file identifier for the voice message
     */
    @Json(name = "voice_file_id") private String voiceFileId;

    /**
     * Recording title
     */
    private String title;

    /**
     * Optional. Caption, 0-200 characters
     */
    private String caption;

    public InlineQueryResultCachedVoice() {
        super("voice");
    }

    /**
     *
     * @param voiceFileId A valid file identifier for the voice message
     * @param title Title
     */
    public InlineQueryResultCachedVoice(String voiceFileId, String title) {
        this();
        this.voiceFileId = voiceFileId;
        this.title = title;
    }

    /**
     *
     * @param voiceFileId A valid file identifier for the voice message
     * @param title Title
     * @param caption Optional. Caption, 0-200 characters
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultCachedVoice(String voiceFileId, String title, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.voiceFileId = voiceFileId;
        this.title = title;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
