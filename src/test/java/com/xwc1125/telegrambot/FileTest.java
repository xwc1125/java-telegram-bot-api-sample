package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.File;

import static org.junit.Assert.assertNotNull;

/**
 * stas
 * 10/21/15.
 */
public class FileTest {

    public static void check(File file) {
        check(file, true);
    }

    public static void check(File file, boolean path) {
        assertNotNull(file.fileId());
        assertNotNull(file.fileSize());
        if (path) assertNotNull(file.filePath());
    }
}
