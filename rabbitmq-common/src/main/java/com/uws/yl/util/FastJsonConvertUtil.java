package com.uws.yl.util;

import com.alibaba.fastjson.JSON;

public class FastJsonConvertUtil {
    public static String  converObjectToJson(Object obj){
        return JSON.toJSONString(obj);
    }

    public static <T> T jsonStringToObject(String jsonString,Class<T> clazz){
         return JSON.parseObject(jsonString, clazz);
    }
}
