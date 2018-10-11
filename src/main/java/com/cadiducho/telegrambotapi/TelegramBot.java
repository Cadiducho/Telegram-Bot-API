/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.cadiducho.telegrambotapi.inline.InlineKeyboardMarkup;
import com.cadiducho.telegrambotapi.inline.InlineQueryResult;
import com.cadiducho.telegrambotapi.exception.TelegramException;
import com.cadiducho.telegrambotapi.game.GameHighScore;
import com.cadiducho.telegrambotapi.handlers.BotUpdatesPoller;
import com.cadiducho.telegrambotapi.handlers.DefaultBotUpdatesPoller;
import com.cadiducho.telegrambotapi.payment.LabeledPrice;
import com.cadiducho.telegrambotapi.payment.ShippingOption;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

public class TelegramBot implements BotAPI {

    private final String apiUrl;
    @Getter private final BotAPI instance;
    @Getter private BotUpdatesPoller updatesPoller;

    private final Gson gson = new Gson();
    
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
        updatesPoller = new DefaultBotUpdatesPoller(this);
    }

    @Override
    public User getMe() throws TelegramException {
        final String resultBody = handleRequest(Unirest.get(apiUrl + "getMe"));

        return gson.fromJson(resultBody, User.class);
    }
    
    //handleRequest and checkReply by Rainu
    private String handleRequest(BaseRequest request) throws TelegramException {
        JSONObject jsonResult;
        try {
            jsonResult = request.asJson().getBody().getObject();
        } catch (UnirestException e) {
            throw new TelegramException("Could not get a response.", e);
        }

        if (jsonResult.get("ok").equals(false)){
            throw new TelegramException(jsonResult.getString("description"));
        }

        return jsonResult.get("result").toString();
    }
    
    private void checkChatId(Object chatId) {
        if (chatId != null) {
            if (!(  chatId instanceof String ||
                    chatId instanceof Integer ||
                    chatId instanceof Long)){
        
                throw new IllegalStateException("The chatId must be a String or a Number!");
            } 
        }
    }
     
    private void checkReply(Object replyMarkup) {
        if(replyMarkup != null){
            if(!(   replyMarkup instanceof ReplyKeyboardRemove ||
                    replyMarkup instanceof ReplyKeyboardMarkup ||
                    replyMarkup instanceof InlineKeyboardMarkup ||
                    replyMarkup instanceof ForceReply)){

                throw new IllegalStateException("The replyMarkup must be on of the following classes: " +
                    ReplyKeyboardRemove.class.getName() + ", " +
                    ReplyKeyboardMarkup.class.getName() + ", " +
                    InlineKeyboardMarkup.class.getName() + ", " +
                    ForceReply.class.getName());
            }
        }
    }
    
    private Map<String, Object> safe(String str, Object obj) {
        final Map<String, Object> parameters = new HashMap<>();
        
        //Check markup style if exists
        if(obj != null && str.equals("reply_markup")) {
            parameters.put("reply_markup", gson.toJson(obj));
            return parameters;
        }
        
        //Return normal values (check optionals -> null)
        if (obj != null) parameters.put(str, obj);
        
        return parameters;
    }
    //----------------

    @Override
    public Message sendMessage(Object chat_id, String text, String parse_mode, Boolean disable_web_page_preview, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("text", text));
        par.putAll(safe("parse_mode", parse_mode));
        par.putAll(safe("disable_web_page_preview", disable_web_page_preview));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));

        final String resultBody = handleRequest(Unirest.post(apiUrl + "sendMessage").fields(par));

        return gson.fromJson(resultBody, Message.class);
    }
    
    @Override
    public Message forwardMessage(Object chat_id, Integer from_chat_id, Boolean disable_notification, Integer message_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("from_chat_id", from_chat_id));
        par.putAll(safe("message_id", message_id));     
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "forwardMessage").queryString(par));

        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendPhoto(Object chat_id, Object photo, String caption, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("caption", caption));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody;
        if (photo instanceof String) {
            par.put("photo", photo);

            resultBody = handleRequest(Unirest.post(apiUrl + "sendPhoto").fields(par));
        } else if (photo instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "sendPhoto").queryString(par).field("photo", (java.io.File) photo));
        } else {
            throw new IllegalArgumentException("The photo must be a string or a file!");
        }

        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendAudio(Object chat_id, Object audio, String caption, Integer duration, String performer, String title, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("caption", caption));
        par.putAll(safe("duration", duration));
        par.putAll(safe("performer", performer));
        par.putAll(safe("title", title));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody;
        if (audio instanceof String) {
            par.put("audio", audio);

            resultBody = handleRequest(Unirest.post(apiUrl + "sendAudio").fields(par));
        } else if(audio instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "sendAudio").queryString(par).field("audio", (java.io.File) audio));
        } else {
            throw new IllegalArgumentException("The audio must be a string or a file!");
        }

        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendDocument(Object chat_id, Object document, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody;
        if (document instanceof String) {
            par.put("document", document);

            resultBody = handleRequest(Unirest.post(apiUrl + "sendDocument").fields(par));
        } else if(document instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "sendDocument").queryString(par).field("document", (java.io.File) document));
        } else {
            throw new IllegalArgumentException("The document must be a string or a file!");
        }

        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendVideo(Object chat_id, Object video, Integer duration, Integer width, Integer height, String caption, String parse_mode, Boolean supports_streaming, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("duration", duration));
        par.putAll(safe("width", width));
        par.putAll(safe("height", height));
        par.putAll(safe("caption", caption));
        par.putAll(safe("parse_mode", parse_mode));
        par.putAll(safe("supports_streaming", supports_streaming));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody;
        if (video instanceof String) {
            par.put("video", video);

            resultBody = handleRequest(Unirest.post(apiUrl + "sendVideo").fields(par));
        } else if(video instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "sendVideo").queryString(par).field("video", (java.io.File) video));
        } else {
            throw new IllegalArgumentException("The video must be a string or a file!");
        }

        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendVoice(Object chat_id, Object voice, String caption, Integer duration, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("caption", caption));
        par.putAll(safe("duration", duration));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody;
        if (voice instanceof String) {
            par.put("voice", voice);

            resultBody = handleRequest(Unirest.post(apiUrl + "sendVoice").fields(par));
        } else if(voice instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "sendVoice").queryString(par).field("voice", (java.io.File) voice));
        } else {
            throw new IllegalArgumentException("The voice must be a string or a file!");
        }

        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendMediaGroup(Object chat_id, List<InputMedia> media, Boolean disable_notification, Integer reply_to_message_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("media", gson.toJson(media)));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));

        final String resultBody = handleRequest(Unirest.post(apiUrl + "sendMediaGroup").fields(par));
        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendLocation(Object chat_id, Float latitude, Float longitude, Integer live_period, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("latitude", latitude));
        par.putAll(safe("longitude", longitude));
        par.putAll(safe("live_period", live_period));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));

        final String resultBody = handleRequest(Unirest.post(apiUrl + "sendLocation").fields(par));
        return gson.fromJson(resultBody, Message.class);
    }
    
    @Override
    public Object editMessageLiveLocation(Object chat_id, Integer message_id, String inline_message_id, Float latitude, Float longitude, InlineKeyboardMarkup reply_markup) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        par.putAll(safe("inline_message_id", inline_message_id));
        par.putAll(safe("latitude", latitude));
        par.putAll(safe("longitude", longitude));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "editMessageLiveLocation").queryString(par));
        if (resultBody.equalsIgnoreCase("True")) {
            return true;
        } else {
            return gson.fromJson(resultBody, Message.class);
        }
    }
    
    @Override
    public Object stopMessageLiveLocation(Object chat_id, Integer message_id, String inline_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        par.putAll(safe("inline_message_id", inline_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "stopMessageLiveLocation").queryString(par));
        if (resultBody.equalsIgnoreCase("True")) {
            return true;
        } else {
            return gson.fromJson(resultBody, Message.class);
        }
    }
    
    @Override
    public Message sendVideoNote(Object chat_id, Object video_note, Integer duration, Integer length, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("duration", duration));
        par.putAll(safe("length ", length));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody;
        if (video_note instanceof String) {
            par.put("voice", video_note);

            resultBody = handleRequest(Unirest.post(apiUrl + "sendVideoNote").fields(par));
        } else if(video_note instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "sendVideoNote").queryString(par).field("video_note", (java.io.File) video_note));
        } else {
            throw new IllegalArgumentException("The video note must be a string or a file!");
        }

        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendVenue(Object chat_id, Float latitude, Float longitude, String title, String address, String foursquare_id, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("latitude", latitude));
        par.putAll(safe("longitude", longitude));
        par.putAll(safe("title", title));
        par.putAll(safe("address", address));
        par.putAll(safe("foursquare_id", foursquare_id));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));

        final String resultBody = handleRequest(Unirest.post(apiUrl + "sendVenue").fields(par));
        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Message sendContact(Object chat_id, String phone_number, String first_name, String last_name, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("phone_number", phone_number));
        par.putAll(safe("first_name", first_name));
        par.putAll(safe("last_name", last_name));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));

        final String resultBody = handleRequest(Unirest.post(apiUrl + "sendContact").fields(par));
        return gson.fromJson(resultBody, Message.class);
    }
    
    @Override
    public Boolean sendChatAction(Object chat_id, String action) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("action", action));
        
        final String resultBody = handleRequest(Unirest.post(apiUrl + "sendChatAction").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public UserProfilePhotos getUserProfilePhotos(Integer user_id, Integer offset, Integer limit) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("offset", offset));
        par.putAll(safe("limit", limit));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "getUserProfilePhotos").queryString(par));
        return gson.fromJson(resultBody, UserProfilePhotos.class);
    }

    @Override
    public File getFile(String file_id) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("file_id", file_id));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "getFile").queryString(par));
        return gson.fromJson(resultBody, File.class);
    }
    
    @Override
    public Boolean kickChatMember(Object chat_id, Integer user_id, Integer until_date) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("until_date", until_date));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "kickChatMember").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean restrictChatMember(Object chat_id, Integer user_id, Integer until_date, Boolean can_send_messages, Boolean can_send_media_messages, Boolean can_send_other_messages, Boolean can_add_web_page_previews) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("until_date", until_date));
        par.putAll(safe("can_send_messages", can_send_messages));
        par.putAll(safe("can_send_media_messages", can_send_media_messages));
        par.putAll(safe("can_send_other_messages", can_send_other_messages));
        par.putAll(safe("can_add_web_page_previews", can_add_web_page_previews));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "restrictChatMember").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean promoteChatMember(Object chat_id, Integer user_id, Boolean can_change_info, Boolean can_post_messages, Boolean can_edit_messages, Boolean can_delete_messages, Boolean can_invite_users, Boolean can_restrict_members, Boolean can_pin_messages, Boolean can_promote_members) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("can_change_info", can_change_info));
        par.putAll(safe("can_post_messages", can_post_messages));
        par.putAll(safe("can_edit_messages", can_edit_messages));
        par.putAll(safe("can_delete_messages", can_delete_messages));
        par.putAll(safe("can_invite_users", can_invite_users));
        par.putAll(safe("can_restrict_members", can_restrict_members));
        par.putAll(safe("can_pin_messages", can_pin_messages));
        par.putAll(safe("can_promote_members", can_promote_members));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "promoteChatMember").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public String exportChatInviteLink(Object chat_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));

        return handleRequest(Unirest.get(apiUrl + "exportChatInviteLink").queryString(par));
    }

    @Override
    public Boolean setChatPhoto(Object chat_id, java.io.File photo) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));

        final String resultBody = handleRequest(Unirest.post(apiUrl + "setChatPhoto").queryString(par).field("photo", photo));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean deleteChatPhoto(Object chat_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "deleteChatPhoto").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean setChatTitle(Object chat_id, String title) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("title", title));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "setChatTitle").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean setChatDescription(Object chat_id, String description) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("description", description));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "setChatDescription").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean pinChatMessage(Object chat_id, Integer message_id, Boolean disable_notification) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        par.putAll(safe("disable_notification", disable_notification));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "pinChatMessage").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean unpinChatMessage(Object chat_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();

        par.putAll(safe("chat_id", chat_id));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "unpinChatMessage").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Boolean leaveChat(Object chat_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "leaveChat").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean unbanChatMember(Object chat_id, Integer user_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("user_id", user_id));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "unbanChatMember").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Chat getChat(Object chat_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "getChat").queryString(par));
        return gson.fromJson(resultBody, Chat.class);
    }
    
    @Override
    public List<ChatMember> getChatAdministrators(Object chat_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "getChatAdministrators").queryString(par));
        Type listType = new TypeToken<List<ChatMember>>() {}.getType();
        return gson.fromJson(resultBody, listType);
    }
    
    @Override
    public Integer getChatMembersCount(Object chat_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "getChatMembersCount").queryString(par));
        return gson.fromJson(resultBody, Integer.class);
    }
    
    @Override
    public ChatMember getChatMember(Object chat_id, Integer user_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("user_id", user_id));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "getChatMember").queryString(par));
        return gson.fromJson(resultBody, ChatMember.class);
    }
    
    @Override
    public Boolean setChatStickerSet(Object chat_id, String sticker_set_name) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("sticker_set_name", sticker_set_name));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "setChatStickerSet").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Boolean deleteChatStickerSet(Object chat_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "deleteChatStickerSet").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean answerCallbackQuery(String callback_query_id, String text, Boolean show_alert, String url, Integer cache_time) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("callback_query_id", callback_query_id));
        par.putAll(safe("text", text));
        par.putAll(safe("show_alert", show_alert));
        par.putAll(safe("url", url));
        par.putAll(safe("cache_time", cache_time));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "answerCallbackQuery").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Boolean editMessageText(Object chat_id, Integer message_id, String inline_message_id, String text, String parse_mode, Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        par.putAll(safe("inline_message_id", inline_message_id));
        par.putAll(safe("text", text));
        par.putAll(safe("parse_mode", parse_mode));
        par.putAll(safe("disable_web_page_preview", disable_web_page_preview));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "editMessageText").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean editMessageCaption(Object chat_id, Integer message_id, String inline_message_id, String caption, InlineKeyboardMarkup reply_markup) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        par.putAll(safe("caption", caption));
        par.putAll(safe("inline_message_id", inline_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "editMessageText").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean editMessageReplyMarkup(Object chat_id, Integer message_id, String inline_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        par.putAll(safe("inline_message_id", inline_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "editMessageText").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Boolean deleteMessage(Object chat_id, Integer message_id) throws TelegramException {
        checkChatId(chat_id);
        
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "deleteMessage").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public List<Update> getUpdates(Integer offset, Integer limit, Integer timeout, List<String> allowed_updates) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("offset", offset));
        par.putAll(safe("limit", limit));
        par.putAll(safe("timeout", timeout));
        par.putAll(safe("allowed_updates", gson.toJson(allowed_updates)));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "getUpdates").queryString(par));
        Type listType = new TypeToken<List<Update>>() {}.getType();
        return gson.fromJson(resultBody, listType);
    }
    
    @Override
    public WebhookInfo getWebhookInfo() throws TelegramException {
        final String resultBody = handleRequest(Unirest.get(apiUrl + "getWebhookInfo"));

        return gson.fromJson(resultBody, WebhookInfo.class);
    }
    

    @Override
    public Boolean setWebhook(String url, java.io.File certificate, Integer max_connections, List<String> allowed_updates) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("url", url));
        par.putAll(safe("certificate", certificate));
        par.putAll(safe("max_connections", max_connections));
        par.putAll(safe("allowed_updates", allowed_updates));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "setWebhook").queryString(par));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean deleteWebhook() throws TelegramException {
        final String resultBody = handleRequest(Unirest.get(apiUrl + "deleteWebhook"));
        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Message sendSticker(Object chat_id, Object sticker, Boolean disable_notification, Integer reply_to_message_id, Object reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody;
        if (sticker instanceof String) {
            par.put("sticker", sticker);

            resultBody = handleRequest(Unirest.post(apiUrl + "sendSticker").fields(par));
        } else if(sticker instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "sendSticker").queryString(par).field("sticker", (java.io.File) sticker));
        } else {
            throw new IllegalArgumentException("The sticker must be a string or a file!");
        }

        return gson.fromJson(resultBody, Message.class);
    }
    
    @Override
    public StickerSet getStickerSet(String name) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("name", name));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "getStickerSet").queryString(par));
        return gson.fromJson(resultBody, StickerSet.class);
    }
    
    @Override
    public File uploadStickerFile(Integer user_id, java.io.File png_sticker) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("png_sticker", png_sticker));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "uploadStickerFile").queryString(par));
        return gson.fromJson(resultBody, File.class);
    }
    
    @Override
    public Boolean createNewStickerSet(Integer user_id, String name, String title, Object png_sticker, String emojis, Boolean contains_masks, MaskPosition mask_position) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("name", name));
        par.putAll(safe("title", title));
        par.putAll(safe("emojis", emojis));
        par.putAll(safe("contains_masks", contains_masks));
        par.putAll(safe("mask_position", mask_position));
        
        final String resultBody;
        if (png_sticker instanceof String) {
            par.put("png_sticker", png_sticker);

            resultBody = handleRequest(Unirest.post(apiUrl + "createNewStickerSet").fields(par));
        } else if(png_sticker instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "createNewStickerSet").queryString(par).field("png_sticker", (java.io.File) png_sticker));
        } else {
            throw new IllegalArgumentException("The png_sticker must be a string or a file!");
        }
        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Boolean addStickerToSet(Integer user_id, String name, Object png_sticker, String emojis, MaskPosition mask_position) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("name", name));
        par.putAll(safe("emojis", emojis));
        par.putAll(safe("mask_position", mask_position));
        
        final String resultBody;
        if (png_sticker instanceof String) {
            par.put("png_sticker", png_sticker);

            resultBody = handleRequest(Unirest.post(apiUrl + "addStickerToSet").fields(par));
        } else if(png_sticker instanceof java.io.File) {
            resultBody = handleRequest(Unirest.post(apiUrl + "addStickerToSet").queryString(par).field("png_sticker", (java.io.File) png_sticker));
        } else {
            throw new IllegalArgumentException("The png_sticker must be a string or a file!");
        }
        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Boolean setStickerPositionInSet(String sticker, Integer position) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("sticker", sticker));
        par.putAll(safe("position", position));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "setStickerPositionInSet").queryString(par));

        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Boolean deleteStickerFromSet(String sticker) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("sticker", sticker));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "deleteStickerFromSet").queryString(par));

        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Boolean answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, Integer cache_time, Boolean is_personal, String next_offset,
            String switch_pm_text, String switch_pm_parameter) throws TelegramException {

        final Map<String, Object> par = new HashMap<>();
        par.putAll(safe("inline_query_id", inlineQueryId));

        par.put("results", gson.toJson(results));

        par.putAll(safe("cache_time", cache_time));
        par.putAll(safe("is_personal", is_personal));
        par.putAll(safe("next_offset", next_offset));
        par.putAll(safe("switch_pm_text", switch_pm_text));
        par.putAll(safe("switch_pm_parameter", switch_pm_parameter));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "answerInlineQuery").queryString(par));

        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Message sendInvoice(Integer chat_id, String title, String description, String payload, String provider_token, String start_parameter, String currency,
            List<LabeledPrice> prices, String provider_data, String photo_url, Integer photo_size, Integer photo_width, Integer photo_height, Boolean need_name, Boolean need_phone_number,
            Boolean need_email, Boolean need_shipping_address, Boolean send_phone_number_to_provider, Boolean send_email_to_provider, Boolean is_flexible, Boolean disable_notification, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        
        checkChatId(chat_id);
        checkReply(reply_markup);
        
        final Map<String, Object> par = new HashMap<>();
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("title", title));
        par.putAll(safe("description", description));
        par.putAll(safe("payload", payload));
        par.putAll(safe("provider_token", provider_token));
        par.putAll(safe("start_parameter", start_parameter));
        par.putAll(safe("currency", currency));

        par.put("prices", gson.toJson(prices));

        par.putAll(safe("provider_data", provider_data));
        par.putAll(safe("photo_url", photo_url));
        par.putAll(safe("photo_size", photo_size));
        par.putAll(safe("photo_width", photo_width));
        par.putAll(safe("photo_height", photo_height));
        par.putAll(safe("need_name", need_name));
        par.putAll(safe("need_phone_number", need_phone_number));
        par.putAll(safe("need_email", need_email));
        par.putAll(safe("need_shipping_address", need_shipping_address));
        par.putAll(safe("send_phone_number_to_provider", send_phone_number_to_provider));
        par.putAll(safe("send_email_to_provider", send_email_to_provider));
        par.putAll(safe("is_flexible", is_flexible));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));

        final String resultBody = handleRequest(Unirest.post(apiUrl + "sendInvoice").fields(par));
        return gson.fromJson(resultBody, Message.class);
    }
    
    @Override
    public Boolean answerShippingQuery(String shipping_query_id, Boolean ok, List<ShippingOption> shipping_options, String error_message) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        par.putAll(safe("shipping_query_id", shipping_query_id));
        par.putAll(safe("ok", ok));
        par.put("shipping_options", gson.toJson(shipping_options));
        par.putAll(safe("error_message", error_message));
        
        final String resultBody = handleRequest(Unirest.get(apiUrl + "answerShippingQuery").queryString(par));

        return "True".equalsIgnoreCase(resultBody);
    }
    
    @Override
    public Boolean answerPreCheckoutQuery(String pre_checkout_query_id, Boolean ok, String error_message) throws TelegramException {
        final Map<String, Object> par = new HashMap<>();
        par.putAll(safe("pre_checkout_query_id", pre_checkout_query_id));
        par.putAll(safe("ok", ok));
        par.putAll(safe("error_message", error_message));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "answerPreCheckoutQuery").queryString(par));

        return "True".equalsIgnoreCase(resultBody);
    }

    @Override
    public Message sendGame(Object chat_id, String game_short_name, Boolean disable_notification, Integer reply_to_message_id, InlineKeyboardMarkup reply_markup) throws TelegramException {
        checkChatId(chat_id);
        checkReply(reply_markup);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("game_short_name", game_short_name));
        par.putAll(safe("disable_notification", disable_notification));
        par.putAll(safe("reply_to_message_id", reply_to_message_id));
        par.putAll(safe("reply_markup", reply_markup));
        
        final String resultBody = handleRequest(Unirest.post(apiUrl + "sendGame").fields(par));
        
        return gson.fromJson(resultBody, Message.class);
    }

    @Override
    public Object setGameScore(Integer user_id, Integer score, Boolean force, Boolean disable_edit_message, Object chat_id, Integer message_id, String inline_message_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("score", score));
        par.putAll(safe("force", force));
        par.putAll(safe("disable_edit_message", disable_edit_message));
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        par.putAll(safe("inline_message_id", inline_message_id));
        
        final String resultBody = handleRequest(Unirest.post(apiUrl + "setGameScore").fields(par));
        
        Object methodReturn;
        try {
            methodReturn = gson.fromJson(resultBody, Message.class);
        } catch (JsonSyntaxException ex) {
            try {
                methodReturn = gson.fromJson(resultBody, Boolean.class);
            } catch (JsonSyntaxException ex2) {
                methodReturn = null;
            }
        }
        
        return methodReturn;
    }

    @Override
    public List<GameHighScore> getGameHighScores(Integer user_id, Object chat_id, Integer message_id, String inline_message_id) throws TelegramException {
        checkChatId(chat_id);
        final Map<String, Object> par = new HashMap<>();
        
        par.putAll(safe("user_id", user_id));
        par.putAll(safe("chat_id", chat_id));
        par.putAll(safe("message_id", message_id));
        par.putAll(safe("inline_message_id", inline_message_id));

        final String resultBody = handleRequest(Unirest.get(apiUrl + "getGameGighScores").queryString(par));
        Type listType = new TypeToken<List<GameHighScore>>() {}.getType();
        return gson.fromJson(resultBody, listType);
    }
    
}
