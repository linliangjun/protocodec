/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.linliangjun.protocodec.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Bean 工具类
 *
 * @author linliangjun
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtils {

    /**
     * 设置字段值
     *
     * @param bean  字段所在对象
     * @param field 字段
     * @param value 字段值
     */
    public static void setFiledValue(Object bean, Field field, Object value) {
        try {
            Method mutator = ReflectUtils.getMutator(field);
            mutator.invoke(bean, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("设置字段值时发生了异常", e);
        }
    }

    /**
     * 获取字段值
     *
     * @param bean  字段所在对象
     * @param field 字段
     * @param <T>   字段值类型
     * @return 字段值
     */
    public static <T> T getFieldValue(Object bean, Field field) {
        try {
            Method accessor = ReflectUtils.getAccessor(field);
            @SuppressWarnings("unchecked")
            T value = (T) accessor.invoke(bean);
            return value;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("获取字段值时发生了异常", e);
        }
    }
}
