package com.cadiducho.telegrambotapi.inline;

import com.cadiducho.telegrambotapi.payment.LabeledPrice;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Represents the content of an invoice message to be sent as the result of an inline query.
 */
@ToString
@Getter
@Setter
public class InputInvoiceMessageContent extends InputMessageContent {

    /**
     * Product name, 1-32 characters
     */
    private String title;

    /**
     * Product description, 1-255 characters
     */
    private String description;

    /**
     * Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal processes.
     */
    private String payload;

    /**
     * Payment provider token, obtained via Botfather
     */
    @Json(name = "provider_token") private String providerToken;

    /**
     * Three-letter ISO 4217 currency code, see more on https://core.telegram.org/bots/payments#supported-currencies
     */
    private String currency;

    /**
     * Price breakdown, a JSON-serialized list of components (e.g. product price, tax, discount, delivery cost, delivery tax, bonus, etc.)
     */
    private List<LabeledPrice> prices;

    /**
     * Optional. The maximum accepted amount for tips in the smallest units of the currency (integer, not float/double).
     * For example, for a maximum tip of US$ 1.45 pass max_tip_amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     * Defaults to 0
     */
    @Json(name = "max_tip_amount") private Integer maxTipAmount;

    /**
     * Optional. A JSON-serialized array of suggested amounts of tip in the smallest units of the currency (integer, not float/double). At most 4 suggested tip amounts can be specified.
     * The suggested tip amounts must be positive, passed in a strictly increased order and must not exceed max_tip_amount.
     */
    @Json(name = "suggested_tip_amounts") private List<Integer> suggestedTipAmounts;

    /**
     * Optional. A JSON-serialized object for data about the invoice, which will be shared with the payment provider.
     * A detailed description of the required fields should be provided by the payment provider.
     */
    @Json(name = "provider_data") private String providerData;

    /**
     * Optional. URL of the product photo for the invoice.
     * Can be a photo of the goods or a marketing image for a service. People like it better when they see what they are paying for.
     */
    @Json(name = "photo_url") private String photo_url;

    /**
     * Optional. Photo size
     */
    @Json(name = "photo_size") private Integer photoSize;

    /**
     * Optional. Photo width
     */
    @Json(name = "photo_width") private Integer photoWidth;

    /**
     * Optional. Photo height
     */
    @Json(name = "photo_height") private Integer photoHeight;

    /**
     * Optional. Pass True, if you require the user's full name to complete the order
     */
    @Json(name = "need_name") private Boolean needName;

    /**
     * Optional. Pass True, if you require the user's phone number to complete the order
     */
    @Json(name = "need_phone_number") private Boolean needPhoneNumber;

    /**
     * Optional. Pass True, if you require the user's email address to complete the order
     */
    @Json(name = "need_email") private Boolean needEmail;

    /**
     * Optional. Pass True, if you require the user's shipping address to complete the order
     */
    @Json(name = "need_shipping_address") private Boolean needShippingAddress;

    /**
     * Optional. Pass True, if user's phone number should be sent to provider
     */
    @Json(name = "send_phone_number_to_provider") private Boolean sendPhoneNumberToProvider;

    /**
     * Optional. Pass True, if user's email address should be sent to provider
     */
    @Json(name = "send_email_to_provider") private Boolean sendEmailToProvider;

    /**
     * Optional. Pass True, if the final price depends on the shipping method
     */
    @Json(name = "is_flexible") private Boolean isFlexible;
}
