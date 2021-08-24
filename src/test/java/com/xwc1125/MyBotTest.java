package com.xwc1125;

import com.xwc1125.telegrambot.Callback;
import com.xwc1125.telegrambot.GameTest;
import com.xwc1125.telegrambot.MessageTest;
import com.xwc1125.telegrambot.TelegramBot;
import com.xwc1125.telegrambot.model.*;
import com.xwc1125.telegrambot.model.request.*;
import com.xwc1125.telegrambot.request.AnswerCallbackQuery;
import com.xwc1125.telegrambot.request.AnswerInlineQuery;
import com.xwc1125.telegrambot.request.GetUpdates;
import com.xwc1125.telegrambot.request.SendGame;
import com.xwc1125.telegrambot.response.BaseResponse;
import com.xwc1125.telegrambot.response.GetUpdatesResponse;
import com.xwc1125.telegrambot.response.SendResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.xwc1125.telegrambot.request.ContentTypes.VIDEO_MIME_TYPE;

/**
 * stas
 * 5/2/16.
 */
public class MyBotTest {

    static TelegramBot bot;
    static Integer chatId;
    static Long groupId;
    static String stickerId = "BQADAgAD4AAD9HsZAAGVRXVaYXiJVAI";

    static String someUrl = "http://google.com";
    static String audioFileId = "CQADAgADXAADgNqgSevw7NljQE4lAg";
    static String voiceFileId = "AwADAgADYwADuYNZSZww_hkrzCIpAg";
    static String videoFileId = "BAADAgADZAADuYNZSXhLnzJTZ2yvAg";
    static String photoFileId = "AgADAgADDKgxG7mDWUlvyFIJ9XfF9yszSw0ABBhVadWwbAK1z-wIAAEC";
    static String gifFileId = "CgADAgADfQADgNqgSTt9SzatJhc3Ag";

