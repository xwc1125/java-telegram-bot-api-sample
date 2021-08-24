package com.xwc1125.telegrambot;

import static org.junit.Assert.assertNotNull;

import com.xwc1125.telegrambot.model.ChosenInlineResult;

/**
 * stas
 * 1/20/16.
 */
public class ChosenInlineResultTest {

    public static void check(ChosenInlineResult result) {
        assertNotNull(result.resultId());
        assertNotNull(result.query());
        UserTest.checkUser(result.from());
    }

}
