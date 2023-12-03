/*
 * The MIT License
 *
 * Copyright 2020 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.cadiducho.telegrambotapi.exception.TelegramException;
import com.cadiducho.telegrambotapi.game.GameHighScore;
import com.cadiducho.telegrambotapi.handlers.BotUpdatesPoller;
import com.cadiducho.telegrambotapi.inline.InlineKeyboardMarkup;
import com.cadiducho.telegrambotapi.inline.InlineQueryResult;
import com.cadiducho.telegrambotapi.keyboard.ReplyKeyboardMarkup;
import com.cadiducho.telegrambotapi.keyboard.ReplyKeyboardRemove;
import com.cadiducho.telegrambotapi.payment.LabeledPrice;
import com.cadiducho.telegrambotapi.payment.ShippingOption;
import com.cadiducho.telegrambotapi.sticker.InputSticker;
import com.cadiducho.telegrambotapi.sticker.MaskPosition;
import com.cadiducho.telegrambotapi.sticker.Sticker;
import com.cadiducho.telegrambotapi.sticker.StickerSet;

import java.util.List;

/**
 * Interface to build Telegrams Bots 
 * Telegram Bot API version 6.6
 */
public interface BotAPI {

    //---- Custom Methods ----//

    /**
     * Starts the updates thread
     */
    void startUpdatesPoller();

    /**
     * Stop the updates thread
     */
    void stopUpdatesPoller();

    /**
     * Get the UpdatesPoller instance
     * @return the updates poller instance
     */
    BotUpdatesPoller getUpdatesPoller();
    
    /**
     * Get Bot instance
     * @return BotAPI
     */
    BotAPI getInstance();
    
    //---- Telegram Methods ----//
    
    /**
     * A simple method for testing your bot's auth token. Requires no parameters. Returns basic information about the bot in form of a {@link User} object.
     * @return {@link User}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers if the method failed on Telegram servers
     */
    User getMe() throws TelegramException;

    /**
     * Use this method to log out from the cloud Bot API server before launching the bot locally.
     * You must log out the bot before running it locally, otherwise there is no guarantee that the bot will receive updates.
     * After a successful call, you can immediately log in on a local server, but will not be able to log in back to the cloud Bot API server for 10 minutes.
     * Requires no parameters.
     * @return True on success
     */
    Boolean logOut() throws TelegramException;

    /**
     * Use this method to close the bot instance before moving it from one local server to another.
     * You need to delete the webhook before calling this method to ensure that the bot isn't launched again after server restart.
     * The method will return error 429 in the first 10 minutes after the bot is launched.
     * Requires no parameters.
     * @return True on success
     */
    Boolean close() throws TelegramException;

