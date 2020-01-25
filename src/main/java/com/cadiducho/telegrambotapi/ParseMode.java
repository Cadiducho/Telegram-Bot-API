/*
 * The MIT License
 *
 * Copyright 2020 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import lombok.Getter;

/**
 * Types of parse mode in a message
 */
public enum ParseMode {

    MARKDOWN("Markdown"),
    MARKDOWNV2("MarkdownV2"),
    HTML("html");

    @Getter private final String mode;

    ParseMode(String mode) {
        this.mode = mode;
    }
}