package ru.taravkov.serialaser.core.serializer.impl;

import io.netty.buffer.ByteBuf;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class IntegerSerializer implements Serializer<Integer> {
    @Override
    public byte[] serialize(Integer value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        write(value, buffer);
        return buffer.array();
    }

    @Override
    public Integer deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return read(buffer);
    }

    @Override
    public void write(Integer value, ByteBuffer buffer) {
        buffer.putInt(value);
    }

    @Override
    public Integer read(ByteBuffer buffer) {
        return buffer.getInt();
    }

    @Override
    public void write(Integer value, ByteBuf buffer) {
        buffer.writeInt(value);
    }

    @Override
    public Integer read(ByteBuf buffer) {
        return buffer.readInt();
    }
}
