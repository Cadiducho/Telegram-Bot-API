/*
 * The MIT License
 *
 * Copyright 2018 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.cadiducho.telegrambotapi.game.Game;
import com.cadiducho.telegrambotapi.payment.Invoice;
import com.cadiducho.telegrambotapi.payment.SuccessfulPayment;
import java.util.List;
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
    private Integer message_id;
         
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
    private User forward_from;
    
    /**
     * Optional. For messages forwarded from a channel, information about the original channel
     */
    private Chat forward_from_chat;
    
    /**
     * Optional. For forwarded channel posts, identifier of the original message in the channel
     */
    private Integer forward_from_message_id;
    
    /**
     * Optional. For messages forwarded from channels, signature of the post author if present
     */
    private String forward_signature;
    
    /**
     * Optional. For forwarded messages, date the original message was sent in Unix time
     */
    private Integer forward_date;
    
    /**
     * Optional. For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
     */
    private Message reply_to_message;
    
    /**
     * Optional. Date the message was last edited in Unix time
     */
    private Integer edit_date;
    
    /**
     * Optional. Signature of the post author for messages in channels
     */
    private String author_signature;
    
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
    private List<MessageEntity> caption_entities;
    
    /**
     * Optional. Message is an audio file, information about the file
     */
    private Audio audio;
    
    /**
     * Optional. Message is a general file, information about the file
     */
    private Document document;
    
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
    private VideoNote video_note;
    
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
     * 	Optional. Message is a venue, information about the venue
     */
    private Venue venue;
    
    /**
     * Optional. New members that were added to the group or supergroup and information about them (the bot itself may be one of these members)
     */
    private List<User> new_chat_members;
    
    /**
     * Optional. A member was removed from the group, information about them (this member may be bot itself)
     */
    private User left_chat_member;
    
    /**
     * Optional. Message is a shared location, information about the location
     */
    private String new_chat_title;
    
    /**
     * Optional. A group photo was change to this value
     */
    private List<PhotoSize> new_chat_photo;
    
    /**
     * Optional. Informs that the group photo was deleted
     */
    private Boolean delete_chat_photo; 
    
    /**
     * Optional. Informs that the group has been created
     */
    private Boolean group_chat_created;
    
    /**
     * Optional. Informs that the supergroup has been created
     */
    private Boolean supergroup_chat_created;
    
    /**
     * Optional. Informs that the channel has been created
     */
    private Boolean channel_chat_created;
    
    /**
     * Optional. The chat has been migrated to a chat with specified identifier, not exceeding 1e13 by absolute value
     */
    private Long migrate_to_chat_id;
    
    /**
     * Optional. The chat has been migrated from a chat with specified identifier, not exceeding 1e13 by absolute value
     */
    private Long migrate_from_chat_id;
    
    /**
     * Optional. Specified message was pinned. Note that the Message object in this field will not contain further reply_to_message fields even if it is itself a reply.
     */
    private Message pinned_message;
    
    /**
     * Optional. Message is an invoice for a payment, information about the invoice. More about payments »
     */
    private Invoice invoice;
    
    /**
     * Optional. Message is a service message about a successful payment, information about the payment
     */
    private SuccessfulPayment successful_payment;

    /**
     * Optional. The domain name of the website on which the user has logged in. See https://core.telegram.org/widgets/login
     */
    private String connected_website;
    
    /**
     * Type of message, can be either text, audio, document, photo, sticker, video, contact, location, new_chat_participant
     *      left_chat_participant, new_chat_photo, delete_chat_photo or group_chat_created
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
        else if (game != null) type = Type.GAME;
        else if (photo != null) type = Type.PHOTO;
        else if (sticker != null) type = Type.STICKER;
        else if (video != null) type = Type.VIDEO;
        else if (voice != null) type = Type.VOICE;
        else if (video_note != null) type = Type.VIDEO_NOTE;
        else if (contact != null) type = Type.CONTACT;
        else if (location != null) type = Type.LOCATION;
        else if (venue != null) type = Type.VENUE;
        else if (new_chat_members != null) type = Type.NEW_CHAT_MEMBERS;
        else if (left_chat_member != null) type = Type.LEFT_CHAT_MEMBER;
        else if (new_chat_title != null) type = Type.NEW_CHAT_TITLE;
        else if (new_chat_photo != null) type = Type.NEW_CHAT_PHOTO;
        else if (delete_chat_photo != null && delete_chat_photo) type = Type.DELETE_CHAT_PHOTO;
        else if (group_chat_created != null && group_chat_created) type = Type.GROUP_CHAT_CREATED;
        else if (supergroup_chat_created != null && supergroup_chat_created) type = Type.SUPERGROUP_CHAT_CREATED;
        else if (channel_chat_created != null && channel_chat_created) type = Type.CHANNEL_CHAT_CREATED;
        else if (migrate_to_chat_id != null) type = Type.MIGRATE_TO_CHAT_ID;
        else if (migrate_from_chat_id != null) type = Type.MIGRATE_FROM_CHAT_ID;
        else if (pinned_message != null) type = Type.PINNED_MESSAGE;
        else if (invoice != null) type = Type.INVOICE;
        else if (successful_payment != null) type = Type.SUCCESSFUL_PAYMENT;
        else if (connected_website != null) type = Type.CONNECTED_WEBSITE;    
        else type = Type.UNKNOWN;
    }
    
    /**
     * Defines the different types of Messages that can be received.
     */
    public enum Type {
        TEXT,
        AUDIO,
        DOCUMENT,
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
        UPERGROUP_CHAT_CREATED, 
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