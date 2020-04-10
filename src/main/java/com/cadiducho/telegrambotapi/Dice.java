package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a dice with random value from 1 to 6.
 */
@ToString
@Getter @Setter
public class Dice {

    /**
     * Unique identifier for this file
     */
    @Json(name = "file_id") private String fileId;

    /**
     * Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @Json(name = "file_unique_id") private String fileUniqueId;

    /**
     * Optional. File size, if known
     */
    @Json(name = "file_size") private Integer fileSize;

    /**
     * Optional. File path. Use https://api.telegram.org/file/bot<token>/<file_path> to get the file.
     */
    @Json(name = "file_path") private String filePath;
}
