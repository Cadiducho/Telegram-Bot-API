package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a photo to be sent.
 */
@Getter @Setter
public class InputMediaPhoto extends InputMedia {

    /**
     * Optional. Pass True if the photo needs to be covered with a spoiler animation
     */
    @Json(name = "has_spoiler") private Boolean hasSpoiler;

    public InputMediaPhoto(String media, String caption, ParseMode parseMode, Boolean disableContentTypeDetection, Boolean has_spoiler) {
        super("photo", media, caption, parseMode, disableContentTypeDetection);
        this.hasSpoiler = hasSpoiler;
    }
}
