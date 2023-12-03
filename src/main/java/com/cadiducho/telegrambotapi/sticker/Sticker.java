/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.sticker;

import com.cadiducho.telegrambotapi.File;
import com.cadiducho.telegrambotapi.PhotoSize;
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
     * True, if the sticker is a video sticker.
     * See <a href="https://telegram.org/blog/video-stickers-better-reactions">https://telegram.org/blog/video-stickers-better-reactions</a>
     */
    @Json(name = "is_video") private Boolean isVideo;

    /**
     * Optional. Sticker thumbnail in .webp or .jpg format
     */
    private PhotoSize thumbnail;
    
    /**
     * Optional. Emoji associated with the sticker
     */
    private String emoji;
    
    /**
     * Optional. Name of the sticker set to which the sticker belongs
     */
    @Json(name = "set_name") private String setName;

    /**
     * Optional. For premium regular stickers, premium animation for the sticker
     */
    @Json(name = "premium_animation") private File premium_Animation;
    
    /**
     * Optional. For mask stickers, the position where the mask should be placed
     */
    @Json(name = "mask_position") private MaskPosition maskPosition;

    /**
     * Optional. For custom emoji stickers, unique identifier of the custom emoji
     */
    @Json(name = "custom_emoji_id") private MaskPosition customEmojiId;

    /**
     * Optional. True, if the sticker must be repainted to a text color in messages, the color of the Telegram Premium badge in emoji status, white color on chat photos, or another appropriate color in other places
     */
    @Json(name = "needs_repainting") private Boolean needsRepainting;
    
    /**
     * Optional. File size
     */
    @Json(name = "file_size") private Long fileSize;

}
