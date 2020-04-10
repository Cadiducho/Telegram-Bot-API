package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an audio file to be treated as music to be sent.
 */
@Getter
@Setter
public class InputMediaAudio extends InputMedia {

    /**
     * Optional. Thumbnail of the file sent. The thumbnail should be in JPEG format and less than 200 kB in size.
     * A thumbnail‘s width and height should not exceed 90.
     * Ignored if the file is not uploaded using multipart/form-data.
     * Thumbnails can’t be reused and can be only uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using multipart/form-data under <file_attach_name>.
     */
    private Object thumb;

    /**
     * Optional. Duration of the audio in seconds
     */
    private Integer duration;

    /**
     * 	Optional. Performer of the audio
     */
    private String performer;

    /**
     * Optional. Title of the audio
     */
    private String title;

    public InputMediaAudio(String media,Object thumb, String caption, ParseMode parseMode, Integer duration, String performer, String title) {
        super("audio", media, caption, parseMode);
        this.thumb = thumb;
        this.duration = duration;
        this.performer = performer;
        this.title = title;
    }
}
