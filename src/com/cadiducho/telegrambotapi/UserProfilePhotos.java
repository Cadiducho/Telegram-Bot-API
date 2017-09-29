/*
 * The MIT License
 *
 * Copyright 2017 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represent a user's profile pictures.
 */
@ToString
@Getter @Setter
public class UserProfilePhotos {
    
    /**
     * Total number of profile pictures the target user has
     */
    private Integer total_count;
    
    /**
     * Requested profile pictures (in up to 4 sizes each)
     */
    private List<List<PhotoSize>> photos;

}
