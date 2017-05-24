package ru.taravkov.serialaser.core.reader.impl;

import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.exception.SerializationException;
import ru.taravkov.serialaser.core.serializer.Serializer;

import java.io.IOException;
import java.io.OutputStream;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class StreamObjectWriter extends AbstractObjectWriter<OutputStream> {
    @Override
    protected void writeValue(DataType dataType, Object value, OutputStream output) {
        byte[] bytes = getBytes(value, dataType);
        writeBytes(bytes, output);
    }

    @Override
    protected void writeBytes(byte[] bytes, OutputStream output) {
        try {
            output.write(bytes);
        } catch (IOException e) {
            throw new SerializationException("Unable to write byte[] to OutputStream", e);
        }
    }

    private byte[] getBytes(Object value, DataType dataType) {
        Serializer serializer = serializerProvider.get(dataType);
        @SuppressWarnings("unchecked")
        byte[] bytes = serializer.serialize(value);
        return bytes;
    }
}
