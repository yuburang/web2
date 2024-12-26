package cn.wzptsoft.tinymallbe.security;

import cn.wzptsoft.tinymallbe.entity.User;

import java.util.HashMap;
import java.util.Map;

//当前线程的上下文
public class ThreadContext {
    private static ThreadLocal<Map<String,Object>> threadLocal = new
            ThreadLocal<>();
    public static void setUser(User user){
        Map<String,Object> map = threadLocal.get();
        if(map == null){
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put("user",user);
    }
    public static void clear(){
        ThreadContext.threadLocal.remove();
    }
    public static User getUser(){
        Map<String,Object> map = threadLocal.get();
        User user = (User) map.get("user");
        return user;
    }
}
