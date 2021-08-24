package com.xwc1125.telegrambot;

import static org.junit.Assert.assertNotNull;

import com.xwc1125.telegrambot.model.ChatMember;

/**
 * Stas Parshin
 * 29 May 2016
 */
public class ChatMemberTest {

    public static void check(ChatMember chatMember) {
        assertNotNull(chatMember.user());
        assertNotNull(chatMember.status());
        UserTest.checkUser(chatMember.user(), chatMember.status() == ChatMember.Status.creator);
    }


}
