/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import java.util.List;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Contains information about the current status of a webhook.
 */
@ToString
@Getter @Setter
public class WebhookInfo {
    
    /**
     * Webhook URL, may be empty if webhook is not set up
     */
    private String url;
    
    /**
     * True, if a custom certificate was provided for webhook certificate checks
     */
    @Json(name = "has_custom_certificate") private Boolean hasCustomCertificate;
    
    /**
     * Number of updates awaiting delivery
     */
    @Json(name = "pending_update_count") private Integer pendingUpdateCount;

    /**
     * Optional. Currently used webhook IP address
     */
    @Json(name = "ip_address") private String ipAddress;
    
    /**
     * Optional. Unix time for the most recent error that happened when trying to deliver an update via webhook
     */
    @Json(name = "last_error_date") private Integer lastErrorDate;
    
    /**
     * Optional. Error message in human-readable format for the most recent error 
     * that happened when trying to deliver an update via webhook
     */
    @Json(name = "last_error_message") private String lastErrorMessage;

    /**
     * Optional. Unix time of the most recent error that happened when trying to synchronize available updates with Telegram datacenters
     */
    @Json(name = "last_synchronization_error_date") private Integer lastSynchronizationErrorDate;

    /**
     * Optional. Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery
     */
    @Json(name = "max_connections") private Integer maxConnections;
    
    /**
     * Optional. A list of update types the bot is subscribed to. Defaults to all update types
     */
    @Json(name = "allowed_updates") private List<String> allowedUpdates;
    
}
