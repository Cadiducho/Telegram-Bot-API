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
 * This object represents a general file (as opposed to photos, voice messages and audio files).
 */
@ToString
@Getter @Setter
public class Document {

    /**
     * Unique file identifier
     */
    @Json(name = "file_id") private String fileId;

    /**
     * Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @Json(name = "file_unique_id") private String fileUniqueId;
    
    /**
     * Optional. Document thumbnail as defined by sender
     */
    private PhotoSize thumb;
    
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
}
