package ru.taravkov.serialaser.core.serializer.impl;

import io.netty.buffer.ByteBuf;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class LongSerializer implements Serializer<Long> {
    @Override
    public byte[] serialize(Long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        write(value, buffer);
        return buffer.array();
    }

    @Override
    public Long deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return read(buffer);
    }

    @Override
    public void write(Long value, ByteBuffer buffer) {
        buffer.putLong(value);
    }

    @Override
    public Long read(ByteBuffer buffer) {
        return buffer.getLong();
    }

    @Override
    public void write(Long value, ByteBuf buffer) {
        buffer.writeLong(value);
    }

    @Override
    public Long read(ByteBuf buffer) {
        return buffer.readLong();
    }
}
