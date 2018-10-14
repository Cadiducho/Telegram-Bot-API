
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
 * Represents the content of a text message to be sent as the result of an inline query.
 */
@ToString
@Getter @Setter
public class InputTextMessageContent extends InputMessageContent {
    
    /**
     * Text of the message to be sent, 1-4096 characters
     */
    @Json(name = "message_text") private String messageText;
    
    /**
     * Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, 
     * fixed-width text or inline URLs in your bot's message.
     */
    @Json(name = "parse_mode") private String parseMode;
    
    /**
     * Optional. Disables link previews for links in the sent message
     */
    @Json(name = "disable_web_page_preview") private Boolean disableWebPagePreview;
}
