/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.game;

import com.cadiducho.telegrambotapi.PhotoSize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * You can provide an animation for your {@link Game} so that it looks stylish in chats (check out Lumberjack for an example). 
 * This object represents an animation file to be displayed in the message containing a {@link Game}.
 */
@ToString
@Getter @Setter
public class Animation {

    /**
     * Title of the game
     */
    private String file_id;
    
    /**
     * Optional. Animation thumbnail as defined by sender
     */
    private PhotoSize thumb;
    
    /**
     * Optional. Original animation filename as defined by sender
     */
    private String file_name;
    
    /**
     * Optional. MIME type of the file as defined by sender
     */
    private String mime_type;
    
    /**
     * Optional. File size
     */
    private Integer file_size;
}