    /**
     * Use this method to send text messages. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param text Text of the message to be sent
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendMessage(Object chat_id, String text) throws TelegramException {
        return sendMessage(chat_id, text, null, null, false, null, null, null);
    }

    /**
     * Use this method to send text messages. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param text Text of the message to be sent
     * @param parse_mode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     * @param disable_web_page_preview Disables link previews for links in this message
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendMessage(Object chat_id, String text, ParseMode parse_mode, Boolean disable_notification, Boolean protect_content, Boolean disable_web_page_preview, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendMessage(chat_id, null, text, parse_mode, disable_notification, protect_content, disable_web_page_preview, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send text messages. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param text Text of the message to be sent
     * @param parse_mode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     * @param disable_web_page_preview Disables link previews for links in this message
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendMessage(Object chat_id, Integer message_thread_id, String text, ParseMode parse_mode, Boolean disable_notification, Boolean protect_content, Boolean disable_web_page_preview, Integer reply_to_message_id, Object reply_markup) throws TelegramException;

    /**
     * Use this method to forward messages of any kind. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param from_chat_id Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
     * @param message_id Unique message identifier
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message forwardMessage(Object chat_id, Integer from_chat_id, Integer message_id) throws TelegramException {
        return forwardMessage(chat_id, from_chat_id, false, false, message_id);
    }
    
    /**
     * Use this method to forward messages of any kind. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param from_chat_id Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param message_id Unique message identifier
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message forwardMessage(Object chat_id, Integer from_chat_id, Boolean disable_notification, Boolean protect_content, Integer message_id) throws TelegramException {
        return forwardMessage(chat_id, null, from_chat_id, disable_notification, protect_content, message_id);
    }

    /**
     * Use this method to forward messages of any kind. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param from_chat_id Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param message_id Unique message identifier
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message forwardMessage(Object chat_id, Integer message_thread_id, Integer from_chat_id, Boolean disable_notification, Boolean protect_content, Integer message_id) throws TelegramException;

    /**
     * Use this method to copy messages of any kind. Service messages and invoice messages can't be copied.
     * The method is analogous to the method forwardMessage, but the copied message doesn't have a link to the original message.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param from_chat_id Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
     * @param message_id Message identifier in the chat specified in from_chat_id
     * @return Returns the MessageId of the sent message on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default MessageId copyMessage(Object chat_id, Object from_chat_id, Integer message_id) throws TelegramException {
        return copyMessage(chat_id, from_chat_id, message_id, null, null, null, null, null, null, null);
    }

    /**
     * Use this method to copy messages of any kind. Service messages and invoice messages can't be copied.
     * The method is analogous to the method forwardMessage, but the copied message doesn't have a link to the original message.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param from_chat_id Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
     * @param message_id Message identifier in the chat specified in from_chat_id
     * @param caption New caption for media, 0-1024 characters after entities parsing. If not specified, the original caption is kept
     * @param parse_mode Mode for parsing entities in the new caption. See formatting options for more details.
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param allow_sending_without_reply Pass True, if the message should be sent even if the specified replied-to message is not found
     * @param reply_markup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
     * @return Returns the MessageId of the sent message on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default MessageId copyMessage(Object chat_id, Object from_chat_id, Integer message_id, String caption, String parse_mode, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Boolean allow_sending_without_reply, Object reply_markup) throws TelegramException {
        return copyMessage(chat_id, null, from_chat_id, message_id, caption, parse_mode, disable_notification, protect_content, reply_to_message_id, allow_sending_without_reply, reply_markup);
    }

    /**
     * Use this method to copy messages of any kind. Service messages and invoice messages can't be copied.
     * The method is analogous to the method forwardMessage, but the copied message doesn't have a link to the original message.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param from_chat_id Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
     * @param message_id Message identifier in the chat specified in from_chat_id
     * @param caption New caption for media, 0-1024 characters after entities parsing. If not specified, the original caption is kept
     * @param parse_mode Mode for parsing entities in the new caption. See formatting options for more details.
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param allow_sending_without_reply Pass True, if the message should be sent even if the specified replied-to message is not found
     * @param reply_markup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
     * @return Returns the MessageId of the sent message on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    MessageId copyMessage(Object chat_id, Integer message_thread_id, Object from_chat_id, Integer message_id, String caption, String parse_mode, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Boolean allow_sending_without_reply, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to send photos. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendPhoto(Object chat_id, String photo) throws TelegramException {
        return sendPhoto(chat_id, photo, null, null, false, null, null, null);
    }
    
    /**
     * Use this method to send photos. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendPhoto(Object chat_id, java.io.File photo) throws TelegramException {
        return sendPhoto(chat_id, photo, null, null, false, null, null, null);
    }

    /**
     * Use this method to send photos. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
     * @param caption Photo caption (may also be used when resending photos by file_id).
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendPhoto(Object chat_id, Object photo, String caption, Boolean has_spoiler, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendPhoto(chat_id, null, photo, caption, has_spoiler, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }
    
    /**
     * Use this method to send photos. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
     * @param caption Photo caption (may also be used when resending photos by file_id).
     * @param has_spoiler Pass True if the photo needs to be covered with a spoiler animation
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendPhoto(Object chat_id, Integer message_thread_id, Object photo, String caption, Boolean has_spoiler, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to send audio files, if you want Telegram clients to display them in the music player. 
     * Your audio must be in the .mp3 format. On success, the sent {@link Message} is returned. 
     * Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
     * 
     * For backward compatibility, when the fields title and performer are both empty and the mime-type of the file to be sent is not audio/mpeg, 
     * the file will be sent as a playable voice message. For this to work, the audio must be in an .ogg file encoded with OPUS. 
     * This behavior will be phased out in the future. For sending voice messages, use the sendVoice method instead.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param audio Audio file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendAudio(Object chat_id, String audio) throws TelegramException {
        return sendAudio(chat_id, audio, null, null,  null, null, false, null, null, null);
    }
    
    /**
     * Use this method to send audio files, if you want Telegram clients to display them in the music player. 
     * Your audio must be in the .mp3 format. On success, the sent {@link Message} is returned. 
     * Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
     * 
     * For backward compatibility, when the fields title and performer are both empty and the mime-type of the file to be sent is not audio/mpeg, 
     * the file will be sent as a playable voice message. For this to work, the audio must be in an .ogg file encoded with OPUS. 
     * This behavior will be phased out in the future. For sending voice messages, use the sendVoice method instead.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param audio Audio file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendAudio(Object chat_id, java.io.File audio) throws TelegramException {
        return sendAudio(chat_id, audio, null, null, null, null, false, null, null, null);
    }

    /**
     * Use this method to send audio files, if you want Telegram clients to display them in the music player.
     * Your audio must be in the .mp3 format. On success, the sent {@link Message} is returned.
     * Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
     *
     * For backward compatibility, when the fields title and performer are both empty and the mime-type of the file to be sent is not audio/mpeg,
     * the file will be sent as a playable voice message. For this to work, the audio must be in an .ogg file encoded with OPUS.
     * This behavior will be phased out in the future. For sending voice messages, use the sendVoice method instead.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param audio Audio file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
     * @param caption Audio caption, 0-200 characters
     * @param duration Duration of the audio in seconds
     * @param performer Performer
     * @param title Track name
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendAudio(Object chat_id, Object audio, String caption, Integer duration, String performer, String title, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendAudio(chat_id, null, audio, caption, duration, performer, title, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send audio files, if you want Telegram clients to display them in the music player. 
     * Your audio must be in the .mp3 format. On success, the sent {@link Message} is returned. 
     * Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
     * 
     * For backward compatibility, when the fields title and performer are both empty and the mime-type of the file to be sent is not audio/mpeg, 
     * the file will be sent as a playable voice message. For this to work, the audio must be in an .ogg file encoded with OPUS. 
     * This behavior will be phased out in the future. For sending voice messages, use the sendVoice method instead.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param audio Audio file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
     * @param caption Audio caption, 0-200 characters
     * @param duration Duration of the audio in seconds
     * @param performer Performer
     * @param title Track name
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendAudio(Object chat_id, Integer message_thread_id, Object audio, String caption, Integer duration, String performer, String title, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to send general files. On success, the sent {@link Message} is returned. 
     * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param document File to send. You can either pass a file_id as String to resend a file that is already on the Telegram servers, 
     *                  or upload a new file using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendDocument(Object chat_id, String document) throws TelegramException {
        return sendDocument(chat_id, document, false, false, null, null, null);
    }
    
    /**
     * Use this method to send general files. On success, the sent {@link Message} is returned. 
     * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param document File to send. You can either pass a file_id as String to resend a file that is already on the Telegram servers, 
     *                  or upload a new file using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendDocument(Object chat_id, java.io.File document) throws TelegramException {
        return sendDocument(chat_id, document, null, false, null, null, null);
    }

    /**
     * Use this method to send general files. On success, the sent {@link Message} is returned.
     * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param document File to send. You can either pass a file_id as String to resend a file that is already on the Telegram servers,
     *                  or upload a new file using multipart/form-data.
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param disable_content_type_detection Disables automatic server-side content type detection for files uploaded using multipart/form-data
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendDocument(Object chat_id, Object document, Boolean disable_content_type_detection, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendDocument(chat_id, null, document, disable_content_type_detection, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send general files. On success, the sent {@link Message} is returned. 
     * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param document File to send. You can either pass a file_id as String to resend a file that is already on the Telegram servers, 
     *                  or upload a new file using multipart/form-data.
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param disable_content_type_detection Disables automatic server-side content type detection for files uploaded using multipart/form-data
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendDocument(Object chat_id, Integer message_thread_id, Object document, Boolean disable_content_type_detection, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to send video files, Telegram clients support mp4 videos (other formats may be sent as {@link Document}). 
     * On success, the sent {@link Message} is returned. 
     * Bots can currently send video files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param video Video to send. You can either pass a file_id as String to resend a video that is already on the Telegram servers, 
     *                  or upload a new video file using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVideo(Object chat_id, String video) throws TelegramException {
        return sendVideo(chat_id, video, null, null, null, null, null, null, null, false, null, null, null);
    }
    
    /**
     * Use this method to send video files, Telegram clients support mp4 videos (other formats may be sent as {@link Document}). 
     * On success, the sent {@link Message} is returned. 
     * Bots can currently send video files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param video Video to send. You can either pass a file_id as String to resend a video that is already on the Telegram servers, 
     *                  or upload a new video file using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVideo(Object chat_id, java.io.File video) throws TelegramException {
        return sendVideo(chat_id, video, null, null, null, null, null, null, null, false, null, null, null);
    }

    /**
     * Use this method to send video files, Telegram clients support mp4 videos (other formats may be sent as {@link Document}).
     * Bots can currently send video files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param video Video file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
     * @param duration Duration of the video in seconds
     * @param width Video width
     * @param height Video height
     * @param caption Video caption (may also be used when resending videos by file_id), 0-200 characters
     * @param parse_mode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     * @param has_spoiler Pass True if the video needs to be covered with a spoiler animation
     * @param supports_streaming Pass True, if the uploaded video is suitable for streaming
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVideo(Object chat_id, Object video, Integer duration, Integer width, Integer height, String caption, ParseMode parse_mode, Boolean has_spoiler, Boolean supports_streaming, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendVideo(chat_id, null, video, duration, width, height, caption, parse_mode, has_spoiler, supports_streaming, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send video files, Telegram clients support mp4 videos (other formats may be sent as {@link Document}).
     * Bots can currently send video files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param video Video file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
     * @param duration Duration of the video in seconds
     * @param width Video width
     * @param height Video height
     * @param caption Video caption (may also be used when resending videos by file_id), 0-200 characters
     * @param parse_mode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     * @param has_spoiler Pass True if the video needs to be covered with a spoiler animation
     * @param supports_streaming Pass True, if the uploaded video is suitable for streaming
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendVideo(Object chat_id, Integer message_thread_id, Object video, Integer duration, Integer width, Integer height, String caption, ParseMode parse_mode, Boolean has_spoiler, Boolean supports_streaming, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound).
     * On success, the sent {@link Message} is returned.
     * Bots can currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param animation Animation to send. Pass a file_id as String to send an animation that exists on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an animation from the Internet, or upload a new animation using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendAnimation(Object chat_id, String animation) throws TelegramException {
        return sendAnimation(chat_id, animation, null, null, null, null, null, null, null, false, null, null, null);
    }

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound).
     * On success, the sent {@link Message} is returned.
     * Bots can currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param animation Animation to send. Pass a file_id as String to send an animation that exists on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an animation from the Internet, or upload a new animation using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendAnimation(Object chat_id, java.io.File animation) throws TelegramException {
        return sendAnimation(chat_id, animation, null, null, null, null, null, null, null, false, null, null, null);
    }

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound).
     * On success, the sent {@link Message} is returned.
     * Bots can currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param animation Animation to send. Pass a file_id as String to send an animation that exists on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an animation from the Internet, or upload a new animation using multipart/form-data.
     * @param duration Duration of the animation in seconds
     * @param width Animation width
     * @param height Animation height
     * @param thumbnail Thumbnail of the file sent. The thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail‘s width and height should not exceed 90. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can’t be reused and can be only uploaded as a new file, so you can pass “attach://&lt;file_attach_name&gt;” if the thumbnail was uploaded using multipart/form-data under &lt;file_attach_name&gt;.
     * @param caption Animation caption (may also be used when resending videos by file_id), 0-1024 characters
     * @param parse_mode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     * @param has_spoiler Pass True if the animation needs to be covered with a spoiler animation
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendAnimation(Object chat_id, Object animation, Integer duration, Integer width, Integer height, Object thumbnail, String caption, ParseMode parse_mode, Boolean has_spoiler, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendAnimation(chat_id, null, animation, duration, width, height, thumbnail, caption, parse_mode, has_spoiler, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound).
     * On success, the sent {@link Message} is returned.
     * Bots can currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param animation Animation to send. Pass a file_id as String to send an animation that exists on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an animation from the Internet, or upload a new animation using multipart/form-data.
     * @param duration Duration of the animation in seconds
     * @param width Animation width
     * @param height Animation height
     * @param thumbnail Thumbnail of the file sent. The thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail‘s width and height should not exceed 90. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can’t be reused and can be only uploaded as a new file, so you can pass “attach://&lt;file_attach_name&gt;” if the thumbnail was uploaded using multipart/form-data under &lt;file_attach_name&gt;.
     * @param caption Animation caption (may also be used when resending videos by file_id), 0-1024 characters
     * @param parse_mode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     * @param has_spoiler Pass True if the animation needs to be covered with a spoiler animation
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendAnimation(Object chat_id, Integer message_thread_id, Object animation, Integer duration, Integer width, Integer height, Object thumbnail, String caption, ParseMode parse_mode, Boolean has_spoiler, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;

    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message. 
     * For this to work, your audio must be in an .ogg file encoded with OPUS (other formats may be sent as {@link Audio} or {@link Document}). 
     * On success, the sent {@link Message} is returned. Bots can currently send voice messages of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param voice Audio file to send. You can either pass a file_id as String to resend a video that is already on the Telegram servers, 
     *                  or upload a new video file using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVoice(Object chat_id, String voice) throws TelegramException {
        return sendVoice(chat_id, voice, null, null, false, null, null, null);
    }
    
    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message. 
     * For this to work, your audio must be in an .ogg file encoded with OPUS (other formats may be sent as {@link Audio} or {@link Document}). 
     * On success, the sent {@link Message} is returned. Bots can currently send voice messages of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param voice Audio file to send. You can either pass a file_id as String to resend a video that is already on the Telegram servers, 
     *                  or upload a new video file using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVoice(Object chat_id, java.io.File voice) throws TelegramException {
        return sendVoice(chat_id, voice, null, null, false, null, null, null);
    }

    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message.
     * For this to work, your audio must be in an .ogg file encoded with OPUS (other formats may be sent as {@link Audio} or {@link Document}).
     * On success, the sent {@link Message} is returned. Bots can currently send voice messages of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param voice Audio file to send. You can either pass a file_id as String to resend a video that is already on the Telegram servers,
     *                  or upload a new video file using multipart/form-data.
     * @param caption Voice message caption, 0-200 characters
     * @param duration Duration of the audio in seconds
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVoice(Object chat_id, Object voice, String caption, Integer duration, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendVoice(chat_id, null, voice, caption, duration, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message. 
     * For this to work, your audio must be in an .ogg file encoded with OPUS (other formats may be sent as {@link Audio} or {@link Document}). 
     * On success, the sent {@link Message} is returned. Bots can currently send voice messages of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param voice Audio file to send. You can either pass a file_id as String to resend a video that is already on the Telegram servers, 
     *                  or upload a new video file using multipart/form-data.
     * @param caption Voice message caption, 0-200 characters
     * @param duration Duration of the audio in seconds
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendVoice(Object chat_id, Integer message_thread_id, Object voice, String caption, Integer duration, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;

    /**
     * Use this method to send a group of photos or videos as an album.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param media A JSON-serialized array describing photos and videos to be sent, must include 2–10 items
     * @return An array of the sent Messages is returned on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendMediaGroup(Object chat_id, List<InputMedia> media) throws TelegramException {
        return sendMediaGroup(chat_id, media, false, null, null);
    }

    /**
     * Use this method to send a group of photos or videos as an album.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param media A JSON-serialized array describing photos and videos to be sent, must include 2–10 items
     * @param disable_notification Sends the messages silently. Users will receive a notification with no sound.
     * @param reply_to_message_id If the messages are a reply, ID of the original message
     * @return An array of the sent Messages is returned on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendMediaGroup(Object chat_id, List<InputMedia> media, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id) throws TelegramException {
        return sendMediaGroup(chat_id, null, media, disable_notification, protect_content, reply_to_message_id);
    }

    /**
     * Use this method to send a group of photos or videos as an album.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param media A JSON-serialized array describing photos and videos to be sent, must include 2–10 items
     * @param disable_notification Sends the messages silently. Users will receive a notification with no sound.
     * @param reply_to_message_id If the messages are a reply, ID of the original message
     * @return An array of the sent Messages is returned on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendMediaGroup(Object chat_id, Integer message_thread_id, List<InputMedia> media, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id) throws TelegramException;

    /**
     * Use this method to send point on the map. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param latitude Latitude of location
     * @param longitude Longitude of location
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendLocation(Object chat_id, Float latitude, Float longitude) throws TelegramException {
        return sendLocation(chat_id, latitude, longitude, null, null, null,  null, false, null, null, null);
    }

    /**
     * Use this method to send point on the map. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param latitude Latitude of location
     * @param longitude Longitude of location
     * @param horizontal_accuracy The radius of uncertainty for the location, measured in meters; 0-1500
     * @param live_period Period in seconds for which the location will be updated (should be between 60 and 86400).
     * @param heading For live locations, a direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
     * @param proximity_alert_radius For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendLocation(Object chat_id, Float latitude, Float longitude, Float horizontal_accuracy, Integer live_period, Integer heading, Integer proximity_alert_radius, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendLocation(chat_id, null, latitude, longitude, horizontal_accuracy, live_period, heading, proximity_alert_radius, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send point on the map. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param latitude Latitude of location
     * @param longitude Longitude of location
     * @param horizontal_accuracy The radius of uncertainty for the location, measured in meters; 0-1500
     * @param live_period Period in seconds for which the location will be updated (should be between 60 and 86400).
     * @param heading For live locations, a direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
     * @param proximity_alert_radius For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendLocation(Object chat_id, Integer message_thread_id, Float latitude, Float longitude, Float horizontal_accuracy, Integer live_period, Integer heading, Integer proximity_alert_radius, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to edit live location messages sent by the bot or via the bot (for inline bots).
     * A location can be edited until its live_period expires or editing is explicitly disabled by a call to stopMessageLiveLocation. 
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @param latitude Latitude of new location
     * @param longitude Longitude of new location
     * @param horizontal_accuracy 	The radius of uncertainty for the location, measured in meters; 0-1500
     * @param heading Direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
     * @param proximity_alert_radius Maximum distance for proximity alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
     * @param reply_markup A JSON-serialized object for a new inline keyboard.
     * @return if the edited message was sent by the bot, the edited Message is returned, otherwise True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Object editMessageLiveLocation(Object chat_id, Integer message_id, String inline_message_id, Float latitude, Float longitude, Float horizontal_accuracy, Integer heading, Integer proximity_alert_radius, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
    /**
     * Use this method to stop updating a live location message sent by the bot or via the bot (for inline bots) before live_period expires.
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @param reply_markup A JSON-serialized object for a new inline keyboard.
     * @return if the message was sent by the bot, the sent Message is returned, otherwise True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Object stopMessageLiveLocation(Object chat_id, Integer message_id, String inline_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
    /**
     * As of v.4.0, Telegram clients support rounded square mp4 videos of up to 1 minute long. Use this method to send video messages. On success, the sent Message is returned.
     * For this to work, your audio must be in an .ogg file encoded with OPUS (other formats may be sent as {@link Audio} or {@link Document}). 
     * On success, the sent {@link Message} is returned. Bots can currently send voice messages of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param video_note Video note to send. Pass a file_id as String to send a video note that exists on the Telegram servers (recommended) or upload a new video using multipart/form-data. More info on Sending Files ». Sending video notes by a URL is currently unsupported
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVideoNote(Object chat_id, String video_note) throws TelegramException {
        return sendVideoNote(chat_id, video_note, null, null, false, null, null, null);
    }
    
    /**
     * As of v.4.0, Telegram clients support rounded square mp4 videos of up to 1 minute long. Use this method to send video messages. On success, the sent Message is returned.
     * For this to work, your audio must be in an .ogg file encoded with OPUS (other formats may be sent as {@link Audio} or {@link Document}). 
     * On success, the sent {@link Message} is returned. Bots can currently send voice messages of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param video_note Video note to send. Pass a file_id as String to send a video note that exists on the Telegram servers (recommended) or upload a new video using multipart/form-data. More info on Sending Files ». Sending video notes by a URL is currently unsupported
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVideoNote(Object chat_id, java.io.File video_note) throws TelegramException {
        return sendVideoNote(chat_id, video_note, null, null, false, null, null, null);
    }

    /**
     * As of v.4.0, Telegram clients support rounded square mp4 videos of up to 1 minute long. Use this method to send video messages. On success, the sent Message is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param video_note Video note to send. Pass a file_id as String to send a video note that exists on the Telegram servers (recommended) or upload a new video using multipart/form-data. More info on Sending Files ». Sending video notes by a URL is currently unsupported
     * @param duration Duration of sent video in seconds
     * @param length Video width and height
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVideoNote(Object chat_id, Object video_note, Integer duration, Integer length, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendVideoNote(chat_id, null, video_note, duration, length, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * As of v.4.0, Telegram clients support rounded square mp4 videos of up to 1 minute long. Use this method to send video messages. On success, the sent Message is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param video_note Video note to send. Pass a file_id as String to send a video note that exists on the Telegram servers (recommended) or upload a new video using multipart/form-data. More info on Sending Files ». Sending video notes by a URL is currently unsupported
     * @param duration Duration of sent video in seconds
     * @param length Video width and height
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendVideoNote(Object chat_id, Integer message_thread_id, Object video_note, Integer duration, Integer length, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to send information about a venue. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param latitude Latitude of the venue
     * @param longitude Longitude of the venue
     * @param title Name of the venue
     * @param address Address of the venue
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVenue(Object chat_id, Float latitude, Float longitude, String title, String address) throws TelegramException {
        return sendVenue(chat_id, latitude, longitude, title, address, null, null, false, null, null, null);
    }

    /**
     * Use this method to send information about a venue. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param latitude Latitude of the venue
     * @param longitude Longitude of the venue
     * @param title Name of the venue
     * @param address Address of the venue
     * @param foursquare_id Foursquare identifier of the venue
     * @param foursquare_type Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendVenue(Object chat_id, Float latitude, Float longitude, String title, String address, String foursquare_id, String foursquare_type, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendVenue(chat_id, null, latitude, longitude, title, address, foursquare_id, foursquare_type, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send information about a venue. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param latitude Latitude of the venue
     * @param longitude Longitude of the venue
     * @param title Name of the venue
     * @param address Address of the venue
     * @param foursquare_id Foursquare identifier of the venue
     * @param foursquare_type Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendVenue(Object chat_id, Integer message_thread_id, Float latitude, Float longitude, String title, String address, String foursquare_id, String foursquare_type, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to send phone contacts. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param phone_number Contact's phone number
     * @param first_name Contact's first name
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendContact(Object chat_id, String phone_number, String first_name)  throws TelegramException {
        return sendContact(chat_id, phone_number, first_name, null, null, false, null, null, null);
    }

    /**
     * Use this method to send phone contacts. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param phone_number Contact's phone number
     * @param first_name Contact's first name
     * @param last_name Contact's last name
     * @param vcard Additional data about the contact in the form of a vCard, 0-2048 bytes
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendContact(Object chat_id, String phone_number, String first_name, String last_name, String vcard, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendContact(chat_id, null, phone_number, first_name, last_name, vcard, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send phone contacts. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param phone_number Contact's phone number
     * @param first_name Contact's first name
     * @param last_name Contact's last name
     * @param vcard Additional data about the contact in the form of a vCard, 0-2048 bytes
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendContact(Object chat_id, Integer message_thread_id, String phone_number, String first_name, String last_name, String vcard, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to send a native poll. A native poll can't be sent to a private chat. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername). A native poll can't be sent to a private chat.
     * @param question Poll question, 1-255 characters
     * @param options List of answer options, 2-10 strings 1-100 characters each
     * @return {@link Message}
     * @throws TelegramException if the method fails in Telegram servers
     */
    default Message sendPoll(Object chat_id, String question, List<String> options) throws TelegramException {
        return sendPoll(chat_id, question, options, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * Use this method to send a native poll. A native poll can't be sent to a private chat. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername). A native poll can't be sent to a private chat.
     * @param question Poll question, 1-255 characters
     * @param options List of answer options, 2-10 strings 1-100 characters each
     * @param is_anonymous True, if the poll needs to be anonymous, defaults to True
     * @param type Poll type, “quiz” or “regular”, defaults to “regular”
     * @param allows_multiple_answers True, if the poll allows multiple answers, ignored for polls in quiz mode, defaults to False
     * @param correct_option_id 0-based identifier of the correct answer option, required for polls in quiz mode
     * @param explanation Text that is shown when a user chooses an incorrect answer or taps on the lamp icon in a quiz-style poll, 0-200 characters with at most 2 line feeds after entities parsing
     * @param explanation_parse_mode Mode for parsing entities in the explanation. See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     * @param open_period Amount of time in seconds the poll will be active after creation, 5-600. Can't be used together with close_date.
     * @param close_date Point in time (Unix timestamp) when the poll will be automatically closed. Must be at least 5 and no more than 600 seconds in the future. Can't be used together with open_period.
     * @param is_closed Pass True, if the poll needs to be immediately closed
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws TelegramException if the method fails in Telegram servers
     */
    default Message sendPoll(Object chat_id, String question, List<String> options, Boolean is_anonymous, String type, Boolean allows_multiple_answers,
                     Integer correct_option_id, String explanation, String explanation_parse_mode, Integer open_period, Integer close_date,
                     Boolean is_closed, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendPoll(chat_id, null, question, options, is_anonymous, type, allows_multiple_answers, correct_option_id, explanation,
                explanation_parse_mode, open_period, close_date, is_closed, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send a native poll. A native poll can't be sent to a private chat. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername). A native poll can't be sent to a private chat.
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param question Poll question, 1-255 characters
     * @param options List of answer options, 2-10 strings 1-100 characters each
     * @param is_anonymous True, if the poll needs to be anonymous, defaults to True
     * @param type Poll type, “quiz” or “regular”, defaults to “regular”
     * @param allows_multiple_answers True, if the poll allows multiple answers, ignored for polls in quiz mode, defaults to False
     * @param correct_option_id 0-based identifier of the correct answer option, required for polls in quiz mode
     * @param explanation Text that is shown when a user chooses an incorrect answer or taps on the lamp icon in a quiz-style poll, 0-200 characters with at most 2 line feeds after entities parsing
     * @param explanation_parse_mode Mode for parsing entities in the explanation. See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     * @param open_period Amount of time in seconds the poll will be active after creation, 5-600. Can't be used together with close_date.
     * @param close_date Point in time (Unix timestamp) when the poll will be automatically closed. Must be at least 5 and no more than 600 seconds in the future. Can't be used together with open_period.
     * @param is_closed Pass True, if the poll needs to be immediately closed
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws TelegramException if the method fails in Telegram servers
     */
    Message sendPoll(Object chat_id, Integer message_thread_id, String question, List<String> options, Boolean is_anonymous, String type, Boolean allows_multiple_answers,
                     Integer correct_option_id, String explanation, String explanation_parse_mode, Integer open_period, Integer close_date,
                     Boolean is_closed, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;

    /**
     * Use this method to send a dice, which will have a random value from 1 to 6.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return On success, the sent Message is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    default Message sendDice(Object chat_id) throws TelegramException {
        return sendDice(chat_id, null, null, null, null, null);
    }

    /**
     * Use this method to send a dice, which will have a random value from 1 to 6.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param emoji Emoji on which the dice throw animation is based. Currently, must be one of “🎲”, “🎯”, “🏀”, “⚽”, “🎳”, or “🎰”. Dice can have values 1-6 for “🎲”, “🎯” and “🎳”, values 1-5 for “🏀” and “⚽”, and values 1-64 for “🎰”. Defaults to “🎲”
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
     * @return On success, the sent Message is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    default Message sendDice(Object chat_id, String emoji, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendDice(chat_id, null, emoji, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send a dice, which will have a random value from 1 to 6.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param emoji Emoji on which the dice throw animation is based. Currently, must be one of “🎲”, “🎯”, “🏀”, “⚽”, “🎳”, or “🎰”. Dice can have values 1-6 for “🎲”, “🎯” and “🎳”, values 1-5 for “🏀” and “⚽”, and values 1-64 for “🎰”. Defaults to “🎲”
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param protect_content Protects the contents of the sent message from forwarding and saving.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
     * @return On success, the sent Message is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Message sendDice(Object chat_id, Integer message_thread_id, String emoji, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;

    /**
     * Use this method when you need to tell the user that something is happening on the bot's side. 
     * The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status).
     * We only recommend using this method when a response from the bot will take a noticeable amount of time to arrive.
     * Watch more in https://core.telegram.org/bots/api#sendchataction
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param action Type of action to broadcast.
     *               Choose one, depending on what the user is about to receive: typing for text messages, upload_photo for photos,
     *               record_video or upload_video for videos, record_voice or upload_voice for voice notes, upload_document for general files,
     *               choose_sticker for stickers, find_location for location data, record_video_note or upload_video_note for video notes.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean sendChatAction(Object chat_id, String action) throws TelegramException {
        return sendChatAction(chat_id, null, action);
    }

    /**
     * Use this method when you need to tell the user that something is happening on the bot's side.
     * The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status).
     * We only recommend using this method when a response from the bot will take a noticeable amount of time to arrive.
     * Watch more in https://core.telegram.org/bots/api#sendchataction
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread; supergroups only
     * @param action Type of action to broadcast.
     *               Choose one, depending on what the user is about to receive: typing for text messages, upload_photo for photos,
     *               record_video or upload_video for videos, record_voice or upload_voice for voice notes, upload_document for general files,
     *               choose_sticker for stickers, find_location for location data, record_video_note or upload_video_note for video notes.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean sendChatAction(Object chat_id, Integer message_thread_id, String action) throws TelegramException;
    
    /**
     * Types of ChatAction. Idea by Rainu
     */
    enum ChatAction {
        typing, upload_photo, record_video, upload_video, record_audio,
        upload_audio, upload_document, find_location, record_video_note, upload_video_note
    }
    
    /**
     * Use this method when you need to tell the user that something is happening on the bot's side. 
     * The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status).
     * We only recommend using this method when a response from the bot will take a noticeable amount of time to arrive.
     * Watch more in https://core.telegram.org/bots/api#sendchataction
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param action Type of action to broadcast.
     *               Choose one, depending on what the user is about to receive: typing for text messages, upload_photo for photos,
     *               record_video or upload_video for videos, record_voice or upload_voice for voice notes, upload_document for general files,
     *               choose_sticker for stickers, find_location for location data, record_video_note or upload_video_note for video notes.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean sendChatAction(Object chat_id, ChatAction action) throws TelegramException {
        return sendChatAction(chat_id, action.name());
    }
    
    /**
     * Use this method to get a list of profile pictures for a user. Returns a UserProfilePhotos object.
     *
     * @param user_id Unique identifier of the target user
     * @return {@link UserProfilePhotos}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default UserProfilePhotos getUserProfilePhotos(Long user_id) throws TelegramException {
        return getUserProfilePhotos(user_id, null, null);
    }

    /**
     * Use this method to get a list of profile pictures for a user. Returns a UserProfilePhotos object.
     *
     * @param user_id Unique identifier of the target user
     * @param offset Sequential number of the first photo to be returned. By default, all photos are returned.
     * @param limit Limits the number of photos to be retrieved. Values between 1—100 are accepted. Defaults to 100.
     * @return {@link UserProfilePhotos}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    UserProfilePhotos getUserProfilePhotos(Long user_id, Integer offset, Integer limit) throws TelegramException;
    
    /**
     * Use this method to get basic info about a file and prepare it for downloading.
     * For the moment, bots can download files of up to 20MB in size. On success, a File object is returned.
     * The file can then be downloaded via the link https://api.telegram.org/file/bot&lt;token&gt;/&lt;file_path&gt;,
     * where &lt;file_path&gt; is taken from the response. It is guaranteed that the link will be valid for at least 1 hour.
     * When the link expires, a new one can be requested by calling getFile again.
     *
     * @param file_id File identifier to get info about
     * @return {@link File}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    File getFile(String file_id) throws TelegramException;
    
    /**
     * Use this method to kick a user from a group or a supergroup. In the case of supergroups, 
     *      the user will not be able to return to the group on their own using invite links, etc., unless unbanned first. 
     * The bot must be an administrator in the group for this to work. Returns True on success.
     * @param chat_id Unique identifier for the target group or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean banChatMember(Object chat_id, Long user_id) throws TelegramException {
        return banChatMember(chat_id, user_id, null, null);
    }

    /**
     * Use this method to kick a user from a group or a supergroup. In the case of supergroups,
     *      the user will not be able to return to the group on their own using invite links, etc., unless unbanned first.
     * The bot must be an administrator in the group for this to work. Returns True on success.
     * @param chat_id Unique identifier for the target group or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param until_date Optional. Date when the user will be unbanned, unix time. If user is banned for more than 366 days or less than 30 seconds from the current time they are considered to be banned forever
     * @param revoke_messages Optional. Pass True to delete all messages from the chat for the user that is being removed. If False, the user will be able to see messages in the group that were sent before the user was removed. Always True for supergroups and channels.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean banChatMember(Object chat_id, Long user_id, Integer until_date, Boolean revoke_messages) throws TelegramException;
    
    /**
     * Use this method for your bot to leave a group, supergroup or channel. Returns True on success.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean leaveChat(Object chat_id) throws TelegramException;
    
    /**
     * Use this method to unban a previously kicked user in a supergroup or channel. 
     * The user will not return to the group or channel automatically, but will be able to join via link, etc. 
     * The bot must be an administrator for this to work. Returns True on success.
     * @param chat_id Unique identifier for the target group or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean unbanChatMember(Object chat_id, Long user_id) throws TelegramException {
        return unbanChatMember(chat_id, user_id);
    }

    /**
     * Use this method to unban a previously kicked user in a supergroup or channel.
     * The user will not return to the group or channel automatically, but will be able to join via link, etc.
     * The bot must be an administrator for this to work. Returns True on success.
     * @param chat_id Unique identifier for the target group or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param only_if_banned Do nothing if the user is not banned
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean unbanChatMember(Object chat_id, Long user_id, Boolean only_if_banned) throws TelegramException;

    /**
     * Use this method to restrict a user in a supergroup.The bot must be an administrator in the supergroup for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param permissions New user permissions
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean restrictChatMember(Object chat_id, Long user_id, ChatPermissions permissions) throws TelegramException {
        return restrictChatMember(chat_id, user_id, permissions, null, null);
    }

    /**
     * * Use this method to restrict a user in a supergroup. 
     * The bot must be an administrator in the supergroup for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param permissions New user permissions
     * @oaran 	Pass True if chat permissions are set independently. Otherwise, the can_send_other_messages and can_add_web_page_previews permissions will imply the can_send_messages, can_send_audios, can_send_documents, can_send_photos, can_send_videos, can_send_video_notes, and can_send_voice_notes permissions; the can_send_polls permission will imply the can_send_messages permission.
     * @param until_date Date when restrictions will be lifted for the user, unix time. If user is restricted for more than 366 days or less than 30 seconds from the current time, they are considered to be restricted forever
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean restrictChatMember(Object chat_id, Long user_id, ChatPermissions permissions, Boolean use_independent_chat_permissions, Integer until_date) throws TelegramException;

    /**
     * Use this method to promote or demote a user in a supergroup or a channel.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * Pass False for all boolean parameters to demote a user
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param is_anonymous Pass True, if the administrator's presence in the chat is hidden
     * @param can_manage_chat Pass True, if the administrator can access the chat event log, chat statistics, message statistics in channels, see channel members, see anonymous administrators in supergroups and ignore slow mode. Implied by any other administrator privilege
     * @param can_change_info Pass True, if the administrator can change chat title, photo and other settings
     * @param can_post_messages Pass True, if the administrator can create channel posts, channels only
     * @param can_edit_messages Pass True, if the administrator can edit messages of other users and can pin messages, channels only
     * @param can_delete_messages Pass True, if the administrator can delete messages of other users
     * @param can_manage_voice_chats Pass True, if the administrator can manage voice chats
     * @param can_invite_users Pass True, if the administrator can invite new users to the chat
     * @param can_restrict_members Pass True, if the administrator can restrict, ban or unban chat members
     * @param can_pin_messages Pass True, if the administrator can pin messages, supergroups only
     * @param can_promote_members Pass True, if the administrator can add new administrators with a subset of his own privileges or demote administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by him)
     * @param can_manage_topics Pass True if the user is allowed to create, rename, close, and reopen forum topics, supergroups only
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean promoteChatMember(Object chat_id, Long user_id, Boolean is_anonymous, Boolean can_manage_chat, Boolean can_change_info, Boolean can_post_messages, Boolean can_edit_messages,
                              Boolean can_delete_messages, Boolean can_manage_voice_chats, Boolean can_invite_users, Boolean can_restrict_members, Boolean can_pin_messages, Boolean can_promote_members, Boolean can_manage_topics) throws TelegramException;

    /**
     * Use this method to set a custom title for an administrator in a supergroup promoted by the bot
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param custom_title New custom title for the administrator; 0-16 characters, emoji are not allowed
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     * @return True on success.
     */
    Boolean setChatAdministratorCustomTitle(Object chat_id, Long user_id, String custom_title) throws TelegramException;

    /**
     * Use this method to ban a channel chat in a supergroup or a channel.
     * Until the chat is unbanned, the owner of the banned chat won't be able to send messages on behalf of any of their channels.
     * The bot must be an administrator in the supergroup or channel for this to work and must have the appropriate administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param sender_chat_id Unique identifier of the target sender chat
     * @return Returns True on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean banChatSenderChat(Object chat_id, String sender_chat_id) throws TelegramException;

    /**
     * Use this method to unban a previously banned channel chat in a supergroup or channel.
     * The bot must be an administrator for this to work and must have the appropriate administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param sender_chat_id nique identifier of the target sender chat
     * @return Returns True on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean unbanChatSenderChat(Object chat_id, String sender_chat_id) throws TelegramException;

    /**
     * Use this method to set default chat permissions for all members.
     * The bot must be an administrator in the group or a supergroup for this to work and must have the can_restrict_members admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param permissions New default chat permissions
     * @param use_independent_chat_permissions Pass True if chat permissions are set independently. Otherwise, the can_send_other_messages and can_add_web_page_previews permissions will imply the can_send_messages, can_send_audios, can_send_documents, can_send_photos, can_send_videos, can_send_video_notes, and can_send_voice_notes permissions; the can_send_polls permission will imply the can_send_messages permission.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setChatPermissions(Object chat_id, ChatPermissions permissions, Boolean use_independent_chat_permissions) throws TelegramException;
    
    /**
     * Use this method to generate a new invite link for a chat; any previously generated link is revoked.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return the new invite link as String on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    String exportChatInviteLink(Object chat_id) throws TelegramException;

    /**
     * Use this method to create an additional invite link for a chat.
     * The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights.
     * The link can be revoked using the method revokeChatInviteLink.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return the new invite link as {{@link ChatInviteLink }} object.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default ChatInviteLink createChatInviteLink(Object chat_id) throws TelegramException {
        return createChatInviteLink(chat_id, null, null, null, null);
    }

    /**
     * Use this method to create an additional invite link for a chat.
     * The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights.
     * The link can be revoked using the method revokeChatInviteLink.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param name Invite link name; 0-32 characters
     * @param expire_date Point in time (Unix timestamp) when the link will expire
     * @param member_limit Maximum number of users that can be members of the chat simultaneously after joining the chat via this invite link; 1-99999
     * @param creates_join_request True, if users joining the chat via the link need to be approved by chat administrators. If True, member_limit can't be specified
     * @return the new invite link as {{@link ChatInviteLink }} object.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    ChatInviteLink createChatInviteLink(Object chat_id, String name, Integer expire_date, Integer member_limit, Boolean creates_join_request) throws TelegramException;

    /**
     * Use this method to edit a non-primary invite link created by the bot.
     * The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param invite_link The invite link to edit
     * @param name Invite link name; 0-32 characters
     * @param expire_date Point in time (Unix timestamp) when the link will expire
     * @param member_limit Maximum number of users that can be members of the chat simultaneously after joining the chat via this invite link; 1-99999
     * @param creates_join_request True, if users joining the chat via the link need to be approved by chat administrators. If True, member_limit can't be specified
     * @return the edited invite link as a {{@link ChatInviteLink}} object.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    ChatInviteLink editChatInviteLink(Object chat_id, String invite_link, String name, Integer expire_date, Integer member_limit, Boolean creates_join_request) throws TelegramException;

    /**
     * Use this method to revoke an invite link created by the bot.
     * If the primary link is revoked, a new link is automatically generated.
     * The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights.
     * @param chat_id Unique identifier of the target chat or username of the target channel (in the format @channelusername)
     * @param invite_link The invite link to revoke
     * @return the revoked invite link as a {{@link ChatInviteLink}} object.
     * @throws  com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    ChatInviteLink revokeChatInviteLink(Object chat_id, String invite_link) throws TelegramException;

    /**
     * Use this method to approve a chat join request.
     * The bot must be an administrator in the chat for this to work and must have the can_invite_users administrator right.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param user_id Unique identifier of the target user
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean approveChatJoinRequest(Object chat_id, Long user_id) throws TelegramException;

    /**
     * Use this method to decline a chat join request.
     * The bot must be an administrator in the chat for this to work and must have the can_invite_users administrator right.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param user_id Unique identifier of the target user
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean declineChatJoinRequest(Object chat_id, Long user_id) throws TelegramException;

    /**
     * Use this method to set a new profile photo for the chat. 
     * Photos can't be changed for private chats.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.     * 
     * Note: In regular groups (non-supergroups), this method will only work if the ‘All Members Are Admins’ setting is off in the target group.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
     * @param photo New chat photo, uploaded using multipart/form-data
     * @return True on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setChatPhoto(Object chat_id, java.io.File photo) throws TelegramException;

    /**
     * Use this method to delete a chat photo. 
     * Photos can't be changed for private chats. 
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
     * @return True on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean deleteChatPhoto(Object chat_id) throws TelegramException;

    /**
     * Use this method to change the title of a chat.
     * Titles can't be changed for private chats.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.Note: In regular groups (non-supergroups), this method will only work if the ‘All Members Are Admins’ setting is off in the target group.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param title New chat title, 1-255 characters
     * @return True on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setChatTitle(Object chat_id, String title) throws TelegramException;

    /**
     * Use this method to change the description of a supergroup or a channel.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return True on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean setChatDescription(Object chat_id) throws TelegramException {
        return setChatDescription(chat_id, null);
    }

    /**
     * Use this method to change the description of a supergroup or a channel.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param description New chat description, 0-255 characters
     * @return True on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setChatDescription(Object chat_id, String description) throws TelegramException;

    /**
     * Use this method to pin a message in a group, a supergroup, or a channel. 
     * The bot must be an administrator in the chat for this to work and must have the ‘can_pin_messages’ admin right in the supergroup or ‘can_edit_messages’ admin right in the channel.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Identifier of a message to pin
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean pinChatMessage(Object chat_id, Integer message_id) throws TelegramException {
        return pinChatMessage(chat_id, message_id, false);
    }

    /**
     * Use this method to pin a message in a supergroup. 
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Identifier of a message to pin
     * @param disable_notification Pass True, if it is not necessary to send a notification to all group members about the new pinned message
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean pinChatMessage(Object chat_id, Integer message_id, Boolean disable_notification) throws TelegramException;

    /**
     * Use this method to remove a message from the list of pinned messages in a chat.
     * If the chat is not a private chat, the bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' admin right in a supergroup or 'can_edit_messages' admin right in a channel.
     * Returns True on success.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean unpinChatMessage(Object chat_id) throws TelegramException {
        return unpinChatMessage(chat_id, null);
    }

    /**
     * Use this method to remove a message from the list of pinned messages in a chat.
     * If the chat is not a private chat, the bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' admin right in a supergroup or 'can_edit_messages' admin right in a channel.
     * Returns True on success.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean unpinChatMessage(Object chat_id, Integer message_id) throws TelegramException;

    /**
     * Use this method to clear the list of pinned messages in a chat.
     * If the chat is not a private chat, the bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' admin right in a supergroup or 'can_edit_messages' admin right in a channel.
     * Returns True on success.
     * @param chat_id 	Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return On success, True is returned.
     * @throws TelegramException
     */
    Boolean unpinAllChatMessages(Object chat_id) throws TelegramException;
    
    /**
     * Use this method to get up to date information about the chat (current name of the user for one-on-one conversations, current username of a user, group or channel, etc.).
     * Returns a {@link Chat} object on success.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
     * @return {@link Chat}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Chat getChat(Object chat_id) throws TelegramException;
    
    /**
     * 
     * @param chat_id Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
     * @return Array of {@link ChatMember}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    List<ChatMember> getChatAdministrators(Object chat_id) throws TelegramException;
    
    /**
     * Use this method to get the number of members in a chat. Returns Int on success.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
     * @return Number of members in a chat
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Integer getChatMemberCount(Object chat_id) throws TelegramException;
    
    /**
     * 
     * @param chat_id Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
     * @param user_id Unique identifier of the target user
     * @return {@link ChatMember}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    ChatMember getChatMember(Object chat_id, Long user_id) throws TelegramException;
    
    /**
     * Use this method to set a new group sticker set for a supergroup. 
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights. 
     * Use the field can_set_sticker_set optionally returned in getChat requests to check if the bot can use this method. 
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param sticker_set_name Name of the sticker set to be set as the group sticker set
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setChatStickerSet(Object chat_id, String sticker_set_name) throws TelegramException;
    
    /**
     * Use this method to delete a group sticker set from a supergroup. 
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights. 
     * Use the field can_set_sticker_set optionally returned in getChat requests to check if the bot can use this method.
     * @param chat_id True on success.
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean deleteChatStickerSet(Object chat_id) throws TelegramException;


    /**
     * Use this method to get custom emoji stickers, which can be used as a forum topic icon by any user. Requires no parameters. Returns an Array of Sticker objects.
     * @return Array of {@link Sticker} objects.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    List<Sticker> getForumTopicIconStickers() throws TelegramException;

    /**
     * Use this method to create a topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param name Topic name, 1-128 characters
     * @return Returns information about the created topic as a {@link ForumTopic} object.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default ForumTopic createForumTopic(Object chat_id, String name) throws TelegramException {
        return createForumTopic(chat_id, name, null, null);
    }

    /**
     * Use this method to create a topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param name Topic name, 1-128 characters
     * @param icon_color Color of the topic icon in RGB format. Currently, must be one of 0x6FB9F0, 0xFFD67E, 0xCB86DB, 0x8EEE98, 0xFF93B2, or 0xFB6F5F
     * @param icon_custom_emoji_id Unique identifier of the custom emoji shown as the topic icon. Use {@link #getForumTopicIconStickers} to get all allowed custom emoji identifiers
     * @return Returns information about the created topic as a {@link ForumTopic} object.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    ForumTopic createForumTopic(Object chat_id, String name, Integer icon_color, String icon_custom_emoji_id) throws TelegramException;

    /**
     * Use this method to edit name and icon of a topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have can_manage_topics administrator rights, unless it is the creator of the topic.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param message_thread_id Unique identifier for the target message thread of the forum topic
     * @param name Optionl. New topic name, 0-128 characters. If not specified or empty, the current name of the topic will be kept
     * @param icon_custom_emoji_id Optional. New unique identifier of the custom emoji shown as the topic icon. Use getForumTopicIconStickers to get all allowed custom emoji identifiers. Pass an empty string to remove the icon. If not specified, the current icon will be kept
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean editForumTopic(Object chat_id, Integer message_thread_id, String name, String icon_custom_emoji_id) throws TelegramException;

    /**
     * Use this method to close an open topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param message_thread_id Unique identifier for the target message thread of the forum topic
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean closeForumTopic(Object chat_id, Integer message_thread_id) throws TelegramException;

    /**
     * Use this method to reopen a closed topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param message_thread_id Unique identifier for the target message thread of the forum topic
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean reopenForumTopic(Object chat_id, Integer message_thread_id) throws TelegramException;

    /**
     * Use this method to delete a forum topic along with all its messages in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_delete_messages administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param message_thread_id Unique identifier for the target message thread of the forum topic
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean deleteForumTopic(Object chat_id, Integer message_thread_id) throws TelegramException;

    /**
     * Use this method to clear the list of pinned messages in a forum topic.
     * The bot must be an administrator in the chat for this to work and must have the can_pin_messages administrator right in the supergroup.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param message_thread_id Unique identifier for the target message thread of the forum topic
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean unpinAllForumTopicMessages(Object chat_id, Integer message_thread_id) throws TelegramException;

    /**
     * Use this method to edit the name of the 'General' topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have can_manage_topics administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param name New name of the 'General' topic, 1-128 characters
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean editGeneralForumTopic(Object chat_id, String name) throws TelegramException;

    /**
     * Use this method to close an open 'General' topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean closeGeneralForumTopic(Object chat_id) throws TelegramException;

    /**
     * Use this method to reopen a closed 'General' topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights.
     * The topic will be automatically unhidden if it was hidden
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean reopenGeneralForumTopic(Object chat_id) throws TelegramException;

    /**
     * Use this method to hide the 'General' topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights.
     * The topic will be automatically closed if it was open.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean hideGeneralForumTopic(Object chat_id) throws TelegramException;

    /**
     * Use this method to unhide the 'General' topic in a forum supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the can_manage_topics administrator rights.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean unhideGeneralForumTopic(Object chat_id) throws TelegramException;
    /**
     * Use this method to send answers to callback queries sent from inline keyboards. 
     * The answer will be displayed to the user as a notification at the top of the chat screen or as an alert. 
     * On success, True is returned.
     * 
     * @param callback_query_id Unique identifier for the query to be answered
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean answerCallbackQuery(String callback_query_id) throws TelegramException {
        return answerCallbackQuery(callback_query_id, null, false, null, 0);
    }
    
    /**
     * Use this method to send answers to callback queries sent from inline keyboards. 
     * The answer will be displayed to the user as a notification at the top of the chat screen or as an alert. 
     * 
     * @param callback_query_id Unique identifier for the query to be answered
     * @param text Text of the notification. If not specified, nothing will be shown to the user, 0-200 characters
     * @param show_alert If true, an alert will be shown by the client instead of a notification at the top of the chat screen. Defaults to false.
     * @param url URL that will be opened by the user's client. 
            If you have created a Game and accepted the conditions via @Botfather, specify the URL that opens your game – note that this will only work if the query comes from a callback_game button.
            Otherwise, you may use links like telegram.me/your_bot?start=XXXX that open your bot with a parameter.
     * @param cache_time The maximum amount of time in seconds that the result of the callback query may be cached client-side. Telegram apps will support caching starting in version 3.14. Defaults to 0.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean answerCallbackQuery(String callback_query_id, String text, Boolean show_alert, String url, Integer cache_time) throws TelegramException;

    /**
     * Use this method to change the list of the bot's commands.
     * @param commands A JSON-serialized list of bot commands to be set as the list of the bot's commands. At most 100 commands can be specified.
     * @param scope A JSON-serialized object, describing scope of users for which the commands are relevant. Defaults to BotCommandScopeDefault.
     * @param language_code A two-letter ISO 639-1 language code. If empty, commands will be applied to all users from the given scope, for whose language there are no dedicated commands
     * @return True on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean setMyCommands(List<BotCommand> commands, BotCommandScope scope, String language_code) throws TelegramException;

    /**
     * Use this method to change the list of the bot's commands.
     * @param commands A JSON-serialized list of bot commands to be set as the list of the bot's commands. At most 100 commands can be specified.
     * @return True on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    default Boolean setMyCommands(List<BotCommand> commands) throws TelegramException {
        return setMyCommands(commands, null, null);
    }

    /**
     * Use this method to delete the list of the bot's commands for the given scope and user language. After deletion, higher level commands will be shown to affected users.
     * @param scope A JSON-serialized object, describing scope of users for which the commands are relevant. Defaults to BotCommandScopeDefault.
     * @param language_code A two-letter ISO 639-1 language code. If empty, commands will be applied to all users from the given scope, for whose language there are no dedicated commands
     * @return True on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean deleteMyCommands(BotCommandScope scope, String language_code) throws TelegramException;

    /**
     * Use this method to get the current list of the bot's commands.
     * @param scope A JSON-serialized object, describing scope of users. Defaults to BotCommandScopeDefault.
     * @param language_code A two-letter ISO 639-1 language code or an empty string
     * @return Array of BotCommand on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    List<BotCommand> getMyCommands(BotCommandScope scope, String language_code) throws TelegramException;

    /**
     * Use this method to get the current list of the bot's commands. Requires no parameters.
     * @return Array of BotCommand on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    default List<BotCommand> getMyCommands() throws TelegramException {
        return getMyCommands(null, null);
    }

    /**
     * Use this method to change the bot's description, which is shown in the chat with the bot if the chat is empty. Returns True on success.
     * @param description New bot description; 0-512 characters. Pass an empty string to remove the dedicated description for the given language.
     * @param language_code A two-letter ISO 639-1 language code. If empty, the description will be applied to all users for whose language there is no dedicated description.
     * @return True on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean setMyDescription(String description, String language_code) throws TelegramException;

    /**
     * Use this method to get the current bot description for the given user language.
     * @param language_code A two-letter ISO 639-1 language code or an empty string
     * @return {@link BotDescription} on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    BotDescription getMyDescription(String language_code) throws TelegramException;

    /**
     * Use this method to change the bot's short description, which is shown on the bot's profile page and is sent together with the link when users share the bot. Returns True on success.
     * @param short_description New short description for the bot; 0-120 characters. Pass an empty string to remove the dedicated short description for the given language.
     * @param language_code A two-letter ISO 639-1 language code. If empty, the short description will be applied to all users for whose language there is no dedicated short description.
     * @return True on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean setMyShortDescription(String short_description, String language_code) throws TelegramException;

    /**
     * Use this method to get the current bot short description for the given user language. Returns BotShortDescription on success.
     * @param language_code A two-letter ISO 639-1 language code or an empty string
     * @return {@link BotShortDescription} on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    BotShortDescription getMyShortDescription(String language_code) throws TelegramException;

    /**
     * Use this method to change the bot's menu button in a private chat, or the default menu button.
     * @param chat_id Unique identifier for the target private chat. If not specified, default bot's menu button will be changed
     * @param menu_button A JSON-serialized object for the bot's new menu button
     * @return True on success.
     */
    Boolean setChatMenuButton(Object chat_id, MenuButton menu_button) throws TelegramException;

    /**
     * Use this method to get the current value of the bot's menu button in a private chat, or the default menu button.
     * @param chat_id Unique identifier for the target private chat. If not specified, default bot's menu button will be changed
     * @return {@link MenuButton}
     * @throws TelegramException if the method fails in Telegram servers
     */
    MenuButton getChatMenuButton(Object chat_id) throws TelegramException;

    /**
     * Use this method to change the default administrator rights requested by the bot when it's added as an administrator to groups or channels.
     * These rights will be suggested to users, but they are free to modify the list before adding the bot.
     * @param rights A JSON-serialized object describing new default administrator rights. If not specified, the default administrator rights will be cleared.
     * @param for_channels Pass True to change the default administrator rights of the bot in channels. Otherwise, the default administrator rights of the bot for groups and supergroups will be changed.
     * @return True on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Boolean setMyDefaultAdministratorRights(ChatAdministratorRights rights, Boolean for_channels) throws TelegramException;

    /**
     * Use this method to get the current default administrator rights of the bot.
     * @param for_channels Pass True to get default administrator rights of the bot in channels. Otherwise, default administrator rights of the bot for groups and supergroups will be returned.
     * @return Returns ChatAdministratorRights on success.
     * @throws TelegramException if the method fails in Telegram servers
     */
    ChatAdministratorRights getMyDefaultAdministratorRights(Boolean for_channels) throws TelegramException;
    
    /**
     * Use this method to edit text and game messages sent by the bot or via the bot (for inline bots). 
     * On success, if edited message is sent by the bot, the edited {@link Message} is returned, otherwise True is returned.
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Unique identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @param text New text of the message
     * @param parse_mode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     * @param disable_web_page_preview Disables link previews for links in this message
     * @param reply_markup A JSON-serialized object for an inline keyboard.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message editMessageText(Object chat_id, Integer message_id, String inline_message_id, String text, ParseMode parse_mode, Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
    /**
     * Use this method to edit captions of messages sent by the bot or via the bot (for inline bots). 
     * On success, if edited message is sent by the bot, the edited Message is returned, otherwise True is returned.
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Unique identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @param caption New caption of the message
     * @param reply_markup A JSON-serialized object for an inline keyboard.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers 
     */
    Message editMessageCaption(Object chat_id, Integer message_id, String inline_message_id, String caption, ParseMode parseMode, InlineKeyboardMarkup reply_markup) throws TelegramException;

    /**
     * Use this method to edit audio, document, photo, or video messages.
     * If a message is a part of a message album, then it can be edited only to a photo or a video.
     * Otherwise, message type can be changed arbitrarily.
     * When inline message is edited, new file can't be uploaded. Use previously uploaded file via its file_id or specify a URL.
     * On success, if the edited message was sent by the bot, the edited Message is returned, otherwise True is returned.
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @param media A JSON-serialized object for a new media content of the message
     * @param reply_markup A JSON-serialized object for a new inline keyboard.
     * @return On success, True is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    Message editMessageMedia(Object chat_id, Integer message_id, String inline_message_id, InputMedia media, InlineKeyboardMarkup reply_markup) throws TelegramException;

    /**
     * Use this method to edit only the reply markup of messages sent by the bot or via the bot (for inline bots). 
     * On success, if edited message is sent by the bot, the edited Message is returned, otherwise True is returned.
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Unique identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @param reply_markup A JSON-serialized object for an inline keyboard.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers 
     */
    Message editMessageReplyMarkup(Object chat_id, Integer message_id, String inline_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
    /**
     * Use this method to stop a poll which was sent by the bot. On success, the stopped Poll with the final results is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Identifier of the original message with the poll
     * @return {@link Poll}
     * @throws TelegramException if the method fails in Telegram servers
     */
    default Poll stopPoll(Object chat_id, Integer message_id) throws TelegramException {
        return stopPoll(chat_id, message_id, null);
    }
    
   
    /**
     * Use this method to stop a poll which was sent by the bot. On success, the stopped Poll with the final results is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Identifier of the original message with the poll
     * @param reply_markup Optional. A JSON-serialized object for a new message inline keyboard.
     * @return {@link Poll}
     * @throws TelegramException if the method fails in Telegram servers
     */
    Poll stopPoll(Object chat_id, Integer message_id, InlineKeyboardMarkup reply_markup) throws TelegramException;

    /**
     * Use this method to delete a message, including service messages, with the following limitations:
     * - A message can only be deleted if it was sent less than 48 hours ago.
     * - Service messages about a supergroup, channel, or forum topic creation can't be deleted.
     * - A dice message in a private chat can only be deleted if it was sent more than 24 hours ago.
     * - Bots can delete outgoing messages in private chats, groups, and supergroups.
     * - Bots can delete incoming messages in private chats.
     * - Bots granted can_post_messages permissions can delete outgoing messages in channels.
     * - If the bot is an administrator of a group, it can delete any message there.
     * - If the bot has can_delete_messages permission in a supergroup or a channel, it can delete any message there.
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Unique identifier of the sent message
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers 
     */
    Boolean deleteMessage(Object chat_id, Integer message_id) throws TelegramException;
    
    /**
     * Use this method to receive incoming updates using long polling (wiki). An Array of {@link Update} objects is returned.
     * @param offset Identifier of the first update to be returned. 
     *              Must be greater by one than the highest among the identifiers of previously received updates. 
     *              By default, updates starting with the earliest unconfirmed update are returned.
     *              An update is considered confirmed as soon as getUpdates is called with an offset higher than its update_id.
     * Watch more in https://core.telegram.org/bots/api#getupdates
     * @param limit Optional. Limits the number of updates to be retrieved. Values between 1—100 are accepted. Defaults to 100
     * @param timeout Optional. Timeout in seconds for long polling. Defaults to 0, i.e. usual short polling
     * @param allowed_updates Optional. List the types of updates you want your bot to receive.
     *                        For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive updates of these types. 
     *                        See Update for a complete list of available update types. Specify an empty list to receive all updates regardless of type (default). 
     *                        If not specified, the previous setting will be used.
     * @return An Array of {@link Update} objects
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    List<Update> getUpdates(Integer offset, Integer limit, Integer timeout, List<String> allowed_updates) throws TelegramException;
    
    /**
     * Use this method to get current webhook status. Requires no parameters. 
     * On success, returns a {@link WebhookInfo} object.
     * If the bot is using {@link BotAPI#getUpdates}, will return an object with the url field empty.
     * @return {@link WebhookInfo}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    WebhookInfo getWebhookInfo() throws TelegramException;
    
    /**
     * Use this method to specify a url and receive incoming updates via an outgoing webhook. Whenever there is an
     * update for the bot, we will send an HTTPS POST request to the specified url, containing a JSON-serialized Update.
     * In case of an unsuccessful request, we will give up after a reasonable amount of attempts.
     * 
     * If you'd like to make sure that the Webhook request comes from Telegram, we recommend using a secret path in the URL,
     * e.g. https://www.example.com/&lt;token&gt;. Since nobody else knows your bot‘s token, you can be pretty sure it’s us.
     * 
     * <b>Notes</b>
     * <ul>
     * <li>You will not be able to receive updates using getUpdates for as long as an outgoing webhook is set up.ǘ
     * <li>We currently do not support self-signed certificates.</li>
     * <li>Ports currently supported for Webhooks: 443, 80, 88, 8443.</li>
     * </ul>
     *
     * @param url         HTTPS url to send updates to. Use an empty string to remove webhook integration
     * @param certificate Optional. Upload your public key certificate so that the root certificate in use can be checked.
     *                    See our <a href="https://core.telegram.org/bots/self-signed">self-signed guide</a> for details.
     * @param ip_address The fixed IP address which will be used to send webhook requests instead of the IP address resolved through DNS
     * @param max_connections Optional. Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100. Defaults to 40.
     *                        Use lower values to limit the load on your bot‘s server, and higher values to increase your bot’s throughput.
     * @param allowed_updates Optional. List the types of updates you want your bot to receive. 
     *                        For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive updates of these types.
     *                        See {@link Update} for a complete list of available update types. Specify an empty list to receive all updates regardless of type (default).
     *                        If not specified, the previous setting will be used.
     * @param drop_pending_updates Pass True to drop all pending updates
     * @param secret_token A secret token to be sent in a header “X-Telegram-Bot-Api-Secret-Token” in every webhook request, 1-256 characters. Only characters A-Z, a-z, 0-9, _ and - are allowed.
     *                     The header is useful to ensure that the request comes from a webhook set by you.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setWebhook(String url, java.io.File certificate, String ip_address, Integer max_connections, List<String> allowed_updates, Boolean drop_pending_updates, String secret_token) throws TelegramException;

    /**
     * Use this method to remove webhook integration if you decide to switch back to {@link BotAPI#getUpdates}. Returns True on success.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean deleteWebhook() throws TelegramException {
        return deleteWebhook(null);
    }

    /**
     * Use this method to remove webhook integration if you decide to switch back to {@link BotAPI#getUpdates}. Returns True on success.
     * @param drop_pending_updates Pass True to drop all pending updates
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean deleteWebhook(Boolean drop_pending_updates) throws TelegramException;
    
    /**
     * Use this method to send static .WEBP or animated .TGS stickers. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param sticker Sticker to send. You can either pass a file_id as String to resend a sticker that is already on the Telegram servers, 
     *                  or upload a new sticker using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendSticker(Object chat_id, String sticker) throws TelegramException {
        return sendSticker(chat_id, sticker, false, null, null, null);
    }
    
    /**
     * Use this method to send static .WEBP or animated .TGS stickers. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param sticker Sticker to send. You can either pass a file_id as String to resend a sticker that is already on the Telegram servers, 
     *                  or upload a new sticker using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendSticker(Object chat_id, java.io.File sticker) throws TelegramException {
        return sendSticker(chat_id, sticker, false, null, null, null);
    }
    
    /**
     * Use this method to send static .WEBP or animated .TGS stickers. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param sticker Sticker to send. You can either pass a file_id as String to resend a sticker that is already on the Telegram servers, 
     *                  or upload a new sticker using multipart/form-data.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendSticker(Object chat_id, Object sticker, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        return sendSticker(chat_id, sticker, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send static .WEBP or animated .TGS stickers. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param sticker Sticker to send. You can either pass a file_id as String to resend a sticker that is already on the Telegram servers,
     *                  or upload a new sticker using multipart/form-data.
     * @param emoji Emoji associated with the sticker; only for just uploaded stickers
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendSticker(Object chat_id, Integer message_thread_id, Object sticker, String emoji, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to get a sticker set. On success, a StickerSet object is returned.
     * @param name Name of the sticker set
     * @return StickerSet
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    StickerSet getStickerSet(String name) throws TelegramException;

    /**
     * Use this method to get information about custom emoji stickers by their identifiers.
     * @param custom_emoji_ids List of custom emoji identifiers. At most 200 custom emoji identifiers can be specified.
     * @return Array of Sticker objects.
     * @throws TelegramException com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    List<Sticker> getCustomEmojiStickers(List<String> custom_emoji_ids) throws TelegramException;

    /**
     * Use this method to upload a .png file with a sticker for later use in createNewStickerSet and addStickerToSet methods (can be used multiple times). Returns the uploaded File on success.
     * @param user_id User identifier of sticker file owner
     * @param png_sticker Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. 
     * @return The uploaded File on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    File uploadStickerFile(Long user_id, java.io.File png_sticker) throws TelegramException;
    
    /**
     * Use this method to create new sticker set owned by a user. The bot will be able to edit the created sticker set. Returns True on success.
     * @param user_id User identifier of created sticker set owner
     * @param name Short name of sticker set, to be used in t.me/addstickers/ URLs. Must begin with a letter, can't contain consecutive underscores and must end in “_by_&lt;bot username&gt;”. &lt;bot_username&gt; is case insensitive. 1-64 characters.
     * @param title Sticker set title, 1-64 characters
     * @param stickers A JSON-serialized list of 1-50 initial stickers to be added to the sticker set
     * @param sticker_format Format of stickers in the set, must be one of “static”, “animated”, “video”
     * @param sticker_type Type of stickers in the set, pass “regular”, “mask”, or “custom_emoji”. By default, a regular sticker set is created.
     * @param sticker_type Type of stickers in the set, pass “regular” or “mask”. Custom emoji sticker sets can't be created via the Bot API at the moment. By default, a regular sticker set is created.
     * @param needs_repainting Pass True if stickers in the sticker set must be repainted to the color of text when used in messages, the accent color if used as emoji status, white on chat photos, or another appropriate color based on context; for custom emoji sticker sets only
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean createNewStickerSet(Long user_id, String name, String title, List<InputSticker> stickers, String sticker_format, String sticker_type, Boolean needs_repainting) throws TelegramException;
    
    /**
     * Use this method to add a new sticker to a set created by the bot. The format of the added sticker must match the format of the other stickers in the set. Emoji sticker sets can have up to 200 stickers. Animated and video sticker sets can have up to 50 stickers. Static sticker sets can have up to 120 stickers. Returns True on success.
     * @param user_id User identifier of sticker set owner
     * @param name Sticker set name
     * @param sticker A JSON-serialized object with information about the added sticker. If exactly the same sticker had already been added to the set, then the set isn't changed.
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean addStickerToSet(Long user_id, String name, InputSticker sticker) throws TelegramException;
    
    /**
     * Use this method to move a sticker in a set created by the bot to a specific position . Returns True on success.
     * @param sticker File identifier of the sticker
     * @param position New sticker position in the set, zero-based
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setStickerPositionInSet(String sticker, Integer position) throws TelegramException;
    
    /**
     * Use this method to delete a sticker from a set created by the bot. Returns True on success.
     * @param sticker File identifier of the sticker
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean deleteStickerFromSet(String sticker) throws TelegramException;

    /**
     * Use this method to change the {@link com.cadiducho.telegrambotapi.sticker.MaskPosition} of a mask sticker. The sticker must belong to a sticker set that was created by the bot. Returns True on success.
     * @param name Sticker set name
     * @param mask_position A JSON-serialized object with the position where the mask should be placed on faces. Omit the parameter to remove the mask position.
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setStickerMaskPosition(String name, com.cadiducho.telegrambotapi.sticker.MaskPosition mask_position) throws TelegramException;

    /**
     * Use this method to set the title of a created sticker set. Returns True on success.
     * @param name Sticker set name
     * @param title Sticker set title, 1-64 characters
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setStickerSetTitle(String name, String title) throws TelegramException;

    /**
     * Use this method to set the thumbnail of a sticker set. Animated thumbnails can be set for animated sticker sets only
     * @param name Sticker set name
     * @param user_id User identifier of the sticker set owner
     * @param thumbnail A PNG image with the thumbnail, must be up to 128 kilobytes in size and have width and height exactly 100px, or a TGS animation with the thumbnail up to 32 kilobytes in size; see https://core.telegram.org/animated_stickers#technical-requirements for animated sticker technical requirements.
     *              Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using multipart/form-data.
     *              More info on Sending Files ». Animated sticker set thumbnail can't be uploaded via HTTP URL.
     * @return True on success.
     * @throws TelegramException
     */
    Boolean setStickerSetThumbnail(String name, Long user_id, java.io.File thumbnail) throws TelegramException;

    /**
     * Use this method to set the thumbnail of a sticker set. Animated thumbnails can be set for animated sticker sets only
     * @param name Sticker set name
     * @param user_id User identifier of the sticker set owner
     * @param thumbnail A PNG image with the thumbnail, must be up to 128 kilobytes in size and have width and height exactly 100px, or a TGS animation with the thumbnail up to 32 kilobytes in size; see https://core.telegram.org/animated_stickers#technical-requirements for animated sticker technical requirements.
     *              Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using multipart/form-data.
     *              More info on Sending Files ». Animated sticker set thumbnail can't be uploaded via HTTP URL.
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setStickerSetThumbnail(String name, Long user_id, String thumbnail) throws TelegramException;

    /**
     * Use this method to set the thumbnail of a custom emoji sticker set. Returns True on success.
     * @param name Sticker set name
     * @param custom_emoji_id Custom emoji identifier of a sticker from the sticker set; pass an empty string to drop the thumbnail and use the first sticker as the thumbnail.
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setCustomEmojiStickerSetThumbnail(String name, String custom_emoji_id) throws TelegramException;

    /**
     * Use this method to delete a sticker set that was created by the bot. Returns True on success.
     * @param name Sticker set name
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean deleteStickerSet(String name) throws TelegramException;

    /**
     * Use this method to change the list of emoji assigned to a regular or custom emoji sticker. The sticker must belong to a sticker set created by the bot. Returns True on success.
     * @param sticker File identifier of the sticker
     * @param emoji_list A JSON-serialized list of 1-20 emoji associated with the sticker
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setStickerEmojiList(String sticker, List<String> emoji_list) throws TelegramException;

    /**
     * Use this method to change search keywords assigned to a regular or custom emoji sticker. The sticker must belong to a sticker set created by the bot. Returns True on success.
     * @param sticker File identifier of the sticker
     * @param keywords A JSON-serialized list of 0-20 search keywords for the sticker with total length of up to 64 characters
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setStickerKeywords(String sticker, List<String> keywords) throws TelegramException;


    /**
     * Use this method to send answers to an inline query. On success, True is returned.
     * No more than <b>50</b> results per query are allowed.
     *
     * @param inlineQueryId Unique identifier for the answered query
     * @param results       A JSON-serialized array of results for the inline query
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results) throws TelegramException {
        return answerInlineQuery(inlineQueryId, results, null, null, null, null, null);
    }

    /**
     * Use this method to send answers to an inline query. On success, True is returned.
     * No more than <b>50</b> results per query are allowed.
     *
     * @param inlineQueryId Unique identifier for the answered query
     * @param results       A JSON-serialized array of results for the inline query
     * @param cache_time     The maximum amount of time in seconds that the result of the inline query may be cached on
     *                      the server. Defaults to 300.
     * @param is_personal    Pass <i>True</i>, if results may be cached on the server side only for the user that sent
     *                      the query. By default, results may be returned to any user who sends the same query
     * @param next_offset    Pass the offset that a client should send in the next query with the same text to receive
     *                      more results. Pass an empty string if there are no more results or if you don‘t support
     *                      pagination. Offset length can’t exceed 64 bytes.
     * @param switch_pm_text If passed, clients will display a button with specified text that switches the user to a private chat 
     *                      with the bot and sends the bot a start message with the parameter switch_pm_parameter
     * @param switch_pm_parameter Deep-linking parameter for the /start message sent to the bot when user presses the switch button. 1-64 characters, only A-Z, a-z, 0-9, _ and - are allowed.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, Integer cache_time, Boolean is_personal, String next_offset,
                              String switch_pm_text, String switch_pm_parameter) throws TelegramException;

    /**
     * Use this method to set the result of an interaction with a Web App and send a corresponding message on behalf of the user to the chat from which the query originated.
     * @param web_app_query_id Unique identifier for the query to be answered
     * @param result A JSON-serialized object describing the message to be sent
     * @return On success, a SentWebAppMessage object is returned.
     * @throws TelegramException if the method fails in Telegram servers
     */
    SentWebAppMessage answerWebAppQuery(String web_app_query_id, InlineQueryResult result) throws TelegramException;

    /**
     * Use this method to send invoices. On success, the sent Message is returned.
     * @param chat_id Unique identifier for the target private chat
     * @param title Product name
     * @param description Product description
     * @param payload Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal processes.
     * @param provider_token Payments provider token, obtained via Botfather
     * @param currency Three-letter ISO 4217 currency code, see more on https://core.telegram.org/bots/payments#supported-currencies
     * @param prices Price breakdown, a list of components (e.g. product price, tax, discount, delivery cost, delivery tax, bonus, etc.)
     * @param max_tip_amount The maximum accepted amount for tips in the smallest units of the currency (integer, not float/double). For example, for a maximum tip of US$ 1.45 pass max_tip_amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies). Defaults to 0
     * @param suggested_tip_amounts A JSON-serialized array of suggested amounts of tips in the smallest units of the currency (integer, not float/double). At most 4 suggested tip amounts can be specified. The suggested tip amounts must be positive, passed in a strictly increased order and must not exceed max_tip_amount.
     * @param start_parameter Unique deep-linking parameter. If left empty, forwarded copies of the sent message will have a Pay button, allowing multiple users to pay directly from the forwarded message, using the same invoice. If non-empty, forwarded copies of the sent message will have a URL button with a deep link to the bot (instead of a Pay button), with the value used as the start parameter
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendInvoice(Integer chat_id, String title, String description, String payload, String provider_token, String currency,
                        List<LabeledPrice> prices, Integer max_tip_amount, List<Integer> suggested_tip_amounts, String start_parameter) throws TelegramException {
        return sendInvoice(chat_id, title, description, payload, provider_token, start_parameter, currency, prices, null, null, null, null, null, false, false, false, false, false, false, false, false, null, null, null);
    }

    /**
     * Use this method to send invoices. On success, the sent Message is returned.
     * @param chat_id Unique identifier for the target private chat
     * @param title Product name
     * @param description Product description
     * @param payload Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal processes.
     * @param provider_token Payments provider token, obtained via Botfather
     * @param start_parameter Unique deep-linking parameter that can be used to generate this invoice when used as a start parameter
     * @param currency Three-letter ISO 4217 currency code, see more on https://core.telegram.org/bots/payments#supported-currencies
     * @param prices Price breakdown, a list of components (e.g. product price, tax, discount, delivery cost, delivery tax, bonus, etc.)
     * @param provider_data JSON-encoded data about the invoice, which will be shared with the payment provider. A detailed description of required fields should be provided by the payment provider.
     * @param photo_url URL of the product photo for the invoice. Can be a photo of the goods or a marketing image for a service. People like it better when they see what they are paying for.
     * @param photo_size Photo size
     * @param photo_width Photo width
     * @param photo_height Photo height
     * @param need_name Pass True, if you require the user's full name to complete the order
     * @param need_phone_number Pass True, if you require the user's phone number to complete the order
     * @param need_email Pass True, if you require the user's email to complete the order
     * @param need_shipping_address Pass True, if you require the user's shipping address to complete the order
     * @param send_phone_number_to_provider Pass True, if user's phone number should be sent to provider
     * @param send_email_to_provider Pass True, if user's email address should be sent to provider
     * @param is_flexible Pass True, if the final price depends on the shipping method
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup A JSON-serialized object for an inline keyboard. If empty, one 'Pay total price' button will be shown. If not empty, the first button must be a Pay button.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendInvoice(Integer chat_id, String title, String description, String payload, String provider_token, String start_parameter, String currency,
                        List<LabeledPrice> prices, String provider_data, String photo_url, Integer photo_size, Integer photo_width, Integer photo_height, Boolean need_name, Boolean need_phone_number,
                        Boolean need_email, Boolean need_shipping_address, Boolean send_phone_number_to_provider, Boolean send_email_to_provider, Boolean is_flexible, Boolean disable_notification,
                        Boolean protect_content, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        return sendInvoice(chat_id, null, title, description, payload, provider_token, start_parameter, currency, prices, provider_data, photo_url, photo_size, photo_width,
                photo_height, need_name, need_phone_number, need_email, need_shipping_address, send_phone_number_to_provider, send_email_to_provider, is_flexible, disable_notification,
                protect_content, reply_to_message_id, reply_markup);
    }


    /**
     * Use this method to send invoices. On success, the sent Message is returned.
     * @param chat_id Unique identifier for the target private chat
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param title Product name
     * @param description Product description
     * @param payload Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal processes.
     * @param provider_token Payments provider token, obtained via Botfather
     * @param start_parameter Unique deep-linking parameter that can be used to generate this invoice when used as a start parameter
     * @param currency Three-letter ISO 4217 currency code, see more on https://core.telegram.org/bots/payments#supported-currencies
     * @param prices Price breakdown, a list of components (e.g. product price, tax, discount, delivery cost, delivery tax, bonus, etc.)
     * @param provider_data JSON-encoded data about the invoice, which will be shared with the payment provider. A detailed description of required fields should be provided by the payment provider.
     * @param photo_url URL of the product photo for the invoice. Can be a photo of the goods or a marketing image for a service. People like it better when they see what they are paying for.
     * @param photo_size Photo size
     * @param photo_width Photo width
     * @param photo_height Photo height
     * @param need_name Pass True, if you require the user's full name to complete the order
     * @param need_phone_number Pass True, if you require the user's phone number to complete the order
     * @param need_email Pass True, if you require the user's email to complete the order
     * @param need_shipping_address Pass True, if you require the user's shipping address to complete the order
     * @param send_phone_number_to_provider Pass True, if user's phone number should be sent to provider
     * @param send_email_to_provider Pass True, if user's email address should be sent to provider
     * @param is_flexible Pass True, if the final price depends on the shipping method
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup A JSON-serialized object for an inline keyboard. If empty, one 'Pay total price' button will be shown. If not empty, the first button must be a Pay button.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendInvoice(Integer chat_id, Integer message_thread_id, String title, String description, String payload, String provider_token, String start_parameter, String currency,
                                    List<LabeledPrice> prices, String provider_data, String photo_url, Integer photo_size, Integer photo_width, Integer photo_height, Boolean need_name, Boolean need_phone_number,
                                    Boolean need_email, Boolean need_shipping_address, Boolean send_phone_number_to_provider, Boolean send_email_to_provider, Boolean is_flexible, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException;

    default String createInvoiceLink(String title, String description, String payload, String provider_token, String currency, List<LabeledPrice> prices) throws TelegramException {
        return createInvoiceLink(title, description, payload, provider_token, currency, prices, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    String createInvoiceLink(String title, String description, String payload, String provider_token, String currency, List<LabeledPrice> prices, Integer max_tip_amount,
                             List<Integer> suggested_tip_amounts, String provider_data, String photo_url, Integer photo_size, Integer photo_width, Integer photo_height,
                             Boolean need_name, Boolean need_phone_number, Boolean need_email, Boolean need_shipping_address, Boolean send_phone_number_to_provider,
                             Boolean send_email_to_provider, Boolean is_flexible) throws TelegramException;

    /**
     * If you sent an invoice requesting a shipping address and the parameter is_flexible was specified, the Bot API will send an Update with a shipping_query field to the bot. 
     * Use this method to reply to shipping queries.
     * On success, True is returned.
     * @param shipping_query_id Unique identifier for the query to be answered
     * @param ok Specify True if delivery to the specified address is possible and False if there are any problems (for example, if delivery to the specified address is not possible)
     * @return True on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean answerShippingQuery(String shipping_query_id, Boolean ok) throws TelegramException {
        return answerShippingQuery(shipping_query_id, ok, null, null);
    }
    
    /**
     * If you sent an invoice requesting a shipping address and the parameter is_flexible was specified, the Bot API will send an Update with a shipping_query field to the bot. 
     * Use this method to reply to shipping queries.
     * On success, True is returned.
     * @param shipping_query_id Unique identifier for the query to be answered
     * @param ok Specify True if delivery to the specified address is possible and False if there are any problems (for example, if delivery to the specified address is not possible)
     * @param shipping_options Required if ok is True. A JSON-serialized array of available shipping options.
     * @param error_message Required if ok is False. Error message in human readable form that explains why it is impossible to complete the order (e.g. "Sorry, delivery to your desired address is unavailable'). Telegram will display this message to the user.
     * @return True on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean answerShippingQuery(String shipping_query_id, Boolean ok, List<ShippingOption> shipping_options, String error_message) throws TelegramException;
    
    /**
     * Once the user has confirmed their payment and shipping details, the Bot API sends the final confirmation in the form of an Update with the field pre_checkout_query. Use this method to respond to such pre-checkout queries.
     * Note: The Bot API must receive an answer within 10 seconds after the pre-checkout query was sent.
     * @param pre_checkout_query_id Unique identifier for the query to be answered
     * @param ok Specify True if everything is alright (goods are available, etc.) and the bot is ready to proceed with the order. Use False if there are any problems.
     * @return On success, True is returned
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean answerPreCheckoutQuery(String pre_checkout_query_id, Boolean ok) throws TelegramException {
        return answerPreCheckoutQuery(pre_checkout_query_id, ok, null);
    }
    
    /**
     * Once the user has confirmed their payment and shipping details, the Bot API sends the final confirmation in the form of an Update with the field pre_checkout_query. Use this method to respond to such pre-checkout queries.
     * Note: The Bot API must receive an answer within 10 seconds after the pre-checkout query was sent.
     * @param pre_checkout_query_id Unique identifier for the query to be answered
     * @param ok Specify True if everything is alright (goods are available, etc.) and the bot is ready to proceed with the order. Use False if there are any problems.
     * @param error_message Required if ok is False. Error message in human readable form that explains the reason for failure to proceed with the checkout (e.g. "Sorry, somebody just bought the last of our amazing black T-shirts while you were busy filling out your payment details. Please choose a different color or garment!"). Telegram will display this message to the user.
     * @return On success, True is returned
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean answerPreCheckoutQuery(String pre_checkout_query_id, Boolean ok, String error_message) throws TelegramException;
    
    /**
     * Use this method to send a game. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat
     * @param game_short_name Short name of the game, serves as the unique identifier for the game.
     * @return On success, the sent message
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendGame(Object chat_id, String game_short_name) throws TelegramException {
        return sendGame(chat_id, game_short_name, null, null, null, null);
    }

    /**
     * Use this method to send a game. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat
     * @param game_short_name Short name of the game, serves as the unique identifier for the game.
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup A JSON-serialized object for an inline keyboard. If empty, one ‘Play game_title’ button will be shown. If not empty, the first button must launch the game.
     * @return On success, the sent message
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendGame(Object chat_id, String game_short_name, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        return sendDice(chat_id, null, game_short_name, disable_notification, protect_content, reply_to_message_id, reply_markup);
    }

    /**
     * Use this method to send a game. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat
     * @param message_thread_id Optional. Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
     * @param game_short_name Short name of the game, serves as the unique identifier for the game.
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup A JSON-serialized object for an inline keyboard. If empty, one ‘Play game_title’ button will be shown. If not empty, the first button must launch the game.
     * @return On success, the sent message
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendGame(Object chat_id, Integer message_thread_id, String game_short_name, Boolean disable_notification, Boolean protect_content, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
    /**
     * Use this method to set the score of the specified user in a game. 
     * On success, if the message was sent by the bot, returns the edited Message, otherwise returns True. 
     * Returns an error, if the new score is not greater than the user's current score in the chat and force is False.
     * @param user_id User identifier
     * @param score New score, must be non-negative
     * @return the Message, true or false
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Object setGameScore(Long user_id, Integer score) throws TelegramException {
        return setGameScore(user_id, score, null, null, null, null, null);
    }
    
    /**
     * Use this method to set the score of the specified user in a game. 
     * On success, if the message was sent by the bot, returns the edited Message, otherwise returns True. 
     * Returns an error, if the new score is not greater than the user's current score in the chat and force is False.
     * @param user_id User identifier
     * @param score New score, must be non-negative
     * @param force Pass True, if the high score is allowed to decrease. This can be useful when fixing mistakes or banning cheaters
     * @param disable_edit_message Pass True, if the game message should not be automatically edited to include the current scoreboard
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat
     * @param message_id Required if inline_message_id is not specified. Identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @return the Message, true or false
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Object setGameScore(Long user_id, Integer score, Boolean force, Boolean disable_edit_message, Object chat_id, Integer message_id, String inline_message_id) throws TelegramException;
    
    /**
     * Use this method to get data for high score tables.
     * Will return the score of the specified user and several of his neighbors in a game. 
     * @param user_id Target user id
     * @return On success, returns an Array of GameHighScore objects.
     * @throws TelegramException if the method fails in Telegram servers
     */
    default List<GameHighScore> getGameHighScores(Long user_id) throws TelegramException {
        return getGameHighScores(user_id, null, null, null);
    }
    
    /**
     * Use this method to get data for high score tables.
     * Will return the score of the specified user and several of his neighbors in a game. 
     * @param user_id Target user id
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat
     * @param message_id Required if inline_message_id is not specified. Identifier of the sent message
     * @param inline_message_id	Required if chat_id and message_id are not specified. Identifier of the inline message
     * @return On success, returns an Array of GameHighScore objects.
     * @throws TelegramException if the method fails in Telegram servers
     */
    List<GameHighScore> getGameHighScores(Long user_id, Object chat_id, Integer message_id, String inline_message_id) throws TelegramException;
}
