/*
 * The MIT License
 *
 * Copyright 2017 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a general file (as opposed to photos, voice messages and audio files).
 */
@ToString
@Getter @Setter
public class Document {

    /**
     * Unique file identifier
     */
    private String file_id;
    
    /**
     * Optional. Document thumbnail as defined by sender
     */
    private PhotoSize thumb;
    
    /**
     * Optional. Original filename as defined by sender
     */
    private String file_name;
    
    /**
     * Optional. MIME type of the file as defined by sender
     */
    private String mime_type;
    
    /**
     * Optional. File size
     */
    private Integer file_size;
}
