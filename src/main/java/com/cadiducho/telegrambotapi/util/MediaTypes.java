package com.cadiducho.telegrambotapi.util;

import okhttp3.MediaType;

public class MediaTypes {
    public static MediaType MEDIA_TYPE_PHOTO = MediaType.parse("image/*");
    public static MediaType MEDIA_TYPE_VIDEO = MediaType.parse("video/*");
    public static MediaType MEDIA_TYPE_AUDIO = MediaType.parse("audio/*");
    public static MediaType MEDIA_TYPE_APPLICATION = MediaType.parse("application/*");
}
