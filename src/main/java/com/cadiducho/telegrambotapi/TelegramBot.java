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
import com.cadiducho.telegrambotapi.handlers.DefaultBotUpdatesPoller;
import com.cadiducho.telegrambotapi.inline.InlineKeyboardMarkup;
import com.cadiducho.telegrambotapi.inline.InlineQueryResult;
import com.cadiducho.telegrambotapi.payment.LabeledPrice;
import com.cadiducho.telegrambotapi.payment.ShippingOption;
import com.cadiducho.telegrambotapi.util.ApiResponse;
import com.cadiducho.telegrambotapi.util.MediaTypes;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.Getter;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TelegramBot implements BotAPI {

    private final String apiUrl;
    @Getter private final BotAPI instance;
    @Getter private BotUpdatesPoller updatesPoller;

    private final Moshi moshi = new Moshi.Builder().build();
    private final OkHttpClient httpClient = new OkHttpClient();

    @Override
    public void startUpdatesPoller() {
        updatesPoller.start();
    }

    @Override
    public void stopUpdatesPoller() {
        updatesPoller.stop();
    }

    public TelegramBot(String token){
        instance = this;
        apiUrl = "https://api.telegram.org/bot" + token + "/";
        updatesPoller = new DefaultBotUpdatesPoller(instance);
    }

    private <T> T handleRequest(Request request, java.lang.reflect.Type type) throws TelegramException {
        try (Response response = httpClient.newCall(request).execute()) {
            ApiResponse<T> apiResponse = ApiResponse.from(Objects.requireNonNull(response.body()).source(), type);
            if (apiResponse.getOk()) {
                return apiResponse.getResult();
            } else {
                throw new TelegramException(apiResponse.getDescription());
            }
        } catch (IOException ex) {
            throw new TelegramException("Could not get a response.", ex);
        }
    }

    
    private Object getSafeChatId(Object rawChatId) {
        if (rawChatId == null) {
            throw new IllegalArgumentException ("The chatId must be a String, a Number, a Chat, a User or a ChatMember!");
        }
        
        Object safeChatId;
        if (rawChatId instanceof Chat) {
            safeChatId = ((Chat) rawChatId).getId();
        } else if (rawChatId instanceof User) {
            safeChatId = ((User) rawChatId).getId();
        } else if (rawChatId instanceof ChatMember) {
            safeChatId = ((ChatMember) rawChatId).getUser().getId();
        } else if (rawChatId instanceof String || rawChatId instanceof Number) {
            safeChatId = rawChatId;
        } else {
            throw new IllegalArgumentException ("The chatId must be a String, a Number, a Chat, a User or a ChatMember!");
        }
        
        return safeChatId;
    }


    private void safeAdd(MultipartBody.Builder parameters, String str, Object obj) {
        //Check markup style if exists
        if (str.equals("reply_markup") && obj != null) {
            JsonAdapter adapter;
            if (obj instanceof ReplyKeyboardRemove) adapter = moshi.adapter(ReplyKeyboardRemove.class);
            else if (obj instanceof ReplyKeyboardMarkup) adapter = moshi.adapter(ReplyKeyboardMarkup.class);
            else if (obj instanceof InlineKeyboardMarkup) adapter = moshi.adapter(InlineKeyboardMarkup.class);
            else if (obj instanceof ForceReply) adapter = moshi.adapter(ForceReply.class);
            else throw new IllegalArgumentException ("The replyMarkup must be on of the following classes: " +
                        ReplyKeyboardRemove.class.getName() + ", " +
                        ReplyKeyboardMarkup.class.getName() + ", " +
                        InlineKeyboardMarkup.class.getName() + ", " +
                        ForceReply.class.getName());

            //noinspection unchecked
            parameters.addFormDataPart("reply_markup", adapter.toJson(obj));
            return;
        }

        if (str.equals("parse_mode")) {
            if (obj == null) return;

            parameters.addFormDataPart(str, ((ParseMode) obj).getMode().toLowerCase());
        }

        //Return normal values (check optionals -> null)
        if (obj != null) parameters.addFormDataPart(str, obj.toString());
    }

    private void addFile(MultipartBody.Builder parameters, String name, Object obj, MediaType type) {
        if (obj instanceof String) {
            parameters.addFormDataPart(name, obj.toString());
        } else if (obj instanceof java.io.File) {
            java.io.File fFile = (java.io.File) obj;
            parameters.addFormDataPart(name, fFile.getName(), RequestBody.create(fFile, type));
        } else {
            throw new IllegalArgumentException("The " + name + " must be a string or a file!");
        }
    }

    //----------------

    @Override
    public User getMe() throws TelegramException {
        final Request request = new Request.Builder()
                .url(apiUrl + "getMe")
                .build();
        return handleRequest(request, User.class);
    }

    @Override
    public Boolean logOut() throws TelegramException {
        final Request request = new Request.Builder()
                .url(apiUrl + "logOut")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean close() throws TelegramException {
        final Request request = new Request.Builder()
                .url(apiUrl + "close")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Message sendMessage(Object chat_id, String text, ParseMode parse_mode, Boolean disable_web_page_preview, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "text", text);
        safeAdd(parameters, "parse_mode", parse_mode);
        safeAdd(parameters, "disableWebPagePreview", disable_web_page_preview);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendMessage")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message forwardMessage(Object chat_id, Integer from_chat_id, Boolean disable_notification, Integer message_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "from_chat_id", from_chat_id);
        safeAdd(parameters, "message_id", message_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendMessage")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public MessageId copyMessage(Object chat_id, Object from_chat_id, Integer message_id, String caption, String parse_mode, Boolean disable_notification, Integer reply_to_message_id, Boolean allow_sending_without_reply, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        Object safeFromChatId = getSafeChatId(from_chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "from_chat_id", safeFromChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "caption", caption);
        safeAdd(parameters, "parse_mode", parse_mode);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "allow_sending_without_reply", allow_sending_without_reply);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "copyMessage")
                .post(parameters.build())
                .build();
        return handleRequest(request, MessageId.class);
    }

    @Override
    public Message sendPhoto(Object chat_id, Object photo, String caption, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "caption", caption);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);
        addFile(parameters, "photo", photo, MediaTypes.MEDIA_TYPE_PHOTO);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendPhoto")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendAudio(Object chat_id, Object audio, String caption, Integer duration, String performer, String title, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "caption", caption);
        safeAdd(parameters, "duration", duration);
        safeAdd(parameters, "performer", performer);
        safeAdd(parameters, "title", title);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);
        addFile(parameters, "audio", audio, MediaTypes.MEDIA_TYPE_AUDIO);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendAudio")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendDocument(Object chat_id, Object document, Boolean disable_content_type_detection, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);
        
        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "disable_content_type_detection", disable_content_type_detection);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);
        addFile(parameters, "document", document, MediaTypes.MEDIA_TYPE_APPLICATION);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendDocument")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendVideo(Object chat_id, Object video, Integer duration, Integer width, Integer height, String caption, ParseMode parse_mode, Boolean supports_streaming, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "duration", duration);
        safeAdd(parameters, "width", width);
        safeAdd(parameters, "height", height);
        safeAdd(parameters, "caption", caption);
        safeAdd(parameters, "parse_mode", parse_mode);
        safeAdd(parameters, "supports_streaming", supports_streaming);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);
        addFile(parameters, "video", video, MediaTypes.MEDIA_TYPE_VIDEO);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendVideo")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendAnimation(Object chat_id, Object animation, Integer duration, Integer width, Integer height, Object thumb, String caption, ParseMode parse_mode, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        addFile(parameters, "animation", animation, MediaTypes.MEDIA_TYPE_VIDEO);
        safeAdd(parameters, "duration", duration);
        safeAdd(parameters, "width", width);
        safeAdd(parameters, "height", height);
        safeAdd(parameters, "caption", caption);
        addFile(parameters, "thumb", thumb, MediaTypes.MEDIA_TYPE_PHOTO);
        safeAdd(parameters, "parse_mode", parse_mode);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendAnimation")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendVoice(Object chat_id, Object voice, String caption, Integer duration, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "caption", caption);
        safeAdd(parameters, "duration", duration);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);
        addFile(parameters, "voice", voice, MediaTypes.MEDIA_TYPE_AUDIO);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendVoice")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendMediaGroup(Object chat_id, List<InputMedia> media, Boolean disable_notification, Integer reply_to_message_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "media", moshi.adapter(Types.newParameterizedType(List.class, InputMedia.class)).toJson(media));
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendMediaGroup")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendLocation(Object chat_id, Float latitude, Float longitude, Float horizontal_accuracy, Integer live_period, Integer heading, Integer proximity_alert_radius, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "latitude", latitude);
        safeAdd(parameters, "longitude", longitude);
        safeAdd(parameters, "horizontal_accuracy", horizontal_accuracy);
        safeAdd(parameters, "heading", heading);
        safeAdd(parameters, "proximity_alert_radius", proximity_alert_radius);
        safeAdd(parameters, "live_period", live_period);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendLocation")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Object editMessageLiveLocation(Object chat_id, Integer message_id, String inline_message_id, Float latitude, Float longitude, Float horizontal_accuracy, Integer heading, Integer proximity_alert_radius, InlineKeyboardMarkup reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "inline_message_id", inline_message_id);
        safeAdd(parameters, "latitude", latitude);
        safeAdd(parameters, "longitude", longitude);
        safeAdd(parameters, "horizontal_accuracy", horizontal_accuracy);
        safeAdd(parameters, "heading", heading);
        safeAdd(parameters, "proximity_alert_radius", proximity_alert_radius);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "editMessageLiveLocation")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Object stopMessageLiveLocation(Object chat_id, Integer message_id, String inline_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "inline_message_id", inline_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "stopMessageLiveLocation")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendVideoNote(Object chat_id, Object video_note, Integer duration, Integer length, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "duration", duration);
        safeAdd(parameters, "length ", length);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);
        addFile(parameters, "video_note", video_note, MediaTypes.MEDIA_TYPE_VIDEO);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendVideoNote")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendVenue(Object chat_id, Float latitude, Float longitude, String title, String address, String foursquare_id, String foursquare_type, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "latitude", latitude);
        safeAdd(parameters, "longitude", longitude);
        safeAdd(parameters, "title", title);
        safeAdd(parameters, "address", address);
        safeAdd(parameters, "foursquare_id", foursquare_id);
        safeAdd(parameters, "foursquare_type", foursquare_type);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendVenue")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendContact(Object chat_id, String phone_number, String first_name, String last_name, String vcard, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "phone_number", phone_number);
        safeAdd(parameters, "first_name", first_name);
        safeAdd(parameters, "last_name", last_name);
        safeAdd(parameters, "vcard", vcard);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendContact")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendPoll(Object chat_id, String question, List<String> options, Boolean is_anonymous, String type, Boolean allows_multiple_answers,
                            Integer correct_option_id, String explanation, String explanation_parse_mode, Integer open_period, Integer close_date,
                            Boolean is_closed, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "question", question);
        safeAdd(parameters, "options", moshi.adapter(Types.newParameterizedType(List.class, String.class)).toJson(options));
        safeAdd(parameters, "is_anonymous", is_anonymous);
        safeAdd(parameters, "type", type);
        safeAdd(parameters, "allows_multiple_answers", allows_multiple_answers);
        safeAdd(parameters, "correct_option_id", correct_option_id);
        safeAdd(parameters, "explanation", explanation);
        safeAdd(parameters, "explanation_parse_mode", explanation_parse_mode);
        safeAdd(parameters, "open_period", open_period);
        safeAdd(parameters, "close_date", close_date);
        safeAdd(parameters, "is_closed", is_closed);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendPoll")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message sendDice(Object chat_id, String emoji, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);

        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "emoji", emoji);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendDice")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Boolean sendChatAction(Object chat_id, String action) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "action", action);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendChatAction")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public UserProfilePhotos getUserProfilePhotos(Integer user_id, Integer offset, Integer limit) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "offset", offset);
        safeAdd(parameters, "limit", limit);

        final Request request = new Request.Builder()
                .url(apiUrl + "getUserProfilePhotos")
                .post(parameters.build())
                .build();
        return handleRequest(request, UserProfilePhotos.class);
    }

    @Override
    public File getFile(String file_id) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "file_id", file_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "getFile")
                .post(parameters.build())
                .build();
        return handleRequest(request, File.class);
    }

    @Override
    public Boolean kickChatMember(Object chat_id, Integer user_id, Integer until_date) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "until_date", until_date);

        final Request request = new Request.Builder()
                .url(apiUrl + "kickChatMember")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean restrictChatMember(Object chat_id, Integer user_id, ChatPermissions permissions, Integer until_date) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "permissions", permissions);
        safeAdd(parameters, "until_date", until_date);

        final Request request = new Request.Builder()
                .url(apiUrl + "restrictChatMember")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean promoteChatMember(Object chat_id, Integer user_id, Boolean is_anonymous, Boolean can_change_info, Boolean can_post_messages, Boolean can_edit_messages, Boolean can_delete_messages, Boolean can_invite_users, Boolean can_restrict_members, Boolean can_pin_messages, Boolean can_promote_members) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "is_anonymous", is_anonymous);
        safeAdd(parameters, "can_change_info", can_change_info);
        safeAdd(parameters, "can_post_messages", can_post_messages);
        safeAdd(parameters, "can_edit_messages", can_edit_messages);
        safeAdd(parameters, "can_delete_messages", can_delete_messages);
        safeAdd(parameters, "can_invite_users", can_invite_users);
        safeAdd(parameters, "can_restrict_members", can_restrict_members);
        safeAdd(parameters, "can_pin_messages", can_pin_messages);
        safeAdd(parameters, "can_promote_members", can_promote_members);

        final Request request = new Request.Builder()
                .url(apiUrl + "promoteChatMember")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean setChatAdministratorCustomTitle(Object chat_id, Integer user_id, String custom_title) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "user_id", custom_title);

        final Request request = new Request.Builder()
                .url(apiUrl + "setChatAdministratorCustomTitle")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean setChatPermissions(Object chat_id, ChatPermissions permissions) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "permissions", permissions);

        final Request request = new Request.Builder()
                .url(apiUrl + "setChatPermissions")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }
    
    @Override
    public String exportChatInviteLink(Object chat_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);

        final Request request = new Request.Builder()
                .url(apiUrl + "exportChatInviteLink")
                .post(parameters.build())
                .build();
        return handleRequest(request, String.class);
    }

    @Override
    public Boolean setChatPhoto(Object chat_id, java.io.File photo) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        addFile(parameters, "photo", photo, MediaTypes.MEDIA_TYPE_PHOTO);

        final Request request = new Request.Builder()
                .url(apiUrl + "setChatPhoto")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean deleteChatPhoto(Object chat_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);

        final Request request = new Request.Builder()
                .url(apiUrl + "deleteChatPhoto")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean setChatTitle(Object chat_id, String title) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "title", title);

        final Request request = new Request.Builder()
                .url(apiUrl + "setChatTitle")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean setChatDescription(Object chat_id, String description) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "description", description);

        final Request request = new Request.Builder()
                .url(apiUrl + "setChatDescription")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean pinChatMessage(Object chat_id, Integer message_id, Boolean disable_notification) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "disable_notification", disable_notification);

        final Request request = new Request.Builder()
                .url(apiUrl + "pinChatMessage")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean unpinChatMessage(Object chat_id, Integer message_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "unpinChatMessage")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean unpinAllChatMessages(Object chat_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);

        final Request request = new Request.Builder()
                .url(apiUrl + "unpinAllChatMessages")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean leaveChat(Object chat_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);

        final Request request = new Request.Builder()
                .url(apiUrl + "leaveChat")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean unbanChatMember(Object chat_id, Integer user_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "user_id", user_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "unbanChatMember")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Chat getChat(Object chat_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);

        final Request request = new Request.Builder()
                .url(apiUrl + "getChat")
                .post(parameters.build())
                .build();
        return handleRequest(request, Chat.class);
    }

    @Override
    public List<ChatMember> getChatAdministrators(Object chat_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);

        final Request request = new Request.Builder()
                .url(apiUrl + "getChatAdministrators")
                .post(parameters.build())
                .build();
        return handleRequest(request, Types.newParameterizedType(List.class, ChatMember.class));
    }

    @Override
    public Integer getChatMembersCount(Object chat_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);

        final Request request = new Request.Builder()
                .url(apiUrl + "getChatMembersCount")
                .post(parameters.build())
                .build();
        return handleRequest(request, Integer.class);
    }

    @Override
    public ChatMember getChatMember(Object chat_id, Integer user_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "user_id", user_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "getChatMember")
                .post(parameters.build())
                .build();
        return handleRequest(request, ChatMember.class);
    }

    @Override
    public Boolean setChatStickerSet(Object chat_id, String sticker_set_name) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "sticker_set_name", sticker_set_name);

        final Request request = new Request.Builder()
                .url(apiUrl + "setChatStickerSet")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean deleteChatStickerSet(Object chat_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);

        final Request request = new Request.Builder()
                .url(apiUrl + "deleteChatStickerSet")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean answerCallbackQuery(String callback_query_id, String text, Boolean show_alert, String url, Integer cache_time) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "callback_query_id", callback_query_id);
        safeAdd(parameters, "text", text);
        safeAdd(parameters, "show_alert", show_alert);
        safeAdd(parameters, "url", url);
        safeAdd(parameters, "cache_time", cache_time);

        final Request request = new Request.Builder()
                .url(apiUrl + "answerCallbackQuery")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean setMyCommands(List<BotCommand> commands) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "commands", moshi.adapter(Types.newParameterizedType(List.class, BotCommand.class)).toJson(commands));

        final Request request = new Request.Builder()
                .url(apiUrl + "setMyCommands")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public List<BotCommand> getMyCommands() throws TelegramException {
        final Request request = new Request.Builder()
                .url(apiUrl + "getMyCommands")
                .build();
        return handleRequest(request, Types.newParameterizedType(List.class, BotCommand.class));
    }

    @Override
    public Message editMessageText(Object chat_id, Integer message_id, String inline_message_id, String text, ParseMode parse_mode, Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "inline_message_id", inline_message_id);
        safeAdd(parameters, "text", text);
        safeAdd(parameters, "parse_mode", parse_mode);
        safeAdd(parameters, "disableWebPagePreview", disable_web_page_preview);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "editMessageText")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message editMessageCaption(Object chat_id, Integer message_id, String inline_message_id, String caption, ParseMode parse_mode, InlineKeyboardMarkup reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "caption", caption);
        safeAdd(parameters, "caption", parse_mode);
        safeAdd(parameters, "inline_message_id", inline_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "editMessageCaption")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message editMessageMedia(Object chat_id, Integer message_id, String inline_message_id, InputMedia media, InlineKeyboardMarkup reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "inline_message_id", inline_message_id);
        safeAdd(parameters, "media", media);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "editMessageMedia")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message editMessageReplyMarkup(Object chat_id, Integer message_id, String inline_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "inline_message_id", inline_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "editMessageReplyMarkup")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }
    
    @Override
    public Poll stopPoll(Object chat_id, Integer message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);

        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "stopPoll")
                .post(parameters.build())
                .build();
        return handleRequest(request, Poll.class);
    }
    
    @Override
    public Boolean deleteMessage(Object chat_id, Integer message_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);

        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "deleteMessage")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public List<Update> getUpdates(Integer offset, Integer limit, Integer timeout, List<String> allowed_updates) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "offset", offset);
        safeAdd(parameters, "limit", limit);
        safeAdd(parameters, "timeout", timeout);
        safeAdd(parameters, "allowed_updates", moshi.adapter(Types.newParameterizedType(List.class, String.class)).toJson(allowed_updates));

        final Request request = new Request.Builder()
                .url(apiUrl + "getUpdates")
                .post(parameters.build())
                .build();
        return handleRequest(request, Types.newParameterizedType(List.class, Update.class));
    }

    @Override
    public WebhookInfo getWebhookInfo() throws TelegramException {
        final Request request = new Request.Builder()
                .url(apiUrl + "getWebhookInfo")
                .build();
        return handleRequest(request, WebhookInfo.class);
    }


    @Override
    public Boolean setWebhook(String url, java.io.File certificate, String ip_address, Integer max_connections, List<String> allowed_updates, Boolean drop_pending_updates) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "url", url);
        addFile(parameters, "certificate", certificate, MediaTypes.MEDIA_TYPE_APPLICATION);
        safeAdd(parameters, "ip_address", ip_address);
        safeAdd(parameters, "max_connections", max_connections);
        safeAdd(parameters, "allowed_updates", allowed_updates);
        safeAdd(parameters, "drop_pending_updates", drop_pending_updates);

        final Request request = new Request.Builder()
                .url(apiUrl + "setWebhook")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean deleteWebhook(Boolean drop_pending_updates) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);
        safeAdd(parameters, "drop_pending_updates", drop_pending_updates);
        final Request request = new Request.Builder()
                .url(apiUrl + "deleteWebhook")
                .post(parameters.build())
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Message sendSticker(Object chat_id, Object sticker, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);
        addFile(parameters, "sticker", sticker, MediaTypes.MEDIA_TYPE_APPLICATION);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendSticker")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public StickerSet getStickerSet(String name) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "name", name);

        final Request request = new Request.Builder()
                .url(apiUrl + "getStickerSet")
                .build();
        return handleRequest(request, StickerSet.class);
    }

    @Override
    public File uploadStickerFile(Integer user_id, java.io.File png_sticker) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "user_id", user_id);
        addFile(parameters, "png_sticker", png_sticker, MediaTypes.MEDIA_TYPE_PHOTO);

        final Request request = new Request.Builder()
                .url(apiUrl + "uploadStickerFile")
                .build();
        return handleRequest(request, File.class);
    }

    @Override
    public Boolean createNewStickerSet(Integer user_id, String name, String title, Object png_sticker, java.io.File tgs_sticker, String emojis, Boolean contains_masks, MaskPosition mask_position) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "name", name);
        safeAdd(parameters, "title", title);
        safeAdd(parameters, "emojis", emojis);
        safeAdd(parameters, "contains_masks", contains_masks);
        safeAdd(parameters, "mask_position", mask_position);
        addFile(parameters, "png_sticker", png_sticker, MediaTypes.MEDIA_TYPE_PHOTO);
        addFile(parameters, "tgs_sticker", png_sticker, MediaTypes.MEDIA_TYPE_PHOTO);

        final Request request = new Request.Builder()
                .url(apiUrl + "createNewStickerSet")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean addStickerToSet(Integer user_id, String name, Object png_sticker, String emojis, MaskPosition mask_position) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "name", name);
        safeAdd(parameters, "emojis", emojis);
        safeAdd(parameters, "mask_position", mask_position);
        addFile(parameters, "png_sticker", png_sticker, MediaTypes.MEDIA_TYPE_APPLICATION);

        final Request request = new Request.Builder()
                .url(apiUrl + "addStickerToSet")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean setStickerPositionInSet(String sticker, Integer position) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "sticker", sticker);
        safeAdd(parameters, "position", position);

        final Request request = new Request.Builder()
                .url(apiUrl + "setStickerPositionInSet")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean deleteStickerFromSet(String sticker) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "sticker", sticker);

        final Request request = new Request.Builder()
                .url(apiUrl + "deleteStickerFromSet")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean setStickerSetThumb(String name, Integer user_id, java.io.File thumb) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "name", name);
        safeAdd(parameters, "user_id", user_id);
        addFile(parameters, "thumb", thumb, MediaTypes.MEDIA_TYPE_PHOTO);

        final Request request = new Request.Builder()
                .url(apiUrl + "setStickerSetThumb")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean setStickerSetThumb(String name, Integer user_id, String thumb) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "name", name);
        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "thumb", thumb);

        final Request request = new Request.Builder()
                .url(apiUrl + "setStickerSetThumb")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, Integer cache_time, Boolean is_personal, String next_offset,
            String switch_pm_text, String switch_pm_parameter) throws TelegramException {

        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);
        safeAdd(parameters, "inline_query_id", inlineQueryId);
        safeAdd(parameters, "results", moshi.adapter(Types.newParameterizedType(List.class, InlineQueryResult.class)).toJson(results));
        safeAdd(parameters, "cache_time", cache_time);
        safeAdd(parameters, "is_personal", is_personal);
        safeAdd(parameters, "next_offset", next_offset);
        safeAdd(parameters, "switch_pm_text", switch_pm_text);
        safeAdd(parameters, "switch_pm_parameter", switch_pm_parameter);

        final Request request = new Request.Builder()
                .url(apiUrl + "answerInlineQuery")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Message sendInvoice(Integer chat_id, String title, String description, String payload, String provider_token, String start_parameter, String currency,
            List<LabeledPrice> prices, String provider_data, String photo_url, Integer photo_size, Integer photo_width, Integer photo_height, Boolean need_name, Boolean need_phone_number,
            Boolean need_email, Boolean need_shipping_address, Boolean send_phone_number_to_provider, Boolean send_email_to_provider, Boolean is_flexible, Boolean disable_notification, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {

        Object safeChatId = getSafeChatId(chat_id);

        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);
        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "title", title);
        safeAdd(parameters, "description", description);
        safeAdd(parameters, "payload", payload);
        safeAdd(parameters, "provider_token", provider_token);
        safeAdd(parameters, "start_parameter", start_parameter);
        safeAdd(parameters, "currency", currency);
        safeAdd(parameters, "prices", moshi.adapter(Types.newParameterizedType(List.class, LabeledPrice.class)).toJson(prices));
        safeAdd(parameters, "provider_data", provider_data);
        safeAdd(parameters, "photo_url", photo_url);
        safeAdd(parameters, "photo_size", photo_size);
        safeAdd(parameters, "photoWidth", photo_width);
        safeAdd(parameters, "photoHeight", photo_height);
        safeAdd(parameters, "need_name", need_name);
        safeAdd(parameters, "need_phone_number", need_phone_number);
        safeAdd(parameters, "need_email", need_email);
        safeAdd(parameters, "need_shipping_address", need_shipping_address);
        safeAdd(parameters, "send_phone_number_to_provider", send_phone_number_to_provider);
        safeAdd(parameters, "send_email_to_provider", send_email_to_provider);
        safeAdd(parameters, "is_flexible", is_flexible);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendInvoice")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Boolean answerShippingQuery(String shipping_query_id, Boolean ok, List<ShippingOption> shipping_options, String error_message) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);
        safeAdd(parameters, "shipping_query_id", shipping_query_id);
        safeAdd(parameters, "ok", ok);
        safeAdd(parameters, "shipping_options", moshi.adapter(Types.newParameterizedType(List.class, ShippingOption.class)).toJson(shipping_options));
        safeAdd(parameters, "error_message", error_message);

        final Request request = new Request.Builder()
                .url(apiUrl + "answerShippingQuery")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Boolean answerPreCheckoutQuery(String pre_checkout_query_id, Boolean ok, String error_message) throws TelegramException {
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);
        
        safeAdd(parameters, "pre_checkout_query_id", pre_checkout_query_id);
        safeAdd(parameters, "ok", ok);
        safeAdd(parameters, "error_message", error_message);

        final Request request = new Request.Builder()
                .url(apiUrl + "answerPreCheckoutQuery")
                .build();
        return handleRequest(request, Boolean.class);
    }

    @Override
    public Message sendGame(Object chat_id, String game_short_name, Boolean disable_notification, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "game_short_name", game_short_name);
        safeAdd(parameters, "disable_notification", disable_notification);
        safeAdd(parameters, "reply_to_message_id", reply_to_message_id);
        safeAdd(parameters, "reply_markup", reply_markup);

        final Request request = new Request.Builder()
                .url(apiUrl + "sendGame")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public Message setGameScore(Integer user_id, Integer score, Boolean force, Boolean disable_edit_message, Object chat_id, Integer message_id, String inline_message_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "score", score);
        safeAdd(parameters, "force", force);
        safeAdd(parameters, "disable_edit_message", disable_edit_message);
        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "inline_message_id", inline_message_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "setGameScore")
                .post(parameters.build())
                .build();
        return handleRequest(request, Message.class);
    }

    @Override
    public List<GameHighScore> getGameHighScores(Integer user_id, Object chat_id, Integer message_id, String inline_message_id) throws TelegramException {
        Object safeChatId = getSafeChatId(chat_id);
        final MultipartBody.Builder parameters = new MultipartBody.Builder().setType(MultipartBody.FORM);

        safeAdd(parameters, "user_id", user_id);
        safeAdd(parameters, "chat_id", safeChatId);
        safeAdd(parameters, "message_id", message_id);
        safeAdd(parameters, "inline_message_id", inline_message_id);

        final Request request = new Request.Builder()
                .url(apiUrl + "getGameHighScores")
                .post(parameters.build())
                .build();
        return handleRequest(request, Types.newParameterizedType(List.class, Message.class));
    }
}
