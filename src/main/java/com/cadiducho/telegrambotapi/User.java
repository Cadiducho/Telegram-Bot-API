/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
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
    @Json(name = "is_bot") private boolean isBot;
    
    /**
     * User‘s or bot’s first name
     */
    @Json(name = "first_name") private String firstName;
    
    /**
     * Optional. User‘s or bot’s last name
     */
    @Json(name = "last_name") private String lastName;
    
    /**
     * Optional. User‘s or bot’s username
     */
    private String username;
    
    /**
     * Optional. IETF language tag of the user's language
     */
    @Json(name = "language_code") private String languageCode;

    /**
     * Constructor used by Chat
     * @param id The id of the user
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param username His username
     */
    protected User(Integer id, String firstName, String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}
