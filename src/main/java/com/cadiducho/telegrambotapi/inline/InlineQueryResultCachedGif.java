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
 * Represents a link to an animated GIF file stored on the Telegram servers. By default, this animated GIF file will be sent by the user with optional
 * caption. Alternatively, you can provide <i>message_text</i> to send it instead of the animation.
 */
@ToString
@Getter @Setter
public class InlineQueryResultCachedGif extends InlineQueryResult {
    
    /**
     * A valid URL for the GIF file. File size must not exceed 1MB
     */
    @Json(name = "gif_file_id") private String gifFileId;

    /**
     * Optional. Title for the result
     */
    private String title;

    /**
     * Optional. Caption of the GIF file to be sent, 0-200 characters
     */
    private String caption;

    public InlineQueryResultCachedGif() {
        super("gif");
    }

    /**
     *
     * @param gifFileId A valid file identifier for the GIF file
     */
    public InlineQueryResultCachedGif(String gifFileId) {
        this();
        this.gifFileId = gifFileId;
    }

    /**
     *
     * @param gifFileId A valid file identifier for the GIF file
     * @param title Optional. Title for the result
     * @param caption Optional. Caption of the GIF file to be sent, 0-200 characters
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultCachedGif(String gifFileId,String title, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.gifFileId = gifFileId;
        this.title = title;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
