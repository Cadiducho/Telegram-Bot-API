/*
 * The MIT License
 *
 * Copyright 2019 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.cadiducho.telegrambotapi.game.Game;
import com.cadiducho.telegrambotapi.inline.InlineKeyboardMarkup;
import com.cadiducho.telegrambotapi.payment.Invoice;
import com.cadiducho.telegrambotapi.payment.SuccessfulPayment;
import java.util.List;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents a message.
 */
@ToString
@Getter @Setter
public class Message {

    /**
     * Unique message identifier
     */
    @Json(name = "message_id") private Integer messageId;
         
    /**
     * Sender
     */
    private User from;
    
    /**
     * Date the message was sent in Unix time
     */
    private Integer date;
    
    /**
     * Conversation the message belongs to — user in case of a private message, GroupChat in case of a group. User or GroupChat.
     */
    private Chat chat;
    
    /**
     * Optional. For forwarded messages, sender of the original message
     */
    @Json(name = "forward_from") private User forwardFrom;
    
    /**
     * Optional. For messages forwarded from a channel, information about the original channel
     */
    @Json(name = "forward_from_chat") private Chat forwardFromChat;
    
    /**
     * Optional. For forwarded channel posts, identifier of the original message in the channel
     */
    @Json(name = "forward_from_message_id") private Integer forwardFromMessageId;
    
    /**
     * Optional. For messages forwarded from channels, signature of the post author if present
     */
    @Json(name = "forward_signature") private String forwardSignature;
    
    /**
     * Optional. Sender's name for messages forwarded from users who disallow adding a link to their account in forwarded messages
     */
    @Json(name = "forward_sender_name") private String forwardSenderName;
    
    /**
     * Optional. For forwarded messages, date the original message was sent in Unix time
     */
    @Json(name = "forward_date") private Integer forwardDate;
    
    /**
     * Optional. For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
     */
    @Json(name = "reply_to_message") private Message replyToMessage;
    
    /**
     * Optional. Date the message was last edited in Unix time
     */
    @Json(name = "edit_date") private Integer editDate;
    
    /**
     * Optional. Signature of the post author for messages in channels
     */
    @Json(name = "author_signature") private String authorSignature;
    
    /**
     * Optional. For text messages, the actual UTF-8 text of the message
     */
    private String text;
    
    /**
     * Optional. For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text
     */
    private List<MessageEntity> entities;
    
    /**
     * Optional. For messages with a caption, special entities like usernames, URLs, bot commands, etc. that appear in the caption
     */
    @Json(name = "caption_entities") private List<MessageEntity> captionEntities;
    
    /**
     * Optional. Message is an audio file, information about the file
     */
    private Audio audio;
    
    /**
     * Optional. Message is a general file, information about the file
     */
    private Document document;

    /**
     * Optional. Message is an animation, information about the animation. For backward compatibility, when this field is set, the document field will also be set
     */
    private Animation animation;
    
    /**
     * Optional. Message is a game, information about the game. Check https://core.telegram.org/bots/api#games
     */
    private Game game;
    
    /**
     * Optional. Message is a photo, available sizes of the photo
     */
    private List<PhotoSize> photo;
    
    /**
     * Optional. Message is a sticker, information about the sticker
     */
    private Sticker sticker;
    
    /**
     * Optional. Message is a video, information about the video
     */
    private Video video;
    
    /**
     * Optional. Message is a voice message, information about the file
     */
    private Voice voice;
    
    /**
     * Optional. Message is a video note, information about the video message
     */
    @Json(name = "video_note") private VideoNote videoNote;
    
    /**
     * Optional. Caption for the photo or video
     */
    private String caption;
    
    /**
     * Optional. Message is a shared contact, information about the contact
     */
    private Contact contact;
    
    /**
     * Optional. Message is a shared location, information about the location
     */
    private Location location;
    
    /**
     * Optional. Message is a venue, information about the venue
     */
    private Venue venue;
    
    /**
     * Optional. Message is a native poll, information about the poll
     */
    private Poll poll;

    /**
     * Optional. Message is a dice with random value from 1 to 6
     */
    private Dice dice;
    
    /**
     * Optional. New members that were added to the group or supergroup and information about them (the bot itself may be one of these members)
     */
    @Json(name = "new_chat_members") private List<User> newChatMembers;
    
    /**
     * Optional. A member was removed from the group, information about them (this member may be bot itself)
     */
    @Json(name = "left_chat_member") private User leftChatMember;
    
    /**
     * Optional. Message is a shared location, information about the location
     */
    @Json(name = "new_chat_title") private String newChatTitle;
    
    /**
     * Optional. A group photo was change to this value
     */
    @Json(name = "new_chat_photo") private List<PhotoSize> newChatPhoto;
    
    /**
     * Optional. Informs that the group photo was deleted
     */
    @Json(name = "delete_chat_photo") private Boolean deleteChatPhoto;
    
    /**
     * Optional. Informs that the group has been created
     */
    @Json(name = "group_chat_created") private Boolean groupChatCreated;
    
    /**
     * Optional. Informs that the supergroup has been created
     */
    @Json(name = "supergroup_chat_created") private Boolean supergroupChatCreated;
    
