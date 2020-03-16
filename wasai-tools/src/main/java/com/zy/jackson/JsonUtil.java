package com.zy.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper om = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final ObjectMapper omRest = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setDateFormat(new ISO8601DateFormat())
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static String toJsonRest(Object o) {
        try {
            return omRest.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object o) {
        try {
            return om.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String str, Class<T> t) {
        try {
            return om.readValue(str, t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String str, TypeReference<T> type) {
        try {
            return om.readValue(str, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T fromJsonEmbedded(String str, Class<T> wrapper, Class embedded) {
        try {
            return om.readValue(str, om.getTypeFactory().constructParametricType(wrapper, embedded));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertValue(Object fromValue, Class<T> t) {
        try {
            return om.convertValue(fromValue, t);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> listFromJson(String str, Class<T> paramClass) {
        CollectionType listType = om.getTypeFactory().constructCollectionType(ArrayList.class, paramClass);
        try {
            return om.readValue(str, listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> listFromJsonNoExp(String str, Class<T> paramClass) {
        CollectionType listType = om.getTypeFactory().constructCollectionType(ArrayList.class, paramClass);
        try {
            return om.readValue(str, listType);
        } catch (IOException e) {
            log.error("deserialize string to list error! return empty list");
            return new ArrayList<>();
        }
    }

}
