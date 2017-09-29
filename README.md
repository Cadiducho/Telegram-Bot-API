Simple Telegram Bot API implementation to Java 
========================================
> This API is a unofficial one. Check [Telegram Bot API](https://core.telegram.org/bots) for more info.

<p align="center">
    <a href="https://github.com/Cadiducho/Telegram-Bot-API/commits/master"><img src="https://img.shields.io/github/release/Cadiducho/Telegram-Bot-API.svg" alt="Project Version" /></a>
    <a href="https://travis-ci.org/Cadiducho/Telegram-Bot-API"><img src="https://travis-ci.org/Cadiducho/Telegram-Bot-API.svg" alt="Project Status" /></a>
</a>

## Usage

```
import com.cadiducho.minegram.BotAPI;
import com.cadiducho.minegram.TelegramBot;
import com.cadiducho.minegram.api.User;
import com.cadiducho.minegram.api.exception.TelegramException;

...

static BotAPI telegramBot;

public static void main(String[] args) {
	telegramBot = new TelegramBot("to:kken");
	
	try {
		User user = telegramBot.getMe();
	} catch(TelegramException e) {
		System.out.println("Could'nt get bot instance!");
	}
}
```

## Javadocs

JavaDocs are located in https://cadiducho.com/telegrambotapi


## Maven Usage

If you want use Minegram as a Maven Dependency simply add repo and artifact:

```xml
<repository>
	<id>cadiducho-repo</id>
    <url>https://cadiducho.com/repo</url>
</repository>
...
<dependency>
    <groupId>com.cadiducho</groupId>
    <artifactId>Telegram-Bot-API</artifactId>
    <version>1.0</version>
</dependency>
```

## License

This project is released under the [MIT-License](https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE).

> This project stats as a Bukkit-Minecraft API and now it is a standalone library. Check the original [Minegram](https://github.com/Cadiducho/Minegram)!
> Cadiducho's TelegramBotAPI is highly inspired on [Irazasyed's Telegram Bot API - PHP SDK](https://github.com/irazasyed/telegram-bot-sdk) 
> and [Rainu's telegram-bot-api](https://github.com/rainu/telegram-bot-api), but this one is written from scratch.

© 2017 [Cadiducho](https://twitter.com/Cadiducho)
