package com.atguigu.gmall.flume.interceptor;

import com.atguigu.gmall.flume.utils.JSONUtil;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class ETLInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //1 获取body当中的数据
        byte[] body = event.getBody();
        String log = new String(body, StandardCharsets.UTF_8);
        //2 判断是不是合法的json
        //3 是：return event   不是：return null
        if (JSONUtil.isJSONValidate(log)){
            return event;
        }else {
            return null;
        }
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        Iterator<Event> iterator = list.iterator();
        while (iterator.hasNext()){
            Event event = iterator.next();
            if (intercept(event) == null){
                iterator.remove();
            }
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
/**
 * @program: flume-interceptor
 * @description:
 * @author: lydms
 * @create: 2023-08-14 00:33
 **/
