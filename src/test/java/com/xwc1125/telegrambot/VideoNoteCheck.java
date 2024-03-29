package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.VideoNote;

import static org.junit.Assert.assertNotNull;

/**
 * Stas Parshin
 * 24 May 2017
 */
public class VideoNoteCheck {

    public static void check(VideoNote videoNote) {
        check(videoNote, false);
    }

    public static void check(VideoNote videoNote, boolean full) {
        assertNotNull(videoNote.fileId());
        assertNotNull(videoNote.length());
        assertNotNull(videoNote.duration());
        PhotoSizeTest.checkPhotos(videoNote.thumb());
        if (full) {
            assertNotNull(videoNote.fileSize());
        }
    }

}
