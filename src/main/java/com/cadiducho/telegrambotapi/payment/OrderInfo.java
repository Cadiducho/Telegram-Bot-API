/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents information about an order.
 */
@ToString
@Getter @Setter
public class OrderInfo {
    
    /**
     * Optional. User name
     */
    private String name;
    
    /**
     * Optional. User's phone number
     */
    private String phone_number;
    
    /**
     * Optional. User email
     */
    private String email;
    
    /**
     * Optional. User shipping address
     */
    private ShippingAddress shipping_address;
    
}
