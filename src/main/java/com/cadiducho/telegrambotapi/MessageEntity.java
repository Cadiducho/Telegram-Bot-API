/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
 */
@ToString
@Getter @Setter
public class MessageEntity {
    
    /**
     * 	Type of the entity.
     * 	Can be “mention” (@username), “hashtag” (#hashtag), “cashtag” ($USD), “bot_command” (/start@jobs_bot),
     * 	“url” (https://telegram.org), “email” (do-not-reply@telegram.org), “phone_number” (+1-212-555-0123),
     * 	“bold” (bold text), “italic” (italic text), “underline” (underlined text), “strikethrough” (strikethrough text),
     * 	“code” (monowidth string), “pre” (monowidth block), “text_link” (for clickable text URLs),
     * 	“text_mention” (for users without usernames)
     */
    private String type;
    
    /**
     * Offset in UTF-16 code units to the start of the entity
     */
    private Integer offset;
    
    /**
     * Length of the entity in UTF-16 code units
     */
    private Integer length;
    
    /**
     * Optional. For “text_link” only, url that will be opened after user taps on the text
     */
    private String url;
    
    /**
     * Optional. For “text_mention” only, the mentioned user
     */
    private User user;

    /**
     * Optional. For “pre” only, the programming language of the entity text
     */
    private String language;
    
}
