package ru.taravkov.serialaser.core.serializer.impl;

import io.netty.buffer.ByteBuf;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class ByteSerializer implements Serializer<Byte> {
    @Override
    public byte[] serialize(Byte value) {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES);
        write(value, buffer);
        return buffer.array();
    }

    @Override
    public Byte deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return read(buffer);
    }

    @Override
    public void write(Byte value, ByteBuffer buffer) {
        buffer.put(value);
    }

    @Override
    public Byte read(ByteBuffer buffer) {
        return buffer.get();
    }

    @Override
    public void write(Byte value, ByteBuf buffer) {
        buffer.writeByte(value);
    }

    @Override
    public Byte read(ByteBuf buffer) {
        return buffer.readByte();
    }
}
