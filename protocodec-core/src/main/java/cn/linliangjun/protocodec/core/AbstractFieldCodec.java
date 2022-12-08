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


import cn.linliangjun.protocodec.core.util.BeanUtils;
import io.netty.buffer.ByteBuf;

/**
 * 抽象字段编解码器
 *
 * @param <T> 字段类型
 * @param <D> 字段描述符类型
 * @author linliangjun
 */
public abstract non-sealed class AbstractFieldCodec<T, D extends FieldDescriptor> implements FieldCodec<D> {

    @Override
    public void code(D desc, Object obj, ByteBuf buf) {
        T value = BeanUtils.getFieldValue(obj, desc.getField());
        int oldIndex = buf.writerIndex();
        writeTo(desc, value, buf);
        int len = buf.writerIndex() - oldIndex;
        int count = desc.getLength() - len;
        if (count < 0) {
            throw new RuntimeException("写入的字节长度超出预期");
        }
        buf.writeZero(count);
    }

    protected abstract void writeTo(D desc, T value, ByteBuf buf);

    @Override
    public void decode(D desc, Object obj, ByteBuf buf) {
        int oldIndex = buf.readerIndex();
        T value = readFrom(desc, buf);
        int len = buf.readerIndex() - oldIndex;
        if (len != desc.getLength()) {
            throw new RuntimeException("读取的字节长度与预期不符");
        }
        BeanUtils.setFiledValue(obj, desc.getField(), value);
    }

    protected abstract T readFrom(D desc, ByteBuf buf);
}
