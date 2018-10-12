/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.cadiducho.telegrambotapi.exception.TelegramException;
import com.cadiducho.telegrambotapi.game.GameHighScore;
import com.cadiducho.telegrambotapi.handlers.BotUpdatesPoller;
import com.cadiducho.telegrambotapi.inline.InlineKeyboardMarkup;
import com.cadiducho.telegrambotapi.inline.InlineQueryResult;
import com.cadiducho.telegrambotapi.payment.LabeledPrice;
import com.cadiducho.telegrambotapi.payment.ShippingOption;

import java.util.List;

/**
 * Interface to build Telegrams Bots 
 * Telegram Bot API version 3.6
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
     *
     * @return
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
     * Use this method to send text messages. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param text Text of the message to be sent
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendMessage(Object chat_id, String text) throws TelegramException {
        return sendMessage(chat_id, text, null, null, false, null, null);
    }
    
    /**
     * Use this method to send text messages. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param text Text of the message to be sent
     * @param parse_mode Send Markdown, if you want Telegram apps to show bold, italic and inline URLs in your bot's message. For the moment, only Telegram for Android supports this.
     *                  See https://core.telegram.org/bots/api#using-markdown
     * @param disable_web_page_preview Disables link previews for links in this message
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendMessage(Object chat_id, String text, String parse_mode, Boolean disable_notification, Boolean disable_web_page_preview, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to forward messages of any kind. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param from_chat_id Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
     * @param message_id Unique message identifier
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message forwardMessage(Object chat_id, Integer from_chat_id, Integer message_id) throws TelegramException {
        return forwardMessage(chat_id, from_chat_id, false, message_id);
    }
    
    /**
     * Use this method to forward messages of any kind. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param from_chat_id Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername)
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param message_id Unique message identifier
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message forwardMessage(Object chat_id, Integer from_chat_id, Boolean disable_notification, Integer message_id) throws TelegramException;
    
    /**
     * Use this method to send photos. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendPhoto(Object chat_id, String photo) throws TelegramException {
        return sendPhoto(chat_id, photo, null, false, null, null);
    }
    
    /**
     * Use this method to send photos. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendPhoto(Object chat_id, java.io.File photo) throws TelegramException {
        return sendPhoto(chat_id, photo, null, false, null, null);
    }
    
    /**
     * Use this method to send photos. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
     * @param caption Photo caption (may also be used when resending photos by file_id).
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendPhoto(Object chat_id, Object photo, String caption, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
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
        return sendAudio(chat_id, audio, null, null,  null, null, false, null, null);
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
        return sendAudio(chat_id, audio, null, null, null, null, false, null, null);
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
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendAudio(Object chat_id, Object audio, String caption, Integer duration, String performer, String title, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
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
        return sendDocument(chat_id, document, false, null, null);
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
        return sendDocument(chat_id, document, false, null, null);
    }
    
    /**
     * Use this method to send general files. On success, the sent {@link Message} is returned. 
     * Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param document File to send. You can either pass a file_id as String to resend a file that is already on the Telegram servers, 
     *                  or upload a new file using multipart/form-data.
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendDocument(Object chat_id, Object document, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
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
        return sendVideo(chat_id, video, null, null, null, null, null, null, false, null, null);
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
        return sendVideo(chat_id, video, null, null, null, null, null, null, false, null, null);
    }
    
    /**
     * Use this method to send video files, Telegram clients support mp4 videos (other formats may be sent as {@link Document}).
     * Bots can currently send video files of up to 50 MB in size, this limit may be changed in the future.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param video Video file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
     * @param duration Duration of the audio in seconds
     * @param width Video width
     * @param height Video height
     * @param caption Video caption (may also be used when resending videos by file_id), 0-200 characters
     * @param parse_mode Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     * @param supports_streaming Pass True, if the uploaded video is suitable for streaming
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendVideo(Object chat_id, Object video, Integer duration, Integer width, Integer height, String caption, String parse_mode, Boolean supports_streaming, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
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
        return sendVoice(chat_id, voice, null, null, false, null, null);
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
        return sendVoice(chat_id, voice, null, null, false, null, null);
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
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendVoice(Object chat_id, Object voice, String caption, Integer duration, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;

    /**
     * Use this method to send a group of photos or videos as an album.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param media A JSON-serialized array describing photos and videos to be sent, must include 2–10 items
     * @return An array of the sent Messages is returned on success
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendMediaGroup(Object chat_id, List<InputMedia> media) throws TelegramException {
        return sendMediaGroup(chat_id, media, false, null);
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
    Message sendMediaGroup(Object chat_id, List<InputMedia> media, Boolean disable_notification, Integer reply_to_message_id) throws TelegramException;

    /**
     * Use this method to send point on the map. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param latitude Latitude of location
     * @param longitude Longitude of location
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendLocation(Object chat_id, Float latitude, Float longitude) throws TelegramException {
        return sendLocation(chat_id, latitude, longitude, null, false, null, null);
    }
    
    /**
     * Use this method to send point on the map. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param latitude Latitude of location
     * @param longitude Longitude of location
     * @param live_period Period in seconds for which the location will be updated (should be between 60 and 86400).
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendLocation(Object chat_id, Float latitude, Float longitude, Integer live_period, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to edit live location messages sent by the bot or via the bot (for inline bots).
     * A location can be edited until its live_period expires or editing is explicitly disabled by a call to stopMessageLiveLocation. 
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @param latitude Latitude of new location
     * @param longitude Longitude of new location
     * @param reply_markup A JSON-serialized object for a new inline keyboard.
     * @return if the edited message was sent by the bot, the edited Message is returned, otherwise True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Object editMessageLiveLocation(Object chat_id, Integer message_id, String inline_message_id, Float latitude, Float longitude, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
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
        return sendVideoNote(chat_id, video_note, null, null, false, null, null);
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
        return sendVideoNote(chat_id, video_note, null, null, false, null, null);
    }
    
    /**
     * As of v.4.0, Telegram clients support rounded square mp4 videos of up to 1 minute long. Use this method to send video messages. On success, the sent Message is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param video_note Video note to send. Pass a file_id as String to send a video note that exists on the Telegram servers (recommended) or upload a new video using multipart/form-data. More info on Sending Files ». Sending video notes by a URL is currently unsupported
     * @param duration Duration of sent video in seconds
     * @param length Video width and height
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendVideoNote(Object chat_id, Object video_note, Integer duration, Integer length, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
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
        return sendVenue(chat_id, latitude, longitude, title, address, null, false, null, null);
    }
    
    /**
     * Use this method to send information about a venue. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param latitude Latitude of the venue
     * @param longitude Longitude of the venue
     * @param title Name of the venue
     * @param address Address of the venue
     * @param foursquare_id Foursquare identifier of the venue
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendVenue(Object chat_id, Float latitude, Float longitude, String title, String address, String foursquare_id, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to send phone contacts. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param phone_number Contact's phone number
     * @param first_name Contact's first name
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendContact(Object chat_id, String phone_number, String first_name)  throws TelegramException {
        return sendContact(chat_id, phone_number, first_name, null, false, null, null);
    }
    
    /**
     * Use this method to send phone contacts. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param phone_number Contact's phone number
     * @param first_name Contact's first name
     * @param last_name Contact's last name
     * @param disable_notification Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     * @param reply_to_message_id If the message is a reply, ID of the original message
     * @param reply_markup Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user. 
     *                  It can be {@link ReplyKeyboardMarkup}, {@link ReplyKeyboardRemove} or {@link ForceReply}.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Message sendContact(Object chat_id, String phone_number, String first_name, String last_name, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method when you need to tell the user that something is happening on the bot's side. 
     * The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status).
     * We only recommend using this method when a response from the bot will take a noticeable amount of time to arrive.
     * Watch more in https://core.telegram.org/bots/api#sendchataction
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param action Type of action to broadcast. 
     *          Choose one, depending on what the user is about to receive: typing for text messages, upload_photo for photos, 
     *          record_video or upload_video for videos, record_audio or upload_audio for audio files, upload_document for general files, 
     *          find_location for location data.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean sendChatAction(Object chat_id, String action) throws TelegramException;
    
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
     *          Choose one, depending on what the user is about to receive: typing for text messages, upload_photo for photos, 
     *          record_video or upload_video for videos, record_audio or upload_audio for audio files, upload_document for general files, 
     *          find_location for location data.
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
    default UserProfilePhotos getUserProfilePhotos(Integer user_id) throws TelegramException {
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
    UserProfilePhotos getUserProfilePhotos(Integer user_id, Integer offset, Integer limit) throws TelegramException;
    
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
    default Boolean kickChatMember(Object chat_id, Integer user_id) throws TelegramException {
        return kickChatMember(chat_id, user_id, null);
    }

    /**
     * Use this method to kick a user from a group or a supergroup. In the case of supergroups,
     *      the user will not be able to return to the group on their own using invite links, etc., unless unbanned first.
     * The bot must be an administrator in the group for this to work. Returns True on success.
     * @param chat_id Unique identifier for the target group or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param until_date Optional. Date when the user will be unbanned, unix time. If user is banned for more than 366 days or less than 30 seconds from the current time they are considered to be banned forever
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean kickChatMember(Object chat_id, Integer user_id, Integer until_date) throws TelegramException;
    
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
    Boolean unbanChatMember(Object chat_id, Integer user_id) throws TelegramException;

    /**
     * Use this method to restrict a user in a supergroup.
     * The bot must be an administrator in the supergroup for this to work and must have the appropriate admin rights.
     * Pass True for all boolean parameters to lift restrictions from a user.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean restrictChatMember(Object chat_id, Integer user_id) throws TelegramException {
        return restrictChatMember(chat_id, user_id, null, false, false, false, false);
    }

    /**
     * Use this method to restrict a user in a supergroup.
     * The bot must be an administrator in the supergroup for this to work and must have the appropriate admin rights.
     * Pass True for all boolean parameters to lift restrictions from a user.
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param until_date Date when restrictions will be lifted for the user, unix time. If user is restricted for more than 366 days or less than 30 seconds from the current time, they are considered to be restricted forever
     * @param can_send_messages Pass True, if the user can send text messages, contacts, locations and venues
     * @param can_send_media_messages Pass True, if the user can send audios, documents, photos, videos, video notes and voice notes, implies can_send_messages
     * @param can_send_other_messages Pass True, if the user can send animations, games, stickers and use inline bots, implies can_send_media_messages
     * @param can_add_web_page_previews 	Pass True, if the user may add web page previews to their messages, implies can_send_media_messages
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean restrictChatMember(Object chat_id, Integer user_id, Integer until_date, Boolean can_send_messages, Boolean can_send_media_messages,
                               Boolean can_send_other_messages, Boolean can_add_web_page_previews) throws TelegramException;

    /**
     * Use this method to promote or demote a user in a supergroup or a channel.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * Pass False for all boolean parameters to demote a user
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean promoteChatMember(Object chat_id, Integer user_id) throws TelegramException {
        return promoteChatMember(chat_id, user_id, false, false, false, false, false, false, false, false);
    }

    /**
     * Use this method to promote or demote a user in a supergroup or a channel.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * Pass False for all boolean parameters to demote a user
     * @param chat_id Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
     * @param user_id Unique identifier of the target user
     * @param can_change_info Pass True, if the administrator can change chat title, photo and other settings
     * @param can_post_messages Pass True, if the administrator can create channel posts, channels only
     * @param can_edit_messages Pass True, if the administrator can edit messages of other users and can pin messages, channels only
     * @param can_delete_messages Pass True, if the administrator can delete messages of other users
     * @param can_invite_users Pass True, if the administrator can invite new users to the chat
     * @param can_restrict_members Pass True, if the administrator can restrict, ban or unban chat members
     * @param can_pin_messages Pass True, if the administrator can pin messages, supergroups only
     * @param can_promote_members Pass True, if the administrator can add new administrators with a subset of his own privileges or demote administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by him)
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean promoteChatMember(Object chat_id, Integer user_id, Boolean can_change_info, Boolean can_post_messages, Boolean can_edit_messages,
                              Boolean can_delete_messages, Boolean can_invite_users, Boolean can_restrict_members, Boolean can_pin_messages, Boolean can_promote_members) throws TelegramException;

    /**
     * Use this method to generate a new invite link for a chat; any previously generated link is revoked.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return the new invite link as String on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    String exportChatInviteLink(Object chat_id) throws TelegramException;

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
     * Use this method to pin a message in a supergroup. 
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
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
     * Use this method to unpin a message in a supergroup chat.
     * The bot must be an administrator in the chat for this to work and must have the appropriate admin rights.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean unpinChatMessage(Object chat_id) throws TelegramException;
    
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
    Integer getChatMembersCount(Object chat_id) throws TelegramException;
    
    /**
     * 
     * @param chat_id Unique identifier for the target chat or username of the target supergroup or channel (in the format @channelusername)
     * @param user_id Unique identifier of the target user
     * @return {@link ChatMember}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    ChatMember getChatMember(Object chat_id, Integer user_id) throws TelegramException;
    
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
     * Use this method to edit text and game messages sent by the bot or via the bot (for inline bots). 
     * On success, if edited message is sent by the bot, the edited {@link Message} is returned, otherwise True is returned.
     * @param chat_id Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param message_id Required if inline_message_id is not specified. Unique identifier of the sent message
     * @param inline_message_id Required if chat_id and message_id are not specified. Identifier of the inline message
     * @param text New text of the message
     * @param parse_mode Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     * @param disable_web_page_preview Disables link previews for links in this message
     * @param reply_markup A JSON-serialized object for an inline keyboard.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean editMessageText(Object chat_id, Integer message_id, String inline_message_id, String text, String parse_mode, Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
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
    Boolean editMessageCaption(Object chat_id, Integer message_id, String inline_message_id, String caption, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
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
    Boolean editMessageReplyMarkup(Object chat_id, Integer message_id, String inline_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
    /**
     * Use this method to delete a message. A message can only be deleted if it was sent less than 48 hours ago. Any such recently sent outgoing message may be deleted.
     * Additionally, if the bot is an administrator in a group chat, it can delete any message.
     * If the bot is an administrator in a supergroup, it can delete messages from any other user and service messages about people joining or leaving the group (other types of service messages may only be removed by the group creator).
     * In channels, bots can only remove their own messages.
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
     * @param max_connections Optional. Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100. Defaults to 40.
     *                        Use lower values to limit the load on your bot‘s server, and higher values to increase your bot’s throughput.
     * @param allowed_updates Optional. List the types of updates you want your bot to receive. 
     *                        For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive updates of these types.
     *                        See {@link Update} for a complete list of available update types. Specify an empty list to receive all updates regardless of type (default).
     *                        If not specified, the previous setting will be used.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean setWebhook(String url, java.io.File certificate, Integer max_connections, List<String> allowed_updates) throws TelegramException;

    /**
     * Use this method to remove webhook integration if you decide to switch back to {@link BotAPI#getUpdates}. Returns True on success. Requires no parameters.
     * @return On success, True is returned.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean deleteWebhook() throws TelegramException;
    
    /**
     * Use this method to send .webp stickers. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param sticker Sticker to send. You can either pass a file_id as String to resend a sticker that is already on the Telegram servers, 
     *                  or upload a new sticker using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendSticker(Object chat_id, String sticker) throws TelegramException {
        return sendSticker(chat_id, sticker, false, null, null);
    }
    
    /**
     * Use this method to send .webp stickers. On success, the sent {@link Message} is returned.
     * @param chat_id Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * @param sticker Sticker to send. You can either pass a file_id as String to resend a sticker that is already on the Telegram servers, 
     *                  or upload a new sticker using multipart/form-data.
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendSticker(Object chat_id, java.io.File sticker) throws TelegramException {
        return sendSticker(chat_id, sticker, false, null, null);
    }
    
    /**
     * Use this method to send .webp stickers. On success, the sent {@link Message} is returned.
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
    Message sendSticker(Object chat_id, Object sticker, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException;
    
    /**
     * Use this method to get a sticker set. On success, a StickerSet object is returned.
     * @param name Name of the sticker set
     * @return StickerSet
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    StickerSet getStickerSet(String name) throws TelegramException;
    
    /**
     * Use this method to upload a .png file with a sticker for later use in createNewStickerSet and addStickerToSet methods (can be used multiple times). Returns the uploaded File on success.
     * @param user_id User identifier of sticker file owner
     * @param png_sticker Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. 
     * @return The uploaded File on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    File uploadStickerFile(Integer user_id, java.io.File png_sticker) throws TelegramException;

    /**
     * Use this method to create new sticker set owned by a user. The bot will be able to edit the created sticker set. Returns True on success.
     * @param user_id User identifier of created sticker set owner
     * @param name Short name of sticker set, to be used in t.me/addstickers/ URLs. Must begin with a letter, can't contain consecutive underscores and must end in “_by_&lt;bot username&gt;”. &lt;bot_username&gt; is case insensitive. 1-64 characters.
     * @param title Sticker set title, 1-64 characters
     * @param png_sticker Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using 
     * @param emojis One or more emoji corresponding to the sticker
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean createNewStickerSet(Integer user_id, String name, String title, java.io.File png_sticker, String emojis) throws TelegramException {
        return createNewStickerSet(user_id, name, title, png_sticker, emojis, null, null);
    }
    
    /**
     * Use this method to create new sticker set owned by a user. The bot will be able to edit the created sticker set. Returns True on success.
     * @param user_id User identifier of created sticker set owner
     * @param name Short name of sticker set, to be used in t.me/addstickers/ URLs. Must begin with a letter, can't contain consecutive underscores and must end in “_by_&lt;bot username&gt;”. &lt;bot_username&gt; is case insensitive. 1-64 characters.
     * @param title Sticker set title, 1-64 characters
     * @param png_sticker Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using 
     * @param emojis One or more emoji corresponding to the sticker
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean createNewStickerSet(Integer user_id, String name, String title, String png_sticker, String emojis) throws TelegramException {
        return createNewStickerSet(user_id, name, title, png_sticker, emojis, null, null);
    }
    
    /**
     * Use this method to create new sticker set owned by a user. The bot will be able to edit the created sticker set. Returns True on success.
     * @param user_id User identifier of created sticker set owner
     * @param name Short name of sticker set, to be used in t.me/addstickers/ URLs. Must begin with a letter, can't contain consecutive underscores and must end in “_by_&lt;bot username&gt;”. &lt;bot_username&gt; is case insensitive. 1-64 characters.
     * @param title Sticker set title, 1-64 characters
     * @param png_sticker Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using 
     * @param emojis One or more emoji corresponding to the sticker
     * @param contains_masks Optional. Pass True, if a set of mask stickers should be created
     * @param mask_position Optional. A JSON-serialized object for position where the mask should be placed on faces
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean createNewStickerSet(Integer user_id, String name, String title, Object png_sticker, String emojis, Boolean contains_masks, MaskPosition mask_position) throws TelegramException;
    
    /**
     * Use this method to add a new sticker to a set created by the bot. Returns True on success.
     * @param user_id User identifier of sticker set owner
     * @param name Sticker set name
     * @param png_sticker Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using multipart/form-data
     * @param emojis One or more emoji corresponding to the sticker
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean addStickerToSet(Integer user_id, String name, java.io.File png_sticker, String emojis) throws TelegramException {
        return addStickerToSet(user_id, name, png_sticker, emojis, null);
    }
    
    /**
     * Use this method to add a new sticker to a set created by the bot. Returns True on success.
     * @param user_id User identifier of sticker set owner
     * @param name Sticker set name
     * @param png_sticker Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using multipart/form-data
     * @param emojis One or more emoji corresponding to the sticker
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Boolean addStickerToSet(Integer user_id, String name, String png_sticker, String emojis) throws TelegramException {
        return addStickerToSet(user_id, name, png_sticker, emojis, null);
    }
    
    /**
     * Use this method to add a new sticker to a set created by the bot. Returns True on success.
     * @param user_id User identifier of sticker set owner
     * @param name Sticker set name
     * @param png_sticker Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using multipart/form-data
     * @param emojis One or more emoji corresponding to the sticker
     * @param mask_position Optional. A JSON-serialized object for position where the mask should be placed on faces
     * @return True on success.
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    Boolean addStickerToSet(Integer user_id, String name, Object png_sticker, String emojis, MaskPosition mask_position) throws TelegramException;
    
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
     * Use this method to send invoices. On success, the sent Message is returned.
     * @param chat_id Unique identifier for the target private chat
     * @param title Product name
     * @param description Product description
     * @param payload Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal processes.
     * @param provider_token Payments provider token, obtained via Botfather
     * @param start_parameter Unique deep-linking parameter that can be used to generate this invoice when used as a start parameter
     * @param currency Three-letter ISO 4217 currency code, see more on https://core.telegram.org/bots/payments#supported-currencies
     * @param prices Price breakdown, a list of components (e.g. product price, tax, discount, delivery cost, delivery tax, bonus, etc.)
     * @return {@link Message}
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Message sendInvoice(Integer chat_id, String title, String description, String payload, String provider_token, String start_parameter, String currency,
                        List<LabeledPrice> prices) throws TelegramException {
        return sendInvoice(chat_id, title, description, payload, provider_token, start_parameter, currency, prices, null, null, null, null, null, false, false, false, false, false, false, false, false, null, null);
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
    Message sendInvoice(Integer chat_id, String title, String description, String payload, String provider_token, String start_parameter, String currency,
                                    List<LabeledPrice> prices, String provider_data, String photo_url, Integer photo_size, Integer photo_width, Integer photo_height, Boolean need_name, Boolean need_phone_number,
                                    Boolean need_email, Boolean need_shipping_address, Boolean send_phone_number_to_provider, Boolean send_email_to_provider, Boolean is_flexible, Boolean disable_notification, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
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
        return sendGame(chat_id, game_short_name, null, null, null);
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
    Message sendGame(Object chat_id, String game_short_name, Boolean disable_notification, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException;
    
    /**
     * Use this method to set the score of the specified user in a game. 
     * On success, if the message was sent by the bot, returns the edited Message, otherwise returns True. 
     * Returns an error, if the new score is not greater than the user's current score in the chat and force is False.
     * @param user_id User identifier
     * @param score New score, must be non-negative
     * @return the Message, true or false
     * @throws com.cadiducho.telegrambotapi.exception.TelegramException if the method fails in Telegram servers
     */
    default Object setGameScore(Integer user_id, Integer score) throws TelegramException {
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
    Object setGameScore(Integer user_id, Integer score, Boolean force, Boolean disable_edit_message, Object chat_id, Integer message_id, String inline_message_id) throws TelegramException;
    
    /**
     * Use this method to get data for high score tables.
     * Will return the score of the specified user and several of his neighbors in a game. 
     * @param user_id Target user id
     * @return On success, returns an Array of GameHighScore objects.
     * @throws TelegramException if the method fails in Telegram servers
     */
    default List<GameHighScore> getGameHighScores(Integer user_id) throws TelegramException {
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
    List<GameHighScore> getGameHighScores(Integer user_id, Object chat_id, Integer message_id, String inline_message_id) throws TelegramException;
}
