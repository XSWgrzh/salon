package com.salon.common.core.utils;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.utils
 * @Project：salon
 * @name：ConvertUtil
 * @Date：2024/1/16 15:32
 */
@Slf4j
public class ConvertUtil extends BeanUtils {
    public static <T> T sourceToTarget(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        T targetObject = null;
        try {
            targetObject = instantiateClass(target);
            copyProperties(source, targetObject);
        }
        catch (Exception e) {
            log.error("convert {} to {} error :{}", source, target, e.getMessage());
        }
        return targetObject;
    }

    public static <T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> target) {
        if (CollectionUtil.isEmpty(sourceList)) {
            return Collections.emptyList();
        }
        return sourceList.stream().map(s -> sourceToTarget(s, target)).toList();
    }
}
