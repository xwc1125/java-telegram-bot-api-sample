package com.xwc1125.telegrambot;

import static org.junit.Assert.assertNotNull;

import com.xwc1125.telegrambot.model.ChatPhoto;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class ChatPhotoTest {

    public static void check(ChatPhoto photo) {
        assertNotNull(photo.smallFileId());
        assertNotNull(photo.bigFileId());
    }

}
