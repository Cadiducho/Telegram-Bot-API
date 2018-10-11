/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.game;

import com.cadiducho.telegrambotapi.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents one row of the high scores table for a game.
 */
@ToString
@Getter @Setter
public class GameHighScore {

    /**
     * 	Position in high score table for the game
     */
    private Integer position;
    
    /**
     * User
     */
    private User user;
    
    /**
     * Score
     */
    private Integer score;
    
}
