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

package cn.linliangjun.protocodec.core;

import io.netty.buffer.ByteBuf;

/**
 * 字段编解码器
 *
 * @param <D> 字段描述符类型
 * @author linliangjun
 */
public sealed interface FieldCodec<D extends FieldDescriptor> permits AbstractFieldCodec {

    /**
     * 编码
     *
     * @param desc 字段描述符
     * @param obj  字段所在的对象
     * @param buf  （写入的）字节缓冲区
     */
    void code(D desc, Object obj, ByteBuf buf);

    /**
     * 解码
     *
     * @param desc 字段描述符
     * @param obj  字段所在的对象
     * @param buf  （读取的）字节缓冲区
     */
    void decode(D desc, Object obj, ByteBuf buf);
}
