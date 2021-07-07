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
 * This object represents a video file.
 */
@ToString
@Getter @Setter
public class Video {
    
    /**
     * Unique identifier for this file
     */
    @Json(name = "file_id") private String fileId;

    /**
     * Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @Json(name = "file_unique_id") private String fileUniqueId;
    
    /**
     * Video width as defined by sender
     */
    private Integer width;
    
    /**
     * Video height as defined by sender
     */
    private Integer height;
    
    /**
     * Duration of the video in seconds as defined by sender
     */
    private Integer duration;
    
    /**
     * Optional. Video thumbnail
     */
    private PhotoSize thumb;

    /**
     * Optional. Original filename as defined by sender
     */
    @Json(name = "file_name") private String fileName;
    
    /**
     * Optional. Mime type of a file as defined by sender
     */
    @Json(name = "mime_type") private String mimeType;
    
    /**
     * Optional. File size
     */
    @Json(name = "file_size") Integer fileSize;

}
