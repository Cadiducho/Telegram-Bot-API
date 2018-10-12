package com.cadiducho.telegrambotapi.util;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.Type;

import lombok.Builder;
import lombok.Value;
import okio.BufferedSource;

/**
 * A container that parse an API response
 * @author Cadiducho
 * @param <T> Type of the result
 */
@Value
@Builder
public class ApiResponse<T> {

    /**
     * Check if the response was success or not.
     */
    Boolean ok;

    /**
     * Optional. The message of the response.
     */
    String description;

    /**
     * Object responded by the API.
     */
    T result;

    private static final Moshi moshi = new Moshi.Builder().build();

    /**
     * Serialize this response to json
     * @return String that contains the serialized response
     */
    public String toJson() {
        //Only parametrize Result object if this is not null
        Class<?> type = (result != null ? result.getClass() : Object.class);
        return moshi.adapter(Types.newParameterizedType(ApiResponse.class, type)).toJson(this);
    }

    /**
     * Create an ApiResponse object from a String
     * @param <T> Type of the result
     * @param source String fetched from the API
     * @param resultType Class of the result
     * @return An ApiResponse object with that data
     * @throws IOException If Moshi fails
     */
    public static <T> ApiResponse<T> from(String source, Type resultType) throws IOException {
        JsonAdapter<ApiResponse<T>> resAdapter = moshi.adapter(Types.newParameterizedType(ApiResponse.class, resultType));
        return resAdapter.fromJson(source);
    }

    /**
     * Create an ApiResponse object from a BufferedSource
     * @param <T> Type of the result
     * @param source String fetched from the API
     * @param resultType Class of the result
     * @return An ApiResponse object with that data
     * @throws IOException If Moshi fails
     */
    public static <T> ApiResponse<T> from(BufferedSource source, Type resultType) throws IOException {
        JsonAdapter<ApiResponse<T>> resAdapter = moshi.adapter(Types.newParameterizedType(ApiResponse.class, resultType));
        return resAdapter.fromJson(source);
    }
}