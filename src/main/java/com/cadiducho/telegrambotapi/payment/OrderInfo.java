/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.payment;

import com.squareup.moshi.Json;
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
    @Json(name = "phone_number") private String phoneNumber;
    
    /**
     * Optional. User email
     */
    private String email;
    
    /**
     * Optional. User shipping address
     */
    @Json(name = "shipping_address") private ShippingAddress shippingAddress;
    
}
