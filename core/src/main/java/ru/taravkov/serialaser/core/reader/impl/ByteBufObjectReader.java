package ru.taravkov.serialaser.core.reader.impl;

import io.netty.buffer.ByteBufInputStream;
import ru.taravkov.serialaser.core.exception.DeserializationException;
import ru.taravkov.serialaser.core.exception.SerializationException;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class ByteBufObjectReader extends AbstractObjectReader<ByteBufInputStream> {
    @Override
    protected byte[] readBytes(int length, ByteBufInputStream input) {
        byte[] bytes = new byte[length];
        try {
            input.read(bytes);
        } catch (IOException e) {
            throw new SerializationException("Unable to read byte[] from InputStream");
        }
        return bytes;
    }

    @Override
    protected void mark(ByteBufInputStream input) {
        input.mark(Integer.MAX_VALUE);
    }

    @Override
    protected void reset(ByteBufInputStream input) {
        try {
            input.reset();
        } catch (IOException e) {
            throw new DeserializationException(e);
        }
    }
}
