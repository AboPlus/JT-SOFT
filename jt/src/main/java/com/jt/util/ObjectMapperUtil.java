package com.jt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 刘昱江
 * 时间 2021/4/16
 */
public class ObjectMapperUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJSON(Object object){
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <T> T toObj(String json, Class<T> target){
        try {
            return MAPPER.readValue(json,target);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
