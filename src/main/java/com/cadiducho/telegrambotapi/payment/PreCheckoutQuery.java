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
 * This object contains information about an incoming pre-checkout query.
 */
@ToString
@Getter @Setter
public class PreCheckoutQuery {
    
    /**
     * Unique query identifier
     */
    private String id;
    
    /**
     * User who sent the query
     */
    private User from;
    
    /**
     * Three-letter ISO 4217 currency code
     */
    private String currency;
    
    /**
     * Total price in the smallest units of the currency (integer, not float/double).
     * For example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in https://core.telegram.org/bots/payments/currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @Json(name = "total_amount") private Integer totalAmount;
    
    /**
     * Bot specified invoice payload
     */
    @Json(name = "invoice_payload") private String invoicePayload;
    
    /**
     * Optional. Identifier of the shipping option chosen by the user
     */
    @Json(name = "shipping_option_id") private String shippingOptionId;
    
    /**
     * Optional. Order info provided by the user
     */
    @Json(name = "order_info") private OrderInfo orderInfo;
    
}
