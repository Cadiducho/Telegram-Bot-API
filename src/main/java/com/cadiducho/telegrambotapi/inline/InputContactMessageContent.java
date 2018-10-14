/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the content of a contact message to be sent as the result of an inline query.
 */
@ToString
@Getter @Setter
public class InputContactMessageContent extends InputMessageContent {
    
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
}
