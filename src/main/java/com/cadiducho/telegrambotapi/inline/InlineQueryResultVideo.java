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
 * Represents a link to a video file stored on the Telegram servers.
 * By default, this video file will be sent by the user with an optional caption.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the video.
 */
@ToString
@Getter @Setter
public class InlineQueryResultVideo extends InlineQueryResult {

    /**
     * A valid URL for the embedded video player or video file
     */
    @Json(name = "video_url") private String videoUrl;

    /**
     * Mime type of the content of video url, “text/html” or “video/mp4”
     */
    @Json(name = "mime_type") private String mimeType;

    /**
     * Text of the message to be sent with the video, 1-512 characters
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
     * Optional. Video width
     */
    @Json(name = "video_width") private Integer videoWidth;

    /**
     * Optional. Video height
     */
    @Json(name = "video_height") private Integer videoHeight;

    /**
     * Optional. Video duration in seconds
     */
    @Json(name = "video_duration") private Integer videoDuration;

    /**
     * URL of the thumbnail (jpeg only) for the video
     */
    @Json(name = "thumb_url") private String thumbUrl;

    /**
     * Title for the result
     */
    private String title;

    /**
     * Optional. Short description of the result
     */
    private String description;

    public InlineQueryResultVideo() {
        super("video");
    }

    /**
     *
     * @param videoUrl A valid URL for the embedded video player or video file
     * @param mimeType Mime type of the content of video url, “text/html” or “video/mp4”
     * @param messageText Text of the message to be sent with the video, 1-512 characters
     * @param thumbUrl URL of the thumbnail (jpeg only) for the video
     * @param title Title for the result
     */
    public InlineQueryResultVideo(String videoUrl, String mimeType, String messageText, String thumbUrl, String title) {
        this();
        this.videoUrl = videoUrl;
        this.mimeType = mimeType;
        this.messageText = messageText;
        this.thumbUrl = thumbUrl;
        this.title = title;
    }

    /**
     *
     * @param videoUrl A valid URL for the embedded video player or video file
     * @param mimeType Mime type of the content of video url, “text/html” or “video/mp4”
     * @param messageText Text of the message to be sent with the video, 1-512 characters
     * @param parseMode Optional. Send “Markdown”, if you want Telegram apps to show
     *                   <a href="https://core.telegram.org/bots/api#using-markdown">bold, italic and inline URLs</a>
     *                   in your bot's message.
     * @param disableWebPagePreview Optional. Disables link previews for links in the sent message
     * @param videoWidth Optional. Video width
     * @param videoHeight Optional. Video height
     * @param videoDuration Optional. Video duration in seconds
     * @param thumbUrl URL of the thumbnail (jpeg only) for the video
     * @param title Title for the result
     * @param description Optional. Short description of the result
     * @param replyMarkup Optional. Inline keyboard attached to the message
     * @param inputMessageContent Optional. Content of the message
     */
    public InlineQueryResultVideo(String videoUrl, String mimeType, String messageText, ParseMode parseMode,
                                  Boolean disableWebPagePreview, Integer videoWidth, Integer videoHeight,
                                  Integer videoDuration, String thumbUrl, String title, String description,
                                  InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this();
        this.videoUrl = videoUrl;
        this.mimeType = mimeType;
        this.messageText = messageText;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
        this.videoWidth = videoWidth;
        this.videoHeight = videoHeight;
        this.videoDuration = videoDuration;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.description = description;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }
    
}
