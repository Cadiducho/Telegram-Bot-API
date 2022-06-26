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
 * This object represents a sticker.
 */
@ToString
@Getter @Setter
public class Sticker {
    
    /**
     * Unique identifier for this file
     */
    @Json(name = "file_id") private String fileId;

    /**
     * Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @Json(name = "file_unique_id") private String fileUniqueId;
    
    /**
     * Sticker width
     */
    private Integer width;
    
    /**
     * Sticker height
     */
    private Integer height;
    
    /**
     * True, if the sticker is animated. 
     * See <a href="https://telegram.org/blog/animated-stickers">https://telegram.org/blog/animated-stickers</a>
     */
    @Json(name = "is_animated") private Boolean isAnimated;
    
    /**
     * Optional. Sticker thumbnail in .webp or .jpg format
     */
    private PhotoSize thumb;
    
    /**
     * Optional. Emoji associated with the sticker
     */
    private String emoji;
    
    /**
     * Optional. Name of the sticker set to which the sticker belongs
     */
    @Json(name = "set_name") private String setName;
    
    /**
     * Optional. For mask stickers, the position where the mask should be placed
     */
    @Json(name = "mask_position") private MaskPosition maskPosition;
    
    /**
     * Optional. File size
     */
    @Json(name = "file_size") private Long fileSize;

}