    public static void main(String[] args) {
        String token, chat, group;
        token = "585487224:AAHzUcvSdxBx3NgFFfWFfjzu2RJ_Wt9RKSU";
        chat = "561399981";
        group = "-265007671";
        chatId = Integer.parseInt(chat);
        groupId = Long.parseLong(group);

        bot = new TelegramBot.Builder(token)
                .apiUrl("https://telegram.xwc1125.com/bot")
                .okHttpClient(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .readTimeout(60, TimeUnit.SECONDS) // setWebhook with certificate fails with timeout exception
                        .build())
                .build();

        while (true) {
            try {
                answerInline();
                answerCallback();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public MyBotTest() throws IOException {
    }

    @Test
    public static void answerInline() {
        // 文本输入框里的回复
        InlineQuery lastInlineQuery = getLastInlineQuery();
        if (lastInlineQuery == null) {
            return;
        }
        String inlineQueryId = lastInlineQuery != null ? lastInlineQuery.id() : "invalid_query_id";
        String query = lastInlineQuery.query();

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(new InlineKeyboardButton[]{
                new InlineKeyboardButton("inline game").callbackGame("lottery"),
                new InlineKeyboardButton("inline ok").callbackData("callback ok"),
                new InlineKeyboardButton("cancel").callbackData("callback cancel"),
                new InlineKeyboardButton("url").url(someUrl),
                new InlineKeyboardButton("switch inline").switchInlineQuery("query"),
                new InlineKeyboardButton("switch inline current").switchInlineQueryCurrentChat("query"),
        });

        InlineQueryResult[] results = new InlineQueryResult[]{
                new InlineQueryResultArticle("1", "title",
                        new InputTextMessageContent("message").disableWebPagePreview(false).parseMode(ParseMode.HTML))
                        .url(someUrl).hideUrl(true).description("desc").thumbUrl(someUrl).thumbHeight(100).thumbWidth(100),
                new InlineQueryResultArticle("2", "title",
                        new InputContactMessageContent("123123123", "na,e").lastName("lastName").vcard("qr vcard")),
                new InlineQueryResultArticle("3", "title", new InputLocationMessageContent(50f, 50f).livePeriod(60)),
                new InlineQueryResultArticle("4", "title",
                        new InputVenueMessageContent(50f, 50f, "title", "address").foursquareId("sqrId").foursquareType("frType")),
                new InlineQueryResultArticle("5", "title", "message"),
                new InlineQueryResultAudio("6", someUrl, "title").caption("cap <b>bold</b>").parseMode(ParseMode.HTML).performer("perf").audioDuration(100),
                new InlineQueryResultContact("7", "123123123", "name").lastName("lastName").vcard("tt vcard")
                        .thumbUrl(someUrl).thumbHeight(100).thumbWidth(100),
                new InlineQueryResultDocument("8", someUrl, "title", "application/pdf").caption("cap <b>bold</b>").parseMode(ParseMode.HTML).description("desc")
                        .thumbUrl(someUrl).thumbHeight(100).thumbWidth(100),
                new InlineQueryResultGame("9", "lottery").replyMarkup(keyboardMarkup),
                new InlineQueryResultGif("10", someUrl, someUrl).caption("cap <b>bold</b>").parseMode(ParseMode.HTML).title("title")
                        .gifHeight(100).gifWidth(100).gifDuration(100),
                new InlineQueryResultLocation("11", 50f, 50f, "title").livePeriod(60)
                        .thumbUrl(someUrl).thumbHeight(100).thumbWidth(100),
                new InlineQueryResultMpeg4Gif("12", someUrl, someUrl).caption("cap <b>bold</b>").parseMode(ParseMode.HTML).title("title")
                        .mpeg4Height(100).mpeg4Width(100).mpeg4Duration(100),
                new InlineQueryResultPhoto("13", someUrl, someUrl).photoWidth(100).photoHeight(100).title("title")
                        .description("desc").caption("cap <b>bold</b>").parseMode(ParseMode.HTML),
                new InlineQueryResultVenue("14", 54f, 55f, "title", "address").foursquareId("frsqrId").foursquareType("frType")
                        .thumbUrl(someUrl).thumbHeight(100).thumbWidth(100),
                new InlineQueryResultVideo("15", someUrl, VIDEO_MIME_TYPE, "text", someUrl, "title").caption("cap <b>bold</b>").parseMode(ParseMode.HTML)
                        .videoWidth(100).videoHeight(100).videoDuration(100).description("desc"),
                new InlineQueryResultVoice("16", someUrl, "title").caption("cap <b>bold</b>").parseMode(ParseMode.HTML).voiceDuration(100),
                new InlineQueryResultCachedAudio("17", audioFileId).caption("cap <b>bold</b>").parseMode(ParseMode.HTML),
                new InlineQueryResultCachedDocument("18", stickerId, "title").caption("cap <b>bold</b>").parseMode(ParseMode.HTML).description("desc"),
                new InlineQueryResultCachedGif("19", gifFileId).caption("cap <b>bold</b>").parseMode(ParseMode.HTML).title("title"),
                new InlineQueryResultCachedMpeg4Gif("21", gifFileId).caption("cap <b>bold</b>").parseMode(ParseMode.HTML).title("title"),
                new InlineQueryResultCachedPhoto("22", photoFileId).caption("cap <b>bold</b>").parseMode(ParseMode.HTML).description("desc").title("title"),
                new InlineQueryResultCachedSticker("23", stickerId),
                new InlineQueryResultCachedVideo("24", videoFileId, "title").caption("cap <b>bold</b>").parseMode(ParseMode.HTML).description("desc"),
                new InlineQueryResultCachedVoice("25", voiceFileId, "title").caption("cap <b>bold</b>").parseMode(ParseMode.HTML),
        };
        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery(inlineQueryId, results)
//                .cacheTime(100)
                .isPersonal(true)
                .nextOffset("offset")
                .switchPmText("start")
                .switchPmParameter("my_pm_parameter");
        bot.execute(answerInlineQuery, new Callback<AnswerInlineQuery, BaseResponse>() {
                    @Override
                    public void onResponse(AnswerInlineQuery request, BaseResponse response) {
                        System.out.println(response);
                    }

                    @Override
                    public void onFailure(AnswerInlineQuery request, IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private static InlineQuery getLastInlineQuery() {
        GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates());
        List<Update> updates = updatesResponse.updates();
        Collections.reverse(updates);
        for (Update update : updates) {
            if (update.inlineQuery() != null) {
                return update.inlineQuery();
            }
        }
        return null;
    }

    @Test
    public static void answerCallback() {
        CallbackQuery callbackQuery = getLastCallbackQuery();
        if (callbackQuery == null) {
            return;
        }
        String callbackQueryId = callbackQuery != null ? callbackQuery.id() : "invalid_query_id";
        BaseResponse response = null;
        if (callbackQuery != null) {
            String gameShortName = callbackQuery.gameShortName();
            if (gameShortName != null && gameShortName.equals("lottery")) {
                // 15秒内完成响应
                AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(callbackQueryId);
                answerCallbackQuery.showAlert(false);
                answerCallbackQuery.url("http://lottery491.52win.info");
                response = bot.execute(answerCallbackQuery);
                System.out.println(response);
            }
        }

        // 打开聊天
//        BaseResponse response = bot.execute(new AnswerCallbackQuery(callbackQueryId)
//                .text("answer callback")
//                .url("telegram.me/xwc1125_bot?game=lottery")
//                .showAlert(false)
//                .cacheTime(1));
        System.out.println(response);
    }

    private static CallbackQuery getLastCallbackQuery() {
        GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates());
        List<Update> updates = updatesResponse.updates();
        Collections.reverse(updates);
        for (Update update : updates) {
            if (update.callbackQuery() != null) {
                return update.callbackQuery();
            }
        }
        return null;
    }

    /**
     * 发送游戏
     * <p>
     * 游戏发送出去了，当用户点击后，需要监听获取到update的callback_query，然后进行响应
     */
    @Test
    public void sendGame() {
//        String desc = "lottery";
//        SendGame sendGame = new SendGame(chatId, desc);
//        Message message = bot.execute(sendGame).message();
//        MessageTest.checkMessage(message);
//        Game game = message.game();
//
//        GameTest.check(game);
//        assertEquals(desc, game.description());

        // 发送游戏
        // 游戏的简称(唯一)
        String gameShortName = "lottery";
        SendGame sendGame = new SendGame(chatId, gameShortName);

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        // 发送游戏时，这个button必须是放在第一位，否则会报错
                        new InlineKeyboardButton("Play").callbackGame(gameShortName),

//                        new InlineKeyboardButton("callback_data").callbackData("{" +
//                                "game_short_name: " + gameShortName + "}"),
//                        new InlineKeyboardButton("Play").callbackData(gameShortName),
                        // 点击后，在文本输入框里显示
                        new InlineKeyboardButton("send").switchInlineQueryCurrentChat(gameShortName),
                        // 连接
                        new InlineKeyboardButton("lottery491").url("http://lottery491.52win.info"),
//                        // 切换
                        new InlineKeyboardButton("Switch!").switchInlineQuery("switch_inline_query"),
                        new InlineKeyboardButton("Share").url("http://t.me/xwc1125_bot?game=" + gameShortName)
                });
        sendGame.replyMarkup(inlineKeyboard);
        SendResponse response = bot.execute(sendGame);
        Message message = response.message();
        MessageTest.checkMessage(message);
        Game game = message.game();

        GameTest.check(game);
    }
}
