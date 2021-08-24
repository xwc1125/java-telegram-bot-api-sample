package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.InlineQuery;

import static org.junit.Assert.assertNotNull;

/**
 * stas
 * 1/13/16.
 */
public class InlineQueryTest {

    public static void checkQuery(InlineQuery inlineQuery) {
        assertNotNull(inlineQuery.id());
        assertNotNull(inlineQuery.from());
        assertNotNull(inlineQuery.query());
        assertNotNull(inlineQuery.offset());
        com.xwc1125.telegrambot.UserTest.checkUser(inlineQuery.from());
    }

}
