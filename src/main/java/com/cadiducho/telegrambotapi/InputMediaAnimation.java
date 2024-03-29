package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an animation file (GIF or H.264/MPEG-4 AVC video without sound) to be sent.
 */
@Getter
@Setter
public class InputMediaAnimation extends InputMedia {

    /**
     * Optional. Thumbnail of the file sent. The thumbnail should be in JPEG format and less than 200 kB in size.
     * A thumbnail‘s width and height should not exceed 90.
     * Ignored if the file is not uploaded using multipart/form-data.
     * Thumbnails can’t be reused and can be only uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using multipart/form-data under <file_attach_name>.
     */
    private Object thumbnail;

    /**
     * Optional. Animation width
     */
    private Integer width;

    /**
     * Optional. Animation height
     */
    private Integer height;

    /**
     * Optional. Animation duration
     */
    private Integer duration;

    /**
     * Optional. Pass True if the photo needs to be covered with a spoiler animation
     */
    @Json(name = "has_spoiler") private Boolean hasSpoiler;

    public InputMediaAnimation(String media, String caption, ParseMode parseMode, Boolean disableContentTypeDetection, Boolean hasSpoiler) {
        super("animation", media, caption, parseMode, disableContentTypeDetection);
        this.hasSpoiler = hasSpoiler;
    }
}
