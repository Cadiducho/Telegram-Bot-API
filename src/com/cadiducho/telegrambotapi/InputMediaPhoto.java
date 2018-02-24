package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a photo to be sent.
 */
@Getter @Setter
public class InputMediaPhoto extends InputMedia {

    public InputMediaPhoto(String type, String media, String caption, String parse_mode) {
        super(type, media, caption, parse_mode);
    }
}
