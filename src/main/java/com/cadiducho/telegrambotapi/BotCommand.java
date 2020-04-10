package com.cadiducho.telegrambotapi;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a bot command.
 */
@ToString
@Getter @Setter
@EqualsAndHashCode
public class BotCommand {

    /**
     * Text of the command, 1-32 characters. Can contain only lowercase English letters, digits and underscores.
     */
    private String command;

    /**
     * Description of the command, 3-256 characters.
     */
    private String description;
}
