package cn.wzptsoft.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 转换工具类
 */
public class BeanConverter {
    private static Logger logger = LoggerFactory.getLogger(BeanConverter.class);

    public static <T> T convert(Object source, Class<T> target){
        if(source == null){
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.getConstructor().newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            logger.error("convert error ", e);
        }

        return targetObject;
    }

    public static <T> List<T> convert(Collection<?> sourceList, Class<T> target){
        if(sourceList == null){
            return null;
        }

        List targetList = new ArrayList<>(sourceList.size());
        try {
            for(Object source : sourceList){
                T targetObject = target.getConstructor().newInstance();
                org.springframework.beans.BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        }catch (Exception e){
            logger.error("convert error ", e);
        }

        return targetList;
    }
}