package ru.taravkov.serialaser.core.reader.impl;

import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.exception.DeserializationException;
import ru.taravkov.serialaser.core.exception.SerializationException;
import ru.taravkov.serialaser.core.protocol.ProtocolMarker;
import ru.taravkov.serialaser.core.protocol.ProtocolReader;
import ru.taravkov.serialaser.core.reader.ObjectReader;
import ru.taravkov.serialaser.core.schema.*;
import ru.taravkov.serialaser.core.schema.impl.DefaultObjectInstantiatorProvider;
import ru.taravkov.serialaser.core.schema.impl.DefaultSchemaProvider;
import ru.taravkov.serialaser.core.schema.impl.ReflectionFieldAccessor;
import ru.taravkov.serialaser.core.serializer.SerializerProvider;
import ru.taravkov.serialaser.core.serializer.impl.DefaultSerializerProvider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class StreamObjectReader extends AbstractObjectReader<InputStream> {
    @Override
    protected byte[] readBytes(int length, InputStream input) {
        byte[] bytes = new byte[length];
        try {
            input.read(bytes);
        } catch (IOException e) {
            throw new SerializationException("Unable to read byte[] from InputStream");
        }
        return bytes;
    }

    @Override
    protected void mark(InputStream input) {
        input.mark(Integer.MAX_VALUE);
    }

    @Override
    protected void reset(InputStream input) {
        try {
            input.reset();
        } catch (IOException e) {
            throw new DeserializationException(e);
        }
    }
}
