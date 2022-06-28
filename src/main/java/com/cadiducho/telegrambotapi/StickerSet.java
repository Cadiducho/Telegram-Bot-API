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
     * True, if the sticker set contains animated stickers
     * See <a href="https://telegram.org/blog/animated-stickers">https://telegram.org/blog/animated-stickers</a>
     */
    @Json(name = "is_animated") private Boolean isAnimated;

    /**
     * True, if the sticker set contains
     * See <a href="https://telegram.org/blog/video-stickers-better-reactions">https://telegram.org/blog/video-stickers-better-reactions</a>
     */
    @Json(name = "is_video") private Boolean isVideo;
    
    /**
     * True, if the sticker set contains masks
     */
    @Json(name  = "contains_masks") private Boolean containsMasks;
    
    /**
     * List of all set stickers
     */
    private List<Sticker> stickers;

    /**
     * Optional. Sticker set thumbnail in the .WEBP or .TGS format
     */
    private PhotoSize thumb;
    
}
