/*
 * The MIT License
 *
 * Copyright 2017 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Telegram User or Telegram Bot
 */
@ToString
@Getter @Setter
public class User {
    
    /**
     * Unique identifier for this user or bot
     */
    private Integer id;
    
    /**
     * True, if this user is a bot
     */
    private Boolean is_bot;
    
    /**
     * User‘s or bot’s first name
     */
    private String first_name;
    
    /**
     * Optional. User‘s or bot’s last name
     */
    private String last_name;
    
    /**
     * Optional. User‘s or bot’s username
     */
    private String username;
    
    /**
     * Optional. IETF language tag of the user's language
     */
    private String language_code;
    
}
