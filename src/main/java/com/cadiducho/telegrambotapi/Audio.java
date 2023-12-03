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
     * Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @Json(name = "file_unique_id") private String fileUniqueId;

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
     * Optional. Original filename as defined by sender
     */
    @Json(name = "file_name") private String fileName;

    /**
     * Optional. MIME type of the file as defined by sender
     */
    @Json(name = "mime_type") private String mimeType;

    /**
     * Optional. File size
     */
    @Json(name = "file_size") private Long fileSize;

    /**
     * Optional. Thumbnail of the album cover to which the music file belongs
     */
    private PhotoSize thumbnail;
}
