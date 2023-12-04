/*
 * The MIT License
 *
 * Copyright 2023 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a service message about a user allowing a bot to write messages after adding it to the attachment menu, launching a Web App from a link, or accepting an explicit request from a Web App sent by the method requestWriteAccess.
 */
@ToString
@Getter @Setter
public class WriteAccessAllowed {

    /**
     * Optional. True, if the access was granted after the user accepted an explicit request from a Web App sent by the method requestWriteAccess
     */
     @Json(name = "from_request") private Boolean fromRequest;

    /**
     * Optional. Name of the Web App, if the access was granted when the Web App was launched from a link
     */
    @Json(name = "web_app_name") private String webAppName;

    /**
     * Optional. True, if the access was granted when the bot was added to the attachment or side menu
     */
     @Json(name = "from_attachment_menu") private Boolean fromAttachmentMenu;

}
