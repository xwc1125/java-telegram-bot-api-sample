package com.xwc1125.telegrambot;

import com.xwc1125.telegrambot.model.Chat;
import com.xwc1125.telegrambot.model.Message;
import com.xwc1125.telegrambot.model.Update;
import com.xwc1125.telegrambot.model.User;
import com.xwc1125.telegrambot.request.GetUpdates;
import com.xwc1125.telegrambot.response.GetUpdatesResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * stas
 * 3/31/16.
 */
public class TestLoopGetUpdates {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("local.properties"));
        TelegramBot bot = TelegramBotAdapter.build(properties.getProperty("TEST_TOKEN"));

        GetUpdatesResponse updatesResponse;
        int j = 0;
        while (true) {
            try {
                updatesResponse = bot.execute(new GetUpdates().offset(j).limit(100).timeout(20));
                List<Update> updates = updatesResponse.updates();
                for (int z = 0; z < updates.size(); z++) {
                    j = updates.get(z).updateId() + 1;
                    Message message = updates.get(z).message();
                    //
                    Chat chat = message.chat();
                    User user = message.from();
                    //String mes=message.text();

                    // Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
                    if (message.text() != null) {
                        System.out.println("New message: " + message.text() + " id: " + message.messageId() + " from " + chat);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
