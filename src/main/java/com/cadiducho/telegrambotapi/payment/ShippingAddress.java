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
 * This object represents a shipping address.
 */
@ToString
@Getter @Setter
public class ShippingAddress {
    
    /**
     * ISO 3166-1 alpha-2 country code
     */
    @Json(name = "country_code") private String countryCode;
    
    /**
     * State, if applicable
     */
    private String state;
    
    /**
     * City
     */
    private String city;
    
    /**
     * First line for the address
     */
    @Json(name = "street_line1") private String streetLine1;
    
    /**
     * Second line for the address
     */
    @Json(name = "street_line2") private String streetLine2;
    
    /**
     * Address post code
     */
    @Json(name = "post_code") private String postCode;
    
}
