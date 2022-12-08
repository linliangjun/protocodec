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
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author linliangjun
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtils {

    /**
     * 获取字段访问器
     *
     * @param field 字段
     * @return 字段访问器
     * @throws NoSuchMethodException 没有这样的字段访问器
     */
    public static Method getAccessor(Field field) throws NoSuchMethodException {
        String name = field.getName();
        Class<?> clazz = field.getDeclaringClass();
        if (!clazz.isRecord()) {
            name = StringUtils.capitalize(name);
            Class<?> fieldType = field.getType();
            String prefix = (fieldType == boolean.class || fieldType == Boolean.class) ? "is" : "get";
            name = prefix + name;
        }
        return clazz.getMethod(name);
    }

    /**
     * 获取字段修改器
     *
     * @param field 字段
     * @return 字段修改器
     * @throws NoSuchMethodException 没有这样的字段修改器
     */
    public static Method getMutator(Field field) throws NoSuchMethodException {
        String name = field.getName();
        Class<?> clazz = field.getDeclaringClass();
        if (!clazz.isRecord()) {
            name = "set" + StringUtils.capitalize(name);
        }
        return clazz.getMethod(name, field.getType());
    }
}
