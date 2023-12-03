package com.cadiducho.telegrambotapi;

import com.cadiducho.telegrambotapi.exception.TelegramException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TelegramBotTest {

    private static String BOT_TOKEN;
    private static String CHAT_ID;
    private static String GROUP_ID;
    private static String PHOTO_ID;
    private static String BOT_NAME;

    private static BotAPI bot;

    @BeforeAll
    static void setUpTokens() {
        BOT_TOKEN = System.getenv("TELEGRAM_BOT_TOKEN");
        CHAT_ID = "8609873";
        GROUP_ID = "-278714251";
        PHOTO_ID = "AgADBAADKK0xG9IykVI0Pe7-q_ueezIrnxoABCR76YWV7M2WDHcEAAEC";
        BOT_NAME = "cadibetabot";
    }

    @BeforeEach
    void createBot() {
        bot = new TelegramBot(BOT_TOKEN);
    }

    @Test
    void getMe() throws TelegramException {
        User user = bot.getMe();

        assertNotNull(user);
        assertTrue(user.isBot());
        assertEquals(BOT_NAME, user.getUsername());
    }

    @Test
    void testException() {
        assertThrows(IllegalArgumentException.class, () -> bot.sendMessage(null, null)); //chat id cant be null
    }

    @Test
    void sendMessage() throws TelegramException {
        String hello = "<code>Hello</code> <b>from</b> <i>tests</i> " +
                "<span class=\"tg-spoiler\">spoiler</span> " +
                "<a href=\"http://www.example.com/\">inline URL</a> " +
                "<code>inline fixed-width code</code>\n" +
                "<pre>pre-formatted fixed-width code block</pre>\n" +
                "<pre><code class=\"language-python\">pre-formatted fixed-width code block written in the Python programming language</code></pre>";
        bot.sendMessage(CHAT_ID, hello, ParseMode.HTML, null, true, null, null, null);
    }

    @Test
    void sendPhoto() throws TelegramException {
        bot.sendPhoto(CHAT_ID, PHOTO_ID, "Test caption", null, null, null, null, null);
    }

    @Test
    void sendPhotoWithSpoiler() throws TelegramException {
        bot.sendPhoto(CHAT_ID, PHOTO_ID, "Photo with spoiler", true, null, null, null, null);
    }

    @Test
    void sendChatAction() throws TelegramException {
        for (BotAPI.ChatAction action : BotAPI.ChatAction.values()) {
            assertTrue(bot.sendChatAction(CHAT_ID, action));
        }
    }

    @Test
    void sendLocation() throws TelegramException {
        float latitude = 10.189f;
        float longitude = 48.29f;

        Message result = bot.sendLocation(CHAT_ID, latitude, longitude);

        assertNotNull(result);
        assertNotNull(result.getLocation());
    }

    @Test
    void testEditMessage() throws TelegramException {
        String raw = "Unedited message";
        String edited = "Edited message";
        Message message = bot.sendMessage(CHAT_ID, raw);
        assertEquals(raw, message.getText());

        Message editedMessage = bot.editMessageText(CHAT_ID, message.getMessageId(), null, edited, null, null, null);
        assertNotEquals(message.getText(), editedMessage.getText());
        assertEquals(edited, editedMessage.getText());
    }
    
    @Test
    void testPolls() throws TelegramException {
        String question = "Poll question test";
        List<String> options = Arrays.asList("Option 1", "Option 2");
        
        Message pollMessage = bot.sendPoll(GROUP_ID, question, options);
        Poll poll = bot.stopPoll(GROUP_ID, pollMessage.getMessageId());
        assertEquals(poll.getQuestion(), question);
        assertEquals(poll.getOptions().stream().map(PollOption::getText).collect(Collectors.toList()), options);
    }

    @Test
    void testUpdatePoller() throws InterruptedException {
        Update one = new Update();
        one.setUpdateId(1);
        Update two = new Update();
        two.setUpdateId(2);
        Update three = new Update();
        three.setUpdateId(3);

        ArrayList<Update> updates = new ArrayList<>();
        updates.add(one);
        updates.add(two);
        updates.add(three);

        bot.getUpdatesPoller().setUpdatesSupplier(() -> updates);
        bot.getUpdatesPoller().setHandler((update) -> {
            assertNotNull(update);
            assertNotNull(update.getUpdateId());
        });
        bot.startUpdatesPoller();
        Thread.sleep(100);
        bot.stopUpdatesPoller();
    }

    @Test
    void setAndGetBotCommands() throws TelegramException {
        BotCommand command = new BotCommand();
        command.setCommand("comandodeprueba");
        command.setDescription("descripcion de prueba");
        ArrayList<BotCommand> list = new ArrayList<>();
        list.add(command);

        assertTrue(bot.setMyCommands(list));
        assertEquals(bot.getMyCommands(), list);
    }

    @Nested
    class DescriptionTest {

            @Test
            void setAndGetBotDescription() throws TelegramException {
                String description = "Bot description test";
                String languageCode = "en";
                assertTrue(bot.setMyDescription(description, languageCode));
                assertEquals(bot.getMyDescription(languageCode).getDescription(), description);
            }

            @Test
            void setAndGetBotShortDescription() throws TelegramException {
                String shortDescription = "Bot short description test";
                String languageCode = "en";
                assertTrue(bot.setMyShortDescription(shortDescription, languageCode));
                assertEquals(bot.getMyShortDescription(languageCode).getShortDescription(), shortDescription);
            }
    }

    @Nested
    class MenuButtonTest {

        @Test
        void setChatMenuButton() throws TelegramException {
            assertTrue(bot.setChatMenuButton(CHAT_ID, new MenuButtonCommands()));
            assertTrue(bot.setChatMenuButton(CHAT_ID, new MenuButtonWebApp("test web app", new WebAppInfo("https://telegram.org/"))));
            assertTrue(bot.setChatMenuButton(CHAT_ID, new MenuButtonDefault()));
        }

        @Test
        void getChatMenuButton() throws TelegramException {
            assertTrue(bot.setChatMenuButton(CHAT_ID, new MenuButtonCommands()));
            MenuButton menuButton = bot.getChatMenuButton(CHAT_ID);
            assertNotNull(menuButton);
            assertEquals("commands", menuButton.getType());
        }
    }
}
