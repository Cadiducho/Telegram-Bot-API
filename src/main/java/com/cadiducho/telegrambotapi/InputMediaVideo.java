package com.cadiducho.telegrambotapi;

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
    private Boolean supports_streaming;

    public InputMediaVideo(String type, String media, String caption, String parse_mode, Integer width, Integer height, Integer duration, Boolean supports_streaming) {
        super(type, media, caption, parse_mode);
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.supports_streaming = supports_streaming;
    }

}
