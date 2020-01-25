package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This object represents the content of a media message to be sent.
 * It should be one of
 * <ul>
 * <li>{@link InputMediaPhoto}</li>
 * <li>{@link InputMediaVideo}</li>
 * <li>{@link InputMediaAnimation}</li>
 * <li>{@link InputMediaAudio}</li>
 * <li>{@link InputMediaDocument}</li>
 * </ul>
 */
@Getter @Setter
@AllArgsConstructor
public abstract class InputMedia {

    /**
     * Type of the result
     */
    protected String type;

    /**
     * File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended), pass an HTTP URL for Telegram to get a file from the Internet.
     */
    protected String media;

    /**
     * Optional. Caption of the InputMedia to be sent, 0-200 characters
     */
    protected String caption;

    /**
     * Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     */
    @Json(name = "parse_mode") protected ParseMode parseMode;
}
