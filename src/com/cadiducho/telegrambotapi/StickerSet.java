/*
 * The MIT License
 *
 * Copyright 2017 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a sticker set.
 */
@ToString
@Getter @Setter
public class StickerSet {
    
    /**
     * Sticker set name
     */
    private String name;
    
    /**
     * Sticker set title
     */
    private String title;
    
    /**
     * True, if the sticker set contains masks
     */
    private Boolean contains_masks;
    
    /**
     * List of all set stickers
     */
    private List<Sticker> stickers;
    
}
