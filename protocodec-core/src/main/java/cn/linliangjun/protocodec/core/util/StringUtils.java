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

/**
 * 字符串工具类
 *
 * @author linliangjun
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    /**
     * 首字母大写
     *
     * @param str 源字符串
     * @return 首字母大写后的字符串
     */
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase().concat(str.substring(1));
    }
}
