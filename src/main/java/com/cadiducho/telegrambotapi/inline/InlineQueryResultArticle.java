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
 * Represents a link to an article or web page.
 */
@ToString
@Getter @Setter
public class InlineQueryResultArticle extends InlineQueryResult {
    
    /**
     * Title of the result
     */
    private String title;

    /**
     * Text of the message to be sent
     */
    @Json(name = "message_text") private String messageText;

    /**
     * Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     */
    @Json(name = "parse_mode") private ParseMode parseMode;

    /**
     * Optional. Disables link previews for links in the sent message
     */
    @Json(name = "disable_web_page_preview") private Boolean disableWebPagePreview;

    /**
     * Optional. URL of the result
     */   
    private String url;

    /**
     * Optional. Pass True, if you don't want the URL to be shown in the message
     */
    @Json(name = "hide_url") private Boolean hideUrl;

    /**
     * Optional. Short description of the result
     */  
    private String description;

    /**
     * Optional. Url of the thumbnail for the result
     */
    @Json(name = "thumb_url") private String thumbUrl;

    /**
     * Optional. Thumbnail width
     */
    @Json(name = "thumb_width") private Integer thumbWidth;

    /**
     * Optional. Thumbnail height
     */
    @Json(name = "thumb_height") private Integer thumbHeight;

    public InlineQueryResultArticle() {
        super("article");
    }

    /**
     *
     * @param title Title of the result
     * @param messageText Text of the message to be sent
     */
    public InlineQueryResultArticle(String title, String messageText) {
        this();
        this.title = title;
        this.messageText = messageText;
    }

    /**
     *
     * @param title Title of the result
     * @param messageText Text of the message to be sent
     * @param parseMode Optional. Send “Markdown”, if you want Telegram apps to show bold, italic and inline URLs in your bot's message.
     * @param disableWebPagePreview Optional. Disables link previews for links in the sent message
     * @param url Optional. URL of the result
     * @param hideUrl Optional. Pass True, if you don't want the URL to be shown in the message
     * @param description Optional. Short description of the result
     * @param thumbUrl Optional. Url of the thumbnail for the result
     * @param thumbWidth Optional. Thumbnail width
     * @param thumbHeight Optional. Thumbnail height
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message to be sent
     */
    public InlineQueryResultArticle(String title, String messageText, String parseMode,
                                    Boolean disableWebPagePreview, String url, Boolean hideUrl, String description,
                                    String thumbUrl, Integer thumbWidth, Integer thumbHeight, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.title = title;
        this.messageText = messageText;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
        this.url = url;
        this.hideUrl = hideUrl;
        this.description = description;
        this.thumbUrl = thumbUrl;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
