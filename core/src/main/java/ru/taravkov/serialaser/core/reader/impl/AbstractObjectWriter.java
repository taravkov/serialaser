package ru.taravkov.serialaser.core.reader.impl;

import ru.taravkov.serialaser.core.protocol.CompactEnum;
import ru.taravkov.serialaser.core.protocol.ProtocolWriter;
import ru.taravkov.serialaser.core.schema.FieldAccessor;
import ru.taravkov.serialaser.core.schema.impl.ReflectionFieldAccessor;
import ru.taravkov.serialaser.core.exception.SerializationException;
import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.datatype.DataTypeProvider;
import ru.taravkov.serialaser.core.datatype.impl.DefaultDataTypeProvider;
import ru.taravkov.serialaser.core.protocol.ProtocolMarker;
import ru.taravkov.serialaser.core.reader.ObjectWriter;
import ru.taravkov.serialaser.core.schema.impl.DefaultSchemaProvider;
import ru.taravkov.serialaser.core.schema.Schema;
import ru.taravkov.serialaser.core.schema.SchemaProvider;
import ru.taravkov.serialaser.core.serializer.Serializer;
import ru.taravkov.serialaser.core.serializer.SerializerProvider;
import ru.taravkov.serialaser.core.serializer.impl.DefaultSerializerProvider;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @author vtaravkov
 * @since 1.0
 */
public abstract class AbstractObjectWriter<O> implements ObjectWriter<O>, ProtocolWriter<O> {
    protected final SchemaProvider schemaProvider = new DefaultSchemaProvider();

    protected final FieldAccessor fieldAccessor = new ReflectionFieldAccessor();

    protected final SerializerProvider serializerProvider = new DefaultSerializerProvider();

    protected final DataTypeProvider dataTypeProvider = new DefaultDataTypeProvider();

    @Override
    public void write(Object object, O output) throws SerializationException {
        if (object == null) {
            writeNullPointer(output);
            return;
        }
        DataType dataType = dataTypeProvider.get(object);
        if (dataType.isPrimitive()) {
            writePrimitive(dataType, object, output);
        } else if (DataType.STRING.equals(dataType)) {
            writeString((String) object, output);
        } else if (DataType.ENUM.equals(dataType)) {
            writeEnum((Enum) object, output);
        } else if (DataType.COLLECTION.equals(dataType)) {
            writeCollection((List) object, output);
        } else if (DataType.MAP.equals(dataType)) {
            writeMap((Map) object, output);
        } else if (DataType.OBJECT.equals(dataType)) {
            writeObject(object, output);
        }
    }

    @Override
    public void writePrimitive(DataType dataType, Object value, O output) {
        writeDataType(dataType, output);
        writeValue(dataType, value, output);
    }

    @Override
    public void writeString(String value, O output) {
        DataType dataType = DataType.STRING;
        writeDataType(dataType, output);
        byte[] bytes = value.getBytes();
        writeLengthPrefix(bytes.length, output);
        writeBytes(bytes, output);
    }

    @Override
    public void writeEnum(Enum value, O output) {
        writeDataType(DataType.ENUM, output);
        writeClass(value.getClass(), output);
        writeValue(DataType.INTEGER, value.ordinal(), output);
    }

    @Override
    public void writeCollection(Collection<?> collection, O output) {
        writeDataType(DataType.COLLECTION, output);
        writeStartCollection(output);
        collection.forEach(elem -> write(elem, output));
        writeEndCollection(output);
    }

    @Override
    public void writeMap(Map<?, ?> map, O output) {
    }

    @Override
    public void writeObject(Object object, O output) {
        Class<?> clazz = object.getClass();
        Schema<?> schema = schemaProvider.getSchema(clazz);
        writeDataType(DataType.OBJECT, output);
        writeClass(clazz, output);
        writeStartObject(output);
        schema.getFields().forEach(field -> {
            Object value = fieldAccessor.getValue(field, object);
            write(value, output);
        });
        writeEndObject(output);
    }

    @Override
    public void writeStartObject(O output) {
        writeCompactEnum(ProtocolMarker.START_OBJECT, output);
    }

    @Override
    public void writeEndObject(O output) {
        writeCompactEnum(ProtocolMarker.END_OBJECT, output);
    }

    @Override
    public void writeStartCollection(O output) {
        writeCompactEnum(ProtocolMarker.START_COLLECTION, output);
    }

    @Override
    public void writeEndCollection(O output) {
        writeCompactEnum(ProtocolMarker.END_COLLECTION, output);
    }

    @Override
    public void writeStartMap(O output) {
        writeCompactEnum(ProtocolMarker.START_MAP, output);
    }

    @Override
    public void writeEndMap(O output) {
        writeCompactEnum(ProtocolMarker.END_MAP, output);
    }

    @Override
    public void writeNullPointer(O output) {
        writeCompactEnum(ProtocolMarker.NULL_POINTER, output);
    }

    @Override
    public void writeClass(Class clazz, O output) {
        writeString(clazz.getCanonicalName(), output);
    }

    @Override
    public void writeLengthPrefix(int length, O output) {
        writePrimitive(DataType.INTEGER, length, output);
    }

    @Override
    public void writeDataType(DataType dataType, O output) {
        writeCompactEnum(dataType, output);
    }

    @Override
    public void writeCompactEnum(CompactEnum value, O output) {
        writeValue(DataType.BYTE, value.getCode(), output);
    }

    protected abstract void writeValue(DataType dataType, Object value, O output);

    protected abstract void writeBytes(byte[] bytes, O output);
}
