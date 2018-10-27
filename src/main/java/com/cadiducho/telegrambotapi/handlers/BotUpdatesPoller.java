package com.cadiducho.telegrambotapi.handlers;

public interface BotUpdatesPoller {

    void setHandler(LongPollingHandler handler);

    void setUpdatesSupplier(UpdatesSupplier supplier);

    void setExceptionHandler(ExceptionHandler exceptionHandler);

    /**
     * Starts the bot poller
     */
    void start();

    /**
     * Stops the bot poller
     */
    void stop();

    /**
     * Check if the bot poller is running
     * @return True if the bot is running
     */
    boolean running();
}
