package com.cadiducho.telegrambotapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This object represents the content of a media message to be sent.
 * It should be one of
 * <ul>
 * <li>{@link InputMediaPhoto}</li>
 * <li>{@link InputMediaVideo}</li>
 * </ul>
 */
@Getter @Setter
@AllArgsConstructor
public abstract class InputMedia {

    /**
     * Type of the result, must be photo
     */
    protected String type;

    /**
     * File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended), pass an HTTP URL for Telegram to get a file from the Internet.
     */
    protected String media;

    /**
     * Optional. Caption of the photo to be sent, 0-200 characters
     */
    protected String caption;

    /**
     * Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     */
    protected String parse_mode;
}
