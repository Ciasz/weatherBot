import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Methods extends Bot {

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setKeyboard(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setKeyboard(SendMessage sendMessage) {
        ReplyKeyboardMarkup newKeyboard = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(newKeyboard);
        newKeyboard.setSelective(true);
        newKeyboard.setResizeKeyboard(true);
        newKeyboard.setOneTimeKeyboard(false);
        Answers answer = new Answers();
        List<KeyboardRow> keyboardRow = new ArrayList();

        KeyboardRow keyboard1Row = new KeyboardRow();
        keyboard1Row.add(new KeyboardButton(answer.row1Button));

        keyboardRow.add(keyboard1Row);

        newKeyboard.setKeyboard(keyboardRow);
    }
}
