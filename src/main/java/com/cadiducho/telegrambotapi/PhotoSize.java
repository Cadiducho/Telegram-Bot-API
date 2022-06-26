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
 * This object represents one size of a photo or a file / sticker thumbnail.
 */
@ToString
@Getter @Setter
public class PhotoSize {

    /**
     * Unique identifier for this file
     */
    @Json(name = "file_id") private String fileId;

    /**
     * Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @Json(name = "file_unique_id") private String fileUniqueId;

    /**
     * Photo width
     */
    private Integer width;
    
    /**
     * Photo height
     */
    private Integer height;
    
    /**
     * Optional. File size
     */
    @Json(name = "file_size") private Long fileSize;
}
