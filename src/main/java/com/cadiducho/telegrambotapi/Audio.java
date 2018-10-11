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
 * This object represents an audio file to be treated as music by the https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE clients.
 */
@ToString
@Getter @Setter
public class Audio {

    /**
     * Unique identifier for this file
     */
    private String file_id;

    /**
     * Duration of the audio in seconds as defined by sender
     */
    private Integer duration;
    
    /**
     * Optional. Performer of the audio as defined by sender or by audio tags
     */
    private String performer;
    
    /**
     * Optional. Title of the audio as defined by sender or by audio tags
     */
    private String title;

    /**
     * Optional. MIME type of the file as defined by sender
     */
    private String mime_type;

    /**
     * Optional. File size
     */
    private Integer file_size;
}
