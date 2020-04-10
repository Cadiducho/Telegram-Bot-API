/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

import com.cadiducho.telegrambotapi.ParseMode;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a link to an animated GIF file. By default, this animated GIF file will be sent by the user with optional
 * caption. Alternatively, you can provide <i>message_text</i> to send it instead of the animation.
 */
@ToString
@Getter @Setter
public class InlineQueryResultGif extends InlineQueryResult {
    
    /**
     * A valid URL for the GIF file. File size must not exceed 1MB
     */
    @Json(name = "gif_url") private String gifUrl;

    /**
     * Optional. Width of the GIF
     */
    @Json(name = "gif_width") private Integer gifWidth;

    /**
     * Optional. Height of the GIF
     */
    @Json(name = "gif_height")  private Integer gifHeight;
    
    /**
     * Optional. Duration of the GIF
     */
    @Json(name = "gif_duration") private Integer gifDuration;

    /**
     * URL of the static thumbnail for the result (jpeg or gif)
     */
    @Json(name = "thumb_url") private String thumbUrl;

    /**
     * Optional. Title for the result
     */
    private String title;

    /**
     * Optional. Caption of the GIF file to be sent, 0-200 characters
     */
    private String caption;

    /**
     * Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     */
    @Json(name = "parse_mode") private ParseMode parseMode;

    public InlineQueryResultGif() {
        super("gif");
    }

    /**
     *
     * @param gifUrl A valid URL for the GIF file. File size must not exceed 1MB
     * @param thumbUrl URL of the static thumbnail for the result (jpeg or gif)
     */
    public InlineQueryResultGif(String gifUrl, String thumbUrl) {
        this();
        this.gifUrl = gifUrl;
        this.thumbUrl = thumbUrl;
    }

    /**
     *
     * @param gifUrl A valid URL for the GIF file. File size must not exceed 1MB
     * @param gifWidth Optional. Width of the GIF
     * @param gifHeight Optional. Height of the GIF
     * @param thumbUrl URL of the static thumbnail for the result (jpeg or gif)
     * @param title Optional. Title for the result
     * @param caption Optional. Caption of the GIF file to be sent, 0-200 characters
     * @param parseMode Optional. Send “Markdown”, if you want Telegram apps to show
     *                   <a href="https://core.telegram.org/bots/api#using-markdown">bold, italic and inline URLs</a>
     *                   in your bot's message.
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultGif(String gifUrl, Integer gifWidth, Integer gifHeight, String thumbUrl, String title,
                                String caption, ParseMode parseMode, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.gifUrl = gifUrl;
        this.gifWidth = gifWidth;
        this.gifHeight = gifHeight;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.caption = caption;
        this.parseMode = parseMode;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
