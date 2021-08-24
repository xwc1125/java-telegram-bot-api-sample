package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.Game;

import static org.junit.Assert.assertNotNull;

/**
 * Stas Parshin
 * 03 October 2016
 */
public class GameTest {

    public static void check(Game game) {
        assertNotNull(game.title());
        assertNotNull(game.description());
        assertNotNull(game.text());
        assertNotNull(game.textEntities());
        com.xwc1125.telegrambot.PhotoSizeTest.checkPhotos(game.photo());
        com.xwc1125.telegrambot.AnimationTest.check(game.animation());
    }

}
