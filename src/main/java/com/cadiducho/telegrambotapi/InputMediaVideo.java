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

    public InputMediaVideo(String type, String media, String caption, String parseMode, Integer width, Integer height, Integer duration, Boolean supportsStreaming) {
        super(type, media, caption, parseMode);
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.supportsStreaming = supportsStreaming;
    }

}
