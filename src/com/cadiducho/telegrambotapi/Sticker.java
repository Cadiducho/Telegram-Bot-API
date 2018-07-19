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
 * This object represents a sticker.
 */
@ToString
@Getter @Setter
public class Sticker {
    
    /**
     * Unique identifier for this file
     */
    private String file_id;
    
    /**
     * Sticker width
     */
    private Integer width;
    
    /**
     * Sticker height
     */
    private Integer height;
    
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
    private String set_name;
    
    /**
     * Optional. For mask stickers, the position where the mask should be placed
     */
    private MaskPosition mask_position;
    
    /**
     * Optional. File size
     */
    private Integer file_size;

}
