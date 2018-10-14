/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
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
    @Json(name = "file_id") private String fileId;

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
    @Json(name = "mime_type") private String mimeType;

    /**
     * Optional. File size
     */
    @Json(name = "file_size") private Integer fileSize;
}
