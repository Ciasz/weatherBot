import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {

        String PORT = System.getenv("PORT");
        String SERVER_URL = System.getenv("SERVER_URL");

        ApiContextInitializer.init();
        TelegramBotsApi bot = new TelegramBotsApi();
        try {
            bot.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        Methods method = new Methods();
        Answers answer = new Answers();
        Model model = new Model();
        if (message != null && message.hasText()) {
            if (message.getText() == answer.row1Button) {
                method.sendMsg(message, answer.faq);
            }
            String s = message.getText();
            if ("/start".equals(s) || "Справка/помощь по боту".equals(s) || "/help".equals(s)) {
                method.sendMsg(message, answer.faq);
            } else if ("/api".equals(s)) {
                method.sendMsg(message, answer.api);
            } else {
                try {
                    method.sendMsg(message, Weather.getWeather(message.getText(), model));
                } catch (IOException e) {
                    method.sendMsg(message, answer.fail);
                }
            }
        }
    }


    public String getBotUsername() {
        return "Weather";
    }

    public String getBotToken() {
        return "833093483:AAEh7gxZ4zHGGXP5phsqerQkiuAy3QseLCk";
    }
}
