package com.atguigu.gmall.flume.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class JSONUtil {


    public static boolean isJSONValidate(String log) {
        try {
            JSONObject.parseObject(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

/**
 * @program: flume-interceptor
 * @description: 判断数据是否是完整的json格式
 * @author: lydms
 * @create: 2023-08-14 20:19
 **/
