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
 * This object represents a file ready to be downloaded. 
 * The file can be downloaded via the link https://api.telegram.org/file/bot&lt;token&gt;/&lt;file_path&gt;. 
 * It is guaranteed that the link will be valid for at least 1 hour. 
 * When the link expires, a new one can be requested by calling getFile.
 */
@ToString
@Getter @Setter
public class File {

    /**
     * Unique identifier for this file
     */
    @Json(name = "file_id") private String fileId;
    
    /**
     * Optional. File size, if known
     */
    @Json(name = "file_size") private Integer fileSize;
    
    /**
     * Optional. File path. Use https://api.telegram.org/file/bot<token>/<file_path> to get the file.
     */
    @Json(name = "file_path") private String filePath;
}
