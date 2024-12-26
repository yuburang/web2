package cn.wzptsoft.common.utils;

import cn.wzptsoft.common.constant.RespCodeEnum;
import cn.wzptsoft.common.exception.BizException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {

    private static ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        JsonUtils.mapper = mapper;
    }

    public static <T> String objectToJson(T o) {
        try {
            return JsonUtils.mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(RespCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    public static <T> T jsonToObject(String s, TypeReference<T> tr) {
        if (s == null) {
            return null;
        }
        try {
            T o = JsonUtils.mapper.readValue(s, tr);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(RespCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

}

