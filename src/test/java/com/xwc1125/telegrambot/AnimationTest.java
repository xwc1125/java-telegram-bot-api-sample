package com.xwc1125.telegrambot;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.xwc1125.telegrambot.model.Animation;

/**
 * Stas Parshin
 * 04 June 2017
 */
public class AnimationTest {

    public static void check(Animation animation) {
        assertNotNull(animation.fileId());
        assertNotNull(animation.fileName());
        assertNotNull(animation.mimeType());
        assertTrue(animation.fileSize() > 0);
        PhotoSizeTest.checkPhotos(animation.thumb());
    }

}
