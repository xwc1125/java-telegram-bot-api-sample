package com.xwc1125.telegrambot;

import static org.junit.Assert.assertNotNull;

import com.xwc1125.telegrambot.model.Audio;

/**
 * stas
 * 10/21/15.
 */
public class AudioTest {

    public static void checkAudio(Audio audio) {
        checkAudio(audio, true);
    }

    public static void checkAudio(Audio audio, boolean thumb) {
        assertNotNull(audio.fileId());
        assertNotNull(audio.duration());
        assertNotNull(audio.title());
        assertNotNull(audio.mimeType());
        assertNotNull(audio.fileSize());
        assertNotNull(audio.performer());
        if (thumb) {
            assertNotNull(audio.thumb());
        }
    }
}
