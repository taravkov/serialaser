package ru.taravkov.serialaser.core.serializer.impl;

import io.netty.buffer.ByteBuf;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class CharacterSerializer implements Serializer<Character> {
    @Override
    public byte[] serialize(Character value) {
        ByteBuffer buffer = ByteBuffer.allocate(Character.BYTES);
        write(value, buffer);
        return buffer.array();
    }

    @Override
    public Character deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Character.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return read(buffer);
    }

    @Override
    public void write(Character value, ByteBuffer buffer) {
        buffer.putChar(value);
    }

    @Override
    public Character read(ByteBuffer buffer) {
        return buffer.getChar();
    }

    @Override
    public void write(Character value, ByteBuf buffer) {
        buffer.writeChar(value);
    }

    @Override
    public Character read(ByteBuf buffer) {
        return buffer.readChar();
    }
}
