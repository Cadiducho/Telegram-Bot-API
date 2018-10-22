package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a photo to be sent.
 */
@Getter @Setter
public class InputMediaPhoto extends InputMedia {

    public InputMediaPhoto(String media, String caption, String parseMode) {
        super("photo", media, caption, parseMode);
    }
}
