/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.game;

import com.cadiducho.telegrambotapi.Animation;
import com.cadiducho.telegrambotapi.MessageEntity;
import com.cadiducho.telegrambotapi.PhotoSize;
import java.util.List;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a game. Use BotFather to create and edit games, their short names will act as unique identifiers.
 */
@ToString
@Getter @Setter
public class Game {

    /**
     * Title of the game
     */
    private String title;
    
    /**
     * Description of the game
     */
    private String description;
    
    /**
     * Photo that will be displayed in the game message in chats.
     */
    private List<PhotoSize> photo;
    
    /**
     * Optional. Brief description of the game or high scores included in the game message. 
     * Can be automatically edited to include current high scores for the game when the bot calls setGameScore, or manually edited using editMessageText. 
     * 0-4096 characters.
     */
    private String text;
    
    /**
     * Optional. Special entities that appear in text, such as usernames, URLs, bot commands, etc.
     */
    @Json(name = "text_entities") private List<MessageEntity> textEntities;
    
    /**
     * Optional. Animation that will be displayed in the game message in chats. Upload via BotFather
     */
    private Animation animation;
}
