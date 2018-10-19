package com.cadiducho.telegrambotapi;

import com.cadiducho.telegrambotapi.util.ApiResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void decodeApiResponse() throws IOException {
        String botMe = "{\"ok\":true,\"result\":{\"id\":80,\"is_bot\":true,\"first_name\":\"BotName\",\"username\":\"bot\"}}";
        ApiResponse<User> apiResponse = ApiResponse.from(botMe, User.class);
        assertTrue(apiResponse.getOk());
        assertNull(apiResponse.getDescription());
        User user = apiResponse.getResult();
        assertNotNull(user);
        assertTrue(user.isBot());
        assertEquals("bot", user.getUsername());
    }
}
