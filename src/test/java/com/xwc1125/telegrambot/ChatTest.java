package com.xwc1125.telegrambot;

import static org.junit.Assert.assertNotNull;

import com.xwc1125.telegrambot.model.Chat;

/**
 * stas
 * 10/21/15.
 */
public class ChatTest {

    public static void checkChat(Chat chat) {
        assertNotNull(chat.id());
        assertNotNull(chat.type());
        if (chat.photo() != null) {
            ChatPhotoTest.check(chat.photo());
        }
    }

}
