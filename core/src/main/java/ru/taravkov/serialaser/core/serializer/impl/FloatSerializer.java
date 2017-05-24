package ru.taravkov.serialaser.core.serializer.impl;

import io.netty.buffer.ByteBuf;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class FloatSerializer implements Serializer<Float> {
    @Override
    public byte[] serialize(Float value) {
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        write(value, buffer);
        return buffer.array();
    }

    @Override
    public Float deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return read(buffer);
    }

    @Override
    public void write(Float value, ByteBuffer buffer) {
        buffer.putFloat(value);
    }

    @Override
    public Float read(ByteBuffer buffer) {
        return buffer.getFloat();
    }

    @Override
    public void write(Float value, ByteBuf buffer) {
        buffer.writeFloat(value);
    }

    @Override
    public Float read(ByteBuf buffer) {
        return buffer.readFloat();
    }
}
