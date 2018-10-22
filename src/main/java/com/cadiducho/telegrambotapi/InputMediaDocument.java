package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a general file to be sent.
 */
@Getter
@Setter
public class InputMediaDocument extends InputMedia {

    /**
     * Optional. Thumbnail of the file sent. The thumbnail should be in JPEG format and less than 200 kB in size.
     * A thumbnail‘s width and height should not exceed 90.
     * Ignored if the file is not uploaded using multipart/form-data.
     * Thumbnails can’t be reused and can be only uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using multipart/form-data under <file_attach_name>.
     */
    private Object thumb;

    public InputMediaDocument(String media,Object thumb, String caption, String parseMode) {
        super("document", media, caption, parseMode);
        this.thumb = thumb;
    }
}
