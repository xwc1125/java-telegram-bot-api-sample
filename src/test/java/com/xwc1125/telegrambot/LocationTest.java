package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.Location;

import static org.junit.Assert.assertNotNull;

/**
 * stas
 * 10/21/15.
 */
public class LocationTest {


    public static void check(Location location) {
        assertNotNull(location.latitude());
        assertNotNull(location.longitude());
    }
}
