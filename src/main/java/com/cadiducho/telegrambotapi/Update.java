/*
 * The MIT License
 *
 * Copyright 2019 Cadiducho.
 * Read more in https://github.com/Cadiducho/Telegram-Bot-API/blob/master/LICENSE
 */

package com.cadiducho.telegrambotapi;

import com.cadiducho.telegrambotapi.inline.ChosenInlineResult;
import com.cadiducho.telegrambotapi.inline.InlineQuery;
import com.cadiducho.telegrambotapi.payment.PreCheckoutQuery;
import com.cadiducho.telegrambotapi.payment.ShippingQuery;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This object represents an incoming update.
 */
@ToString
@Getter @Setter
public class Update {
    
    /**
     * The update‘s unique identifier. 
     * Update identifiers start from a certain positive number and increase sequentially. 
     * This ID becomes especially handy if you’re using Webhooks, 
     * since it allows you to ignore repeated updates or to restore the correct update sequence, should they get out of order.
     */
    @Json(name = "update_id") private Integer updateId;
    
    /**
     * Optional. New incoming message of any kind — text, photo, sticker, etc.
     */
    private Message message;
    
    /**
     * Optional. New version of a message that is known to the bot and was edited
     */
    @Json(name = "edited_message") private Message editedMessage;
    
    /**
     * Optional. New incoming channel post of any kind — text, photo, sticker, etc.
     */
    @Json(name = "channel_post") private Message channelPost;
    
    /**
     * Optional. New version of a channel post that is known to the bot and was edited
     */
    @Json(name = "edited_channel_post") private Message editedChannelPost;
    /**
     * Optional. New incoming <a href="https://core.telegram.org/bots/api#inline-mode" >inline</a> query
     */
    @Json(name = "inline_query")  private InlineQuery inlineQuery;
    
    /**
     * 	Optional. The result of an New incoming <a href="https://core.telegram.org/bots/api#inline-mode" >inline</a> query query that was chosen by a user and sent to their chat partner.
     */
    @Json(name = "chosen_inline_result")  private ChosenInlineResult chosenInlineResult;
    
    /**
     * Optional. New incoming callback query
     */
    @Json(name = "callback_query") private CallbackQuery callbackQuery;
    
    /**
     * Optional. New incoming shipping query. Only for invoices with flexible price
     */
    @Json(name = "shipping_query") private ShippingQuery shippingQuery;
    
    /**
     * Optional. New incoming pre-checkout query. Contains full information about checkout
     */
    @Json(name = "pre_checkout_query") private PreCheckoutQuery preCheckoutQuery;
    
    /**
     * Optional. New poll state. Bots receive only updates about polls, which are sent or stopped by the bot
     */
    private Poll poll;

    /**
     * Optional. A user changed their answer in a non-anonymous poll.
     * Bots receive new votes only in polls that were sent by the bot itself.
     */
    @Json(name = "poll_answer") private PollAnswer pollAnswer;

    /**
     * Optional. The bot's chat member status was updated in a chat.
     * For private chats, this update is received only when the bot is blocked or unblocked by the user.
     */
    @Json(name = "my_chat_member") private ChatMemberUpdated myChatMember;

    /**
     * Optional. A chat member's status was updated in a chat.
     * The bot must be an administrator in the chat and must explicitly specify “chat_member” in the list of allowed_updates to receive these updates.
     */
    @Json(name = "chat_member") private ChatMemberUpdated chatMember;

    /**
     * Optional. A request to join the chat has been sent.
     * The bot must have the can_invite_users administrator right in the chat to receive these updates.
     */
    @Json(name = "chat_join_request") private ChatJoinRequest chatJoinRequest;
    
}
