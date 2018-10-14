/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.payment;

import com.cadiducho.telegrambotapi.User;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object contains information about an incoming shipping query.
 */
@ToString
@Getter @Setter
public class ShippingQuery {
    
    /**
     * Unique query identifier
     */
    private String id;
    
    /**
     * User who sent the query
     */
    private User from;
    
    /**
     * Bot specified invoice payload
     */
    @Json(name = "invoice_payload") private String invoicePayload;
    
    /**
     * User specified shipping address
     */
    @Json(name = "shipping_address") private ShippingAddress shippingAddress;
    
}
