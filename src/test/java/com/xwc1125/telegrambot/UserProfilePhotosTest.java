package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.PhotoSize;
import com.xwc1125.telegrambot.model.UserProfilePhotos;

import static org.junit.Assert.assertNotNull;

/**
 * stas
 * 10/21/15.
 */
public class UserProfilePhotosTest {

    public static void check(UserProfilePhotos profilePhotos) {
        assertNotNull(profilePhotos.totalCount());
        for (PhotoSize[] photos : profilePhotos.photos()) {
            PhotoSizeTest.checkPhotos(photos);
        }
    }
}
