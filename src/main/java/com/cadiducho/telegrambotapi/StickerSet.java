/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import java.util.List;

import com.squareup.moshi.Json;
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
    @Json(name  = "contains_masks") private Boolean containsMasks;
    
    /**
     * List of all set stickers
     */
    private List<Sticker> stickers;
    
}
