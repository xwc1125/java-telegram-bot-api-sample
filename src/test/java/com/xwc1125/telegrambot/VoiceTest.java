package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.Voice;

import static org.junit.Assert.assertNotNull;

/**
 * stas
 * 10/21/15.
 */
public class VoiceTest {

    public static void check(Voice voice) {
        check(voice, true);
    }

    public static void check(Voice voice, boolean checkSize) {
        assertNotNull(voice.fileId());
        assertNotNull(voice.mimeType());
        assertNotNull(voice.duration());
        if (checkSize) assertNotNull(voice.fileSize());
    }

}
