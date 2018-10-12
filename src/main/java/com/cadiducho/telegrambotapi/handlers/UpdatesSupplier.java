package com.cadiducho.telegrambotapi.handlers;

import com.cadiducho.telegrambotapi.Update;

import java.util.List;

@FunctionalInterface
public interface UpdatesSupplier {
    List<Update> getUpdates() throws Exception;
}