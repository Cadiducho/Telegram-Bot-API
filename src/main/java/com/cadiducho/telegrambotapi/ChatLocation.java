package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a location to which a chat is connected.
 */
@ToString
@Getter
@Setter
public class ChatLocation {

    /**
     * The location to which the supergroup is connected. Can't be a live location.
     */
    private Location location;

    /**
     * Location address; 1-64 characters, as defined by the chat owner
     */
    private String address;
}
