/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.inline;

import com.cadiducho.telegrambotapi.Location;
import com.cadiducho.telegrambotapi.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents an incoming inline query. When the user sends an empty query, your bot could return some default or trending results.
 */
@ToString
@Getter @Setter
public class InlineQuery {
    
    /**
     * Unique identifier for this query
     */
    private String id;
    
    /**
     * Sender
     */
    private User from;
    
    /**
     * Optional. Sender location, only for bots that request user location
     */
    private Location location;
    
    /**
     * Text of the query
     */
    private String query;
    
    /**
     * Offset of the results to be returned, can be controlled by the bot
     */
    private String offset;

    /**
     * Optional. Type of the chat, from which the inline query was sent. Can be either “sender” for a private chat with the inline query sender, “private”, “group”, “supergroup”, or “channel”.
     * The chat type should be always known for requests sent from official clients and most third-party clients, unless the request was sent from a secret chat
     */
    private String chat_type;
    
}
