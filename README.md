Simple Telegram Bot API implementation to Java 
========================================
> This API is an unofficial one. Check [Telegram Bot API](https://core.telegram.org/bots) for more info.

[![License](https://img.shields.io/github/license/Cadiducho/Telegram-Bot-API)](https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE)
[![CI](https://github.com/Cadiducho/Telegram-Bot-API/actions/workflows/gradle.yml/badge.svg)](https://github.com/Cadiducho/Telegram-Bot-API/actions/workflows/gradle.yml)
[![Release](https://img.shields.io/github/release/Cadiducho/Telegram-Bot-API.svg)](https://github.com/Cadiducho/Telegram-Bot-API/releases)
[![Sponsors](https://img.shields.io/github/sponsors/Cadiducho)](https://github.com/sponsors/Cadiducho)

## Usage

```
import com.cadiducho.telegrambotapi.BotAPI;
import com.cadiducho.telegrambotapi.TelegramBot;
import com.cadiducho.telegrambotapi.api.User;
import com.cadiducho.telegrambotapi.api.exception.TelegramException;

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

If you want use Telegram Bot API as a Maven Dependency simply add repo and artifact:

```xml
<repository>
    <id>cadiducho-repo-releases</id>
    <name>Cadiducho Repository</name>
    <url>https://repo.cadiducho.com/releases</url>
</repository>

<dependency>
    <groupId>com.cadiducho</groupId>
    <artifactId>TelegramBotAPI</artifactId>
    <version>6.0</version>
</dependency>
```

You can always check lastest version in [my maven repository](https://repo.cadiducho.com/#/releases/com/cadiducho/TelegramBotAPI).

## License

This project is released under the [MIT-License](https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE).

> This project starts as a Bukkit-Minecraft API and now it is a standalone library. Check the original [Minegram](https://github.com/Cadiducho/Minegram)!
> Cadiducho's TelegramBotAPI is highly inspired on [Irazasyed's Telegram Bot API - PHP SDK](https://github.com/irazasyed/telegram-bot-sdk) 
> and [Rainu's telegram-bot-api](https://github.com/rainu/telegram-bot-api), but this one is written from scratch.

© 2022 [Cadiducho](https://twitter.com/Cadiducho)
