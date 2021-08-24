package com.xwc1125;

import com.xwc1125.telegrambot.TelegramBot;
import com.xwc1125.telegrambot.model.Sticker;
import com.xwc1125.telegrambot.model.request.ReplyKeyboardMarkup;
import com.xwc1125.telegrambot.request.GetFile;
import com.xwc1125.telegrambot.request.GetMe;
import com.xwc1125.telegrambot.request.GetStickerSet;
import com.xwc1125.telegrambot.request.SendMessage;
import com.xwc1125.telegrambot.response.GetFileResponse;
import com.xwc1125.telegrambot.response.GetMeResponse;
import com.xwc1125.telegrambot.response.GetStickerSetResponse;
import com.xwc1125.telegrambot.response.SendResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Description: <br>
 *
 * @author xwc1125 <br>
 * @Copyright: Copyright (c) 2019 <br>
 * @date 2019-01-03  14:57 <br>
 */
@Slf4j
public class MyBotMethodTest {
    TelegramBot bot;
    String chatId = "561399981";

    public MyBotMethodTest() {
        String token = "585487224:AAHzUcvSdxBx3NgFFfWFfjzu2RJ_Wt9RKSU";
        bot = new TelegramBot.Builder(token)
                .apiUrl("https://telegram.xwc1125.com/bot")
                .okHttpClient(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .readTimeout(60, TimeUnit.SECONDS) // setWebhook with certificate fails with timeout exception
                        .build())
                .build();
    }

    @Test
    public void getMe() {
        GetMeResponse getMeResponse = bot.getMe();
        System.out.println(getMeResponse);
    }

    @Test
    public void text() {
        SendResponse response = bot.text(chatId, "test");
        System.out.println(response);
    }

    @Test
    public void photo() {
        bot.photo(chatId, "https://photo.16pic.com/00/48/02/16pic_4802273_b.jpg");
    }

    @Test
    public void testOnStart() {
        try {
            bot.onStart(message -> bot.text(message, "欢迎使用机器人。"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMessage() throws InterruptedException {
        try {
            // 设置命令及其回复
            bot.onHelp(message -> {
                log.info("{}", message);
                bot.text(message, "/echo\r\n/me\r\n/hi");
            })
                    .onCmd("/echo", message -> {
                        log.info("{}", message);
                        bot.text(message, "Hi, " + message.from().username() + ". I,m fine.");
                    })
                    .onCmd("/hi", message -> bot.text(message, "Hi"))
                    .onCmd("/me", message -> bot.text(message, bot.toJson(bot.getMe())))
                    .onCmd("/img", message -> {
                        log.info("收到图片请求");
                        bot.photo(message, new File("/Users/biezhi/Pictures/20150812204022.jpeg"));
                    })
                    .onCmd("kbd", message -> {
                        String[][] key = new String[][]{
                                {"🌝", "🌚"},
                                {"Hello", "Hi"}
                        };
                        // 键盘区显示相应的内容
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(key, true, true, false);
                        SendMessage sendMessage = new SendMessage(message.chat().id(), "请选择一个表情");
                        sendMessage.replyMarkup(replyKeyboardMarkup);
                        bot.execute(sendMessage);
                    })
                    .onCmd("🌝", message -> bot.text(message, "你选择了小黄"))
                    .onCmd("🌚", message -> bot.text(message, "你选择了小黑"))
                    .await();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
