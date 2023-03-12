/**
 * 
 */
package ua.org.gorbounov.telegrambots;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.extern.log4j.Log4j2;

/**
 * @author gk
 *
 */
@Log4j2
@Component
public class SaveYouTube extends TelegramLongPollingBot {

	public SaveYouTube(@Value("${telegram.bot.token}") String botToken) {
		super(new DefaultBotOptions(), botToken);
	}

	@Override
	public void onUpdateReceived(Update update) {
		log.debug("onUpdateReceived");
		if (update.hasMessage()) {
			log.debug("MessageId "+update.getMessage().getMessageId());
			if (update.getMessage().hasText()) {
				SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
				message.setChatId(update.getMessage().getChatId().toString());
				message.setText(update.getMessage().getText());
				log.debug(message.getText());
				message.setReplyMarkup(createKeyboard());
				try {
					execute(message); // Call method to send the message
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if (update.getMessage().hasContact()) {
				log.debug(update.getMessage().getContact().toString());

			}else if (update.getMessage().hasLocation()) {
				log.debug(update.getMessage().getLocation().toString());
			}
		}
	}

	public InlineKeyboardMarkup createKeyboard() {
	      // Create InlineKeyboardMarkup object
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        // Create the keyboard (list of InlineKeyboardButton list)
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        // Create a list for buttons
        List<InlineKeyboardButton> Buttons = new ArrayList<InlineKeyboardButton>();
        // Initialize each button, the text must be written
        InlineKeyboardButton youtube= new InlineKeyboardButton("youtube");
        // Also must use exactly one of the optional fields,it can edit  by set method
        youtube.setUrl("https://www.youtube.com");
        // Add button to the list
        Buttons.add(youtube);
        // Initialize each button, the text must be written
        InlineKeyboardButton github= new InlineKeyboardButton("github");
        // Also must use exactly one of the optional fields,it can edit  by set method
        github.setUrl("https://github.com");
        // Add button to the list
        Buttons.add(github);
        keyboard.add(Buttons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return inlineKeyboardMarkup;
	}
	
	
	@Override
	public String getBotUsername() {
		return "gorbounov_saveYouTube_bot";
	}

}
