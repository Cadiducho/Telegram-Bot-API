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
 * This object contains basic information about a successful payment.
 */
@ToString
@Getter @Setter
public class SuccessfulPayment {
    
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
    
    /**
     * Telegram payment identifier
     */
    @Json(name = "telegram_payment_charge_id") private String telegramPaymentChargeId;
    
    /**
     * Provider payment identifier
     */
    @Json(name = "provider_payment_charge_id") private String providerPaymentChargeId;
    
}
