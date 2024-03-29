package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.Sticker;

import static org.junit.Assert.assertNotNull;

/**
 * stas
 * 10/21/15.
 */
public class StickerTest {

    public static void check(Sticker sticker, boolean emoji, boolean fileSize) {
        assertNotNull(sticker.fileId());
        assertNotNull(sticker.width());
        assertNotNull(sticker.height());
        PhotoSizeTest.checkPhotos(sticker.thumb());
        if (emoji) assertNotNull(sticker.emoji());
        if (fileSize) assertNotNull(sticker.fileSize());
    }
}
