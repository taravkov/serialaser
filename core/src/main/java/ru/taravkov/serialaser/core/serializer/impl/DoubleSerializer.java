package ru.taravkov.serialaser.core.serializer.impl;

import io.netty.buffer.ByteBuf;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class DoubleSerializer implements Serializer<Double> {
    @Override
    public byte[] serialize(Double value) {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
        write(value, buffer);
        return buffer.array();
    }

    @Override
    public Double deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return read(buffer);
    }

    @Override
    public void write(Double value, ByteBuffer buffer) {
        buffer.putDouble(value);
    }

    @Override
    public Double read(ByteBuffer buffer) {
        return buffer.getDouble();
    }

    @Override
    public void write(Double value, ByteBuf buffer) {
        buffer.writeDouble(value);
    }

    @Override
    public Double read(ByteBuf buffer) {
        return buffer.readDouble();
    }
}
