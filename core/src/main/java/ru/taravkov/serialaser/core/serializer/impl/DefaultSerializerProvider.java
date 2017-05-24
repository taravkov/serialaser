package ru.taravkov.serialaser.core.serializer.impl;

import ru.taravkov.serialaser.core.exception.SerializationException;
import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.datatype.impl.DefaultDataTypeProvider;
import ru.taravkov.serialaser.core.datatype.DataTypeProvider;
import ru.taravkov.serialaser.core.serializer.Serializer;
import ru.taravkov.serialaser.core.serializer.SerializerProvider;

import java.util.HashMap;
import java.util.Map;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class DefaultSerializerProvider implements SerializerProvider {
    private final Map<DataType, Serializer> serializers = new HashMap<>();

    private final DataTypeProvider dataTypeProvider = new DefaultDataTypeProvider();

    public DefaultSerializerProvider() {
        serializers.put(DataType.BYTE, new ByteSerializer());
        serializers.put(DataType.SHORT, new ShortSerializer());
        serializers.put(DataType.CHARACTER, new CharacterSerializer());
        serializers.put(DataType.INTEGER, new IntegerSerializer());
        serializers.put(DataType.LONG, new LongSerializer());
        serializers.put(DataType.FLOAT, new FloatSerializer());
        serializers.put(DataType.DOUBLE, new DoubleSerializer());
        serializers.put(DataType.BOOLEAN, new BooleanSerializer());
    }

    @Override
    public Serializer get(DataType dataType) throws SerializationException {
        if (!dataType.isPrimitive()) {
            throw new SerializationException("There are no serializers for complex data types");
        }
        Serializer serializer = serializers.get(dataType);
        if (serializer == null) {
            throw new SerializationException("Unsupported data type");
        }
        return serializer;
    }

    @Override
    public Serializer get(Object value) throws SerializationException {
        return get(dataTypeProvider.get(value));
    }

    @Override
    public Serializer get(Class clazz) throws SerializationException {
        return get(dataTypeProvider.get(clazz));
    }
}
