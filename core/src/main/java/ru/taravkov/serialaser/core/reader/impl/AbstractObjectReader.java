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

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;


/**
 * @author vtaravkov
 * @since 1.0
 */
public abstract class AbstractObjectReader<I> implements ProtocolReader<I>, ObjectReader<I> {
    protected final SerializerProvider serializerProvider = new DefaultSerializerProvider();

    protected final SchemaProvider schemaProvider = new DefaultSchemaProvider();

    protected final FieldAccessor fieldAccessor = new ReflectionFieldAccessor();

    protected final ObjectInstantiatorProvider objectInstantiatorProvider = new DefaultObjectInstantiatorProvider();

    @Override
    public Object read(I input) throws DeserializationException {
        try {
            mark(input);
            DataType dataType = readCompactEnum(DataType.class, input);
            reset(input);
            if (dataType.isPrimitive()) {
                return readPrimitive(input);
            } else if (DataType.STRING.equals(dataType)) {
                return readString(input);
            } else if (DataType.ENUM.equals(dataType)) {
                return readEnum(input);
            } else if (DataType.COLLECTION.equals(dataType)) {
                return readCollection(input);
            } else if (DataType.MAP.equals(dataType)) {
                return readMap(input);
            } else {
                return readObject(input);
            }
        } catch (Exception e) {
            throw new DeserializationException(e);
        }
    }

    @Override
    public Object readPrimitive(I input) throws DeserializationException {
        DataType dataType = readCompactEnum(DataType.class, input);
        if (!dataType.isPrimitive()) {
            throw new DeserializationException("Unable to read primitive, incorrect data type read");
        }
        byte[] bytes = readBytes(dataType.getSize(), input);
        return serializerProvider.get(dataType).deserialize(bytes);
    }

    @Override
    public String readString(I input) {
        DataType dataType = readCompactEnum(DataType.class, input);
        if (!DataType.STRING.equals(dataType)) {
            throw new DeserializationException("Unable to read string, incorrect data type read");
        }
        int length = readLengthPrefix(input);
        byte[] bytes = readBytes(length, input);
        return new String(bytes);
    }

    @Override
    public Enum readEnum(I input) {
        return null;
    }

    @Override
    public Collection<?> readCollection(I input) {
        return null;
    }

    @Override
    public Map<?, ?> readMap(I input) {
        return null;
    }

    @Override
    public Object readObject(I input) {
        try {
            DataType dataType = readCompactEnum(DataType.class, input);
            if (!DataType.OBJECT.equals(dataType)) {
                throw new DeserializationException("Unable to read object, incorrect data type read");
            }
            Class<?> clazz = readClass(input);
            Object object = objectInstantiatorProvider.get(clazz).newInstance(clazz);
            Schema<?> schema = schemaProvider.getSchema(clazz);
            ProtocolMarker startObject = readProtocolMarker(input);
            if (!ProtocolMarker.START_OBJECT.equals(startObject)) {
                throw new DeserializationException(String.format("Expected START_OBJECT marker, but got: %s", startObject));
            }
            for (FieldMetaInfo field : schema.getFields()) {
                Object value = read(input);
                fieldAccessor.setValue(field, object, value);
            }
            ProtocolMarker endObject = readProtocolMarker(input);
            if (!ProtocolMarker.END_OBJECT.equals(endObject)) {
                throw new DeserializationException(String.format("Expected END_OBJECT marker, but got: %s", endObject));
            }
            return object;
        } catch (Exception e) {
            throw new DeserializationException(e);
        }
    }

    @Override
    public int readLengthPrefix(I input) {
        return (Integer) readPrimitive(input);
    }

    @Override
    public Class readClass(I input) throws DeserializationException {
        String className = readString(input);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new DeserializationException(e);
        }
    }

    @Override
    public DataType readDataType(I input) {
        return readCompactEnum(DataType.class, input);
    }

    @Override
    public ProtocolMarker readProtocolMarker(I input) {
        return readCompactEnum(ProtocolMarker.class, input);
    }

    @Override
    public <E extends Enum> E readCompactEnum(Class<E> clazz, I input) {
        byte[] bytes = readBytes(1, input);
        Method method;
        try {
            method = clazz.getMethod("getByCode", byte.class);
        } catch (NoSuchMethodException e) {
            throw new SerializationException("Unable to read compact enum, static method getByCode(byte) not found", e);
        }
        try {
            @SuppressWarnings("unchecked")
            E value = (E) method.invoke(null, bytes[0]);
            return value;
        } catch (IllegalAccessException | InvocationTargetException e) {
           throw new SerializationException(e);
        }
    }

    private Object readValue(DataType dataType, I input) {
        byte[] bytes;
        bytes = readBytes(dataType.getSize(), input);
        return serializerProvider.get(dataType).deserialize(bytes);
    }

    protected abstract byte[] readBytes(int length, I input);

    protected abstract void mark(I input);

    protected abstract void reset(I input);
}
