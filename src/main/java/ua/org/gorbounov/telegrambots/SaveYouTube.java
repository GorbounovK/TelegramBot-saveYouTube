/**
 * 
 */
package ua.org.gorbounov.telegrambots;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.extern.log4j.Log4j2;

/**
 * @author gk
 *
 */
@Log4j2
//@Component
public class SaveYouTube extends TelegramLongPollingBot{

	public SaveYouTube(String botToken) {
        super(new DefaultBotOptions(), botToken);
    }
	
	@Override
    public void onUpdateReceived(Update update) {
		log.debug("onUpdateReceived");
		   if (update.hasMessage() && update.getMessage().hasText()) {
		        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
		        message.setChatId(update.getMessage().getChatId().toString());
//		        log.debug(update.getMessage().getContact().toString());
		        message.setText(update.getMessage().getText());
		        log.debug(message.getText());
		        try {
		            execute(message); // Call method to send the message
		        } catch (TelegramApiException e) {
		            e.printStackTrace();
		        }
		    }
    }

    @Override
    public String getBotUsername() {
        return "gorbounov_saveYouTube_bot";
    }


}
