package ru.taravkov.serialaser.core.serializer;

import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.exception.SerializationException;


/**
 * @author vtaravkov
 * @since 1.0
 */
public interface SerializerProvider {
    /**
     * Returns serializer instance for the given data type.
     *
     * @param dataType data type to get serializer for
     *
     * @return serializer instance
     *
     * @throws SerializationException when serializer instance can not be provided
     */
    Serializer get(DataType dataType) throws SerializationException;

    /**
     * Returns serializer instance for the given object value.
     *
     * @param value object value to get serializer for
     *
     * @return serializer instance
     *
     * @throws SerializationException when serializer instance can not be provided
     */
    Serializer get(Object value) throws SerializationException;

    /**
     * Returns serializer instance for the given class.
     *
     * @param clazz class to get serializer for
     *
     * @return serializer instance
     *
     * @throws SerializationException when serializer instance can not be provided
     */
    Serializer get(Class clazz) throws SerializationException;
}
