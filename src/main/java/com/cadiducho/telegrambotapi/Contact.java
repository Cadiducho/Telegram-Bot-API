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

/**
 * This object represents a phone contact.
 */
@ToString
@Getter @Setter
public class Contact {
    
    /**
     * Contact's phone number
     */
    @Json(name = "phone_number") private String phoneNumber;
    
    /**
     * Contact's first name
     */
    @Json(name = "first_name") private String firstName;
    
    /**
     * Optional. Contact's last name
     */
    @Json(name = "last_name") private String lastName;
    
    /**
     * Optional. Contact's user identifier in Telegram
     */
    @Json(name = "user_id") private Long userId;

    /**
     * Optional. Additional data about the contact in the form of a vCard
     */
    private String vcard;
}
