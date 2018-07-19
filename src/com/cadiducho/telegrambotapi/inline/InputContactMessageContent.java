/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

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
    private String phone_number;
    
    /**
     * Contact's first name
     */
    private String first_name;
    
    /**
     * Optional. Contact's last name
     */
    private String last_name;
}
