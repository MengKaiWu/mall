package com.macro.mall.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @author wumk
 * @date 2019/11/20
 */
@Slf4j
public class CopyPropertyUtil {

    /**
     * 对象属性拷贝
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target){
        try {
            BeanUtils.copyProperties(source,target);
        } catch (BeansException e) {
            log.error("BeanUtil property copy failed,BeansException", e);
        } catch (Exception e) {
            log.error("BeanUtil property copy failed,Exception", e);
        }
    }

    /**
     * 集合对象属性赋值
     * @param input 输入集合
     * @param clzz 输出集合类型
     * @param <E> 输入集合类型
     * @param <T> 输出集合类型
     * @return
     */
    public static <E,T> List<T> converListToList(List<E> input,Class<T> clzz){
        List<T> output = Lists.newArrayList();
        if(CollectionUtils.isEmpty(input)){
            for (E source : input) {
                T target = BeanUtils.instantiateClass(clzz);
                BeanUtils.copyProperties(source,target);
                output.add(target);
            }
        }
        return output;
    }
}
