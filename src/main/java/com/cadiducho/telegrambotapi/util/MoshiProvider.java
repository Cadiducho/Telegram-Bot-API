/*
 * The MIT License
 *
 * Copyright 2022 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi.util;

import com.cadiducho.telegrambotapi.*;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import lombok.Getter;

public class MoshiProvider {

    @Getter
    static final Moshi moshi = new Moshi.Builder()
            .add(PolymorphicJsonAdapterFactory
                    .of(MenuButton.class, "type")
                    .withSubtype(MenuButtonCommands.class, "commands")
                    .withSubtype(MenuButtonWebApp.class, "web_app")
                    .withSubtype(MenuButtonDefault.class, "default"))
            .add(PolymorphicJsonAdapterFactory
                    .of(InputMedia.class, "type")
                    .withSubtype(InputMediaPhoto.class, "photo")
                    .withSubtype(InputMediaVideo.class, "video")
                    .withSubtype(InputMediaAnimation.class, "animation")
                    .withSubtype(InputMediaAudio.class, "audio")
                    .withSubtype(InputMediaDocument.class, "document")
            )
            .add(PolymorphicJsonAdapterFactory
                    .of(ChatMember.class, "status")
                    .withSubtype(ChatMemberOwner.class, "creator")
                    .withSubtype(ChatMemberAdministrator.class, "administrator")
                    .withSubtype(ChatMemberMember.class, "member")
                    .withSubtype(ChatMemberRestricted.class, "restricted")
                    .withSubtype(ChatMemberLeft.class, "left")
                    .withSubtype(ChatMemberBanned.class, "kicked")
            )
            .build();
}
