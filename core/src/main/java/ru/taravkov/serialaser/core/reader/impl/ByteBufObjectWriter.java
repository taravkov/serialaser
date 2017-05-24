package ru.taravkov.serialaser.core.reader.impl;

import io.netty.buffer.ByteBufOutputStream;
import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.exception.SerializationException;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class ByteBufObjectWriter extends AbstractObjectWriter<ByteBufOutputStream> {
    @Override
    @SuppressWarnings("unchecked")
    protected void writeValue(DataType dataType, Object value, ByteBufOutputStream output) {
        Serializer serializer = serializerProvider.get(dataType);
        serializer.write(value, output.buffer());
    }

    @Override
    protected void writeBytes(byte[] bytes, ByteBufOutputStream output) {
        try {
            output.write(bytes);
        } catch (IOException e) {
            throw new SerializationException("Unable to write byte[] to OutputStream", e);
        }
    }
}
