package ua.org.gorbounov.telegrambots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
@EnableAutoConfiguration
public class TelegramBotSaveYouTubeApplication {
	@Autowired
	SaveYouTube bot;
	
	public static void main(String[] args) {
		SpringApplication.run(TelegramBotSaveYouTubeApplication.class, args);
	}

	@Bean
	public SaveYouTube MyBot() {
		log.info("We've just greeted the user!");
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			BotSession bs = botsApi.registerBot(bot);
			if (bs.isRunning()) {
				log.debug("Bot is running");
			}
		} catch (TelegramApiException e) {
			log.error(e.getLocalizedMessage());
		}
		return bot;
	}

}
