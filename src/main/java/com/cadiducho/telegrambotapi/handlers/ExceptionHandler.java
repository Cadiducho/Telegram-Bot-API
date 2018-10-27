package com.cadiducho.telegrambotapi.handlers;

import com.cadiducho.telegrambotapi.exception.TelegramException;

@FunctionalInterface
public interface ExceptionHandler {
    void handle(TelegramException exception);
}