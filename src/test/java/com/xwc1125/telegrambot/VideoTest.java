package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.Video;

import static org.junit.Assert.assertNotNull;

/**
 * stas
 * 10/21/15.
 */
public class VideoTest {

    public static void check(Video video) {
        check(video, true);
    }

    public static void check(Video video, boolean checkSize) {
        assertNotNull(video.fileId());
        assertNotNull(video.duration());
        assertNotNull(video.width());
        assertNotNull(video.height());
        if (checkSize) assertNotNull(video.fileSize());
        PhotoSizeTest.checkPhotos(video.thumb());
    }
}
