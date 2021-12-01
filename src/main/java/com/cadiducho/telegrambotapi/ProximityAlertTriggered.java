package com.cadiducho.telegrambotapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents the content of a service message, sent whenever a user in the chat triggers a proximity alert set by another user.
 */
@ToString
@Getter @Setter
public class ProximityAlertTriggered {

    /**
     * User that triggered the alert
     */
    private User traveler;

    /**
     * User that set the alert
     */
    private User watcher;

    /**
     * The distance between the users
     */
    private Integer distance;
}
