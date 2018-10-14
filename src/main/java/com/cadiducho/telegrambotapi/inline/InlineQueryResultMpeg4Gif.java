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
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound). By default,
 * this animated MPEG-4 file will be sent by the user with optional caption. Alternatively,
 * you can provide message_text to send it instead of the animation.
 */
@ToString
@Getter @Setter
public class InlineQueryResultMpeg4Gif extends InlineQueryResult {

    /**
     * A valid URL for the MP4 file. File size must not exceed 1MB
     */
    @Json(name = "mpeg4_url") private String mpeg4Url;

    /**
     * Optional. Video width
     */
    @Json(name = "mpeg4_width") private Integer mpeg4Width;

    /**
     * Optional. Video height
     */
    @Json(name = "mpeg4_height") private Integer mpeg4Height;
    
    /**
     * Optional. Video duration
     */
    @Json(name = "mpeg4_duration") private Integer mpeg4Duration;

    /**
     * URL of the static thumbnail (jpeg or gif) for the result
     */
    @Json(name = "thumb_url") private String thumbUrl;

    /**
     * Optional. Title for the result
     */
    private String title;

    /**
     * Optional. Caption of the MPEG-4 file to be sent, 0-200 characters
     */
    private String caption;

    /**
     * Optional. Send “Markdown”, if you want Telegram apps to show
     * <a href="https://core.telegram.org/bots/api#using-markdown">bold, italic and inline URLs</a> in your bot's message.
     */
    @Json(name = "parse_mode") private String parseMode;

    public InlineQueryResultMpeg4Gif() {
        super("mpeg4_gif");
    }

    /**
     *
     * @param mpeg4Url A valid URL for the MP4 file. File size must not exceed 1MB
     * @param thumbUrl URL of the static thumbnail (jpeg or gif) for the result
     */
    public InlineQueryResultMpeg4Gif(String mpeg4Url, String thumbUrl) {
        this();
        this.mpeg4Url = mpeg4Url;
        this.thumbUrl = thumbUrl;
    }

    /**
     *
     * @param mpeg4Url A valid URL for the MP4 file. File size must not exceed 1MB
     * @param thumbUrl URL of the static thumbnail (jpeg or gif) for the result
     * @param mpeg4Width Optional. Video width
     * @param mpeg4Height Optional. Video height
     * @param title Optional. Title for the result
     * @param caption Optional. Caption of the MPEG-4 file to be sent, 0-200 characters
     * @param parseMode Optional. Send “Markdown”, if you want Telegram apps to show
     *                   <a href="https://core.telegram.org/bots/api#using-markdown">bold, italic and inline URLs</a>
     *                   in your bot's message.
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultMpeg4Gif(String mpeg4Url, String thumbUrl, Integer mpeg4Width, Integer mpeg4Height,
                                     String title, String caption, String parseMode,
                                     InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.mpeg4Url = mpeg4Url;
        this.mpeg4Width = mpeg4Width;
        this.mpeg4Height = mpeg4Height;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.caption = caption;
        this.parseMode = parseMode;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