    /**
     * Optional. Informs that the channel has been created
     */
    @Json(name = "channel_chat_created") private Boolean channelChatCreated;
    
    /**
     * Optional. The chat has been migrated to a chat with specified identifier, not exceeding 1e13 by absolute value
     */
    @Json(name = "migrate_to_chat_id") private Long migrateToChatId;
    
    /**
     * Optional. The chat has been migrated from a chat with specified identifier, not exceeding 1e13 by absolute value
     */
    @Json(name = "migrate_from_chat_id") private Long migrateFromChatId;
    
    /**
     * Optional. Specified message was pinned. Note that the Message object in this field will not contain further reply_to_message fields even if it is itself a reply.
     */
    @Json(name = "pinned_message") private Message pinnedMessage;
    
    /**
     * Optional. Message is an invoice for a payment, information about the invoice. More about payments »
     */
    private Invoice invoice;
    
    /**
     * Optional. Message is a service message about a successful payment, information about the payment
     */
    @Json(name = "successful_payment") private SuccessfulPayment successfulPayment;

    /**
     * Optional. The domain name of the website on which the user has logged in. See https://core.telegram.org/widgets/login
     */
    @Json(name = "connected_website") private String connectedWebsite;

    /**
     * Optional. Inline keyboard attached to the message. login_url buttons are represented as ordinary url buttons.
     */
    @Json(name = "reply_markup") private InlineKeyboardMarkup replyMarkup;

    /**
     * Type of message, can be either text, audio, document, animation, game, photo, sticker, video, video_note, contact,
     *      location, venue, new_chat_member, left_chat_member, new_chat_tile, new_chat_photo, delete_chat_photo,
     *      group_chat_created, supergroup_chat_created, channel_chat_created, migrate_to_chat_id, migrate_from_chat_id,
     *      pinned_message, invoice, successful_payment, connected_website or passport_data
     */
    private Type type;
    
    /**
     * Returns the {@link com.cadiducho.telegrambotapi.Message.Type} of this Message.
     * @return The {@code Type} of this {@code Message}
     */
    public Type getType() {
        if (type == null) 
            determineType();
        return type;
    }

    private void determineType() {
        if (text != null) type = Type.TEXT;
        else if (audio != null) type = Type.AUDIO;
        else if (document != null) type = Type.DOCUMENT;
        else if (animation != null) type = Type.ANIMATION;
        else if (game != null) type = Type.GAME;
        else if (photo != null) type = Type.PHOTO;
        else if (sticker != null) type = Type.STICKER;
        else if (video != null) type = Type.VIDEO;
        else if (voice != null) type = Type.VOICE;
        else if (videoNote != null) type = Type.VIDEO_NOTE;
        else if (contact != null) type = Type.CONTACT;
        else if (location != null) type = Type.LOCATION;
        else if (venue != null) type = Type.VENUE;
        else if (newChatMembers != null) type = Type.NEW_CHAT_MEMBERS;
        else if (leftChatMember != null) type = Type.LEFT_CHAT_MEMBER;
        else if (newChatTitle != null) type = Type.NEW_CHAT_TITLE;
        else if (newChatPhoto != null) type = Type.NEW_CHAT_PHOTO;
        else if (deleteChatPhoto != null && deleteChatPhoto) type = Type.DELETE_CHAT_PHOTO;
        else if (groupChatCreated != null && groupChatCreated) type = Type.GROUP_CHAT_CREATED;
        else if (supergroupChatCreated != null && supergroupChatCreated) type = Type.SUPERGROUP_CHAT_CREATED;
        else if (channelChatCreated != null && channelChatCreated) type = Type.CHANNEL_CHAT_CREATED;
        else if (migrateToChatId != null) type = Type.MIGRATE_TO_CHAT_ID;
        else if (migrateFromChatId != null) type = Type.MIGRATE_FROM_CHAT_ID;
        else if (pinnedMessage != null) type = Type.PINNED_MESSAGE;
        else if (invoice != null) type = Type.INVOICE;
        else if (successfulPayment != null) type = Type.SUCCESSFUL_PAYMENT;
        else if (connectedWebsite != null) type = Type.CONNECTED_WEBSITE;
        else type = Type.UNKNOWN;
    }
    
    /**
     * Defines the different types of Messages that can be received.
     */
    public enum Type {
        TEXT,
        AUDIO,
        DOCUMENT,
        ANIMATION,
        GAME,
        PHOTO,
        STICKER,
        VIDEO,
        VOICE,
        VIDEO_NOTE,
        CONTACT,
        LOCATION,
        VENUE,
        NEW_CHAT_MEMBERS,
        LEFT_CHAT_MEMBER,
        NEW_CHAT_TITLE,
        NEW_CHAT_PHOTO,
        DELETE_CHAT_PHOTO,
        GROUP_CHAT_CREATED,
        SUPERGROUP_CHAT_CREATED, 
        CHANNEL_CHAT_CREATED, 
        MIGRATE_TO_CHAT_ID, 
        MIGRATE_FROM_CHAT_ID, 
        PINNED_MESSAGE,
        INVOICE, 
        SUCCESSFUL_PAYMENT,
        CONNECTED_WEBSITE,
        UNKNOWN
    }

}
