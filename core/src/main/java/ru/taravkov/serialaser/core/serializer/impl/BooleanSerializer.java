package ru.taravkov.serialaser.core.serializer.impl;

import io.netty.buffer.ByteBuf;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class BooleanSerializer implements Serializer<Boolean> {
    @Override
    public byte[] serialize(Boolean value) {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES);
        write(value, buffer);
        return buffer.array();
    }

    @Override
    public Boolean deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return read(buffer);
    }

    @Override
    public void write(Boolean value, ByteBuffer buffer) {
        buffer.put((byte) (value ? 0x01 : 0x00));
    }

    @Override
    public Boolean read(ByteBuffer buffer) {
        return buffer.get() == 0x01;
    }

    @Override
    public void write(Boolean value, ByteBuf buffer) {
        buffer.writeBoolean(value);
    }

    @Override
    public Boolean read(ByteBuf buffer) {
        return buffer.readBoolean();
    }
}
