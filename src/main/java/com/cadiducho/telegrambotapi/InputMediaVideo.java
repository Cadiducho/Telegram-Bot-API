package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a video to be sent.
 */
@Getter @Setter
public class InputMediaVideo extends InputMedia {

    /**
     * Optional. Thumbnail of the file sent. The thumbnail should be in JPEG format and less than 200 kB in size.
     * A thumbnail‘s width and height should not exceed 90.
     * Ignored if the file is not uploaded using multipart/form-data.
     * Thumbnails can’t be reused and can be only uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using multipart/form-data under <file_attach_name>.
     */
    private Object thumbnail;

    /**
     * Optional. Video width
     */
    private Integer width;

    /**
     * Optional. Video height
     */
    private Integer height;

    /**
     * Optional. Video duration
     */
    private Integer duration;

    /**
     * Optional. Pass True, if the uploaded video is suitable for streaming
     */
    @Json(name = "supports_streaming") private Boolean supportsStreaming;

    /**
     * Optional. Pass True if the photo needs to be covered with a spoiler animation
     */
    @Json(name = "has_spoiler") private Boolean hasSpoiler;

    public InputMediaVideo(String media, Object thumbnail, String caption, ParseMode parseMode, Integer width, Integer height, Integer duration, Boolean supportsStreaming, Boolean disableContentTypeDetection, Boolean hasSpoiler) {
        super("video", media, caption, parseMode, disableContentTypeDetection);
        this.thumbnail = thumbnail;
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.supportsStreaming = supportsStreaming;
        this.hasSpoiler = hasSpoiler;
    }

}
