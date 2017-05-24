package ru.taravkov.serialaser.core.serializer.impl;

import io.netty.buffer.ByteBuf;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class ShortSerializer implements Serializer<Short> {
    @Override
    public byte[] serialize(Short value) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        write(value, buffer);
        return buffer.array();
    }

    @Override
    public Short deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return read(buffer);
    }

    @Override
    public void write(Short value, ByteBuffer buffer) {
        buffer.putShort(value);
    }

    @Override
    public Short read(ByteBuffer buffer) {
        return buffer.getShort();
    }

    @Override
    public void write(Short value, ByteBuf buffer) {
        buffer.readShort();
    }

    @Override
    public Short read(ByteBuf buffer) {
        return buffer.readShort();
    }
}
