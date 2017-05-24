package ru.taravkov.serialaser.core.reader;

import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.exception.DeserializationException;
import ru.taravkov.serialaser.core.exception.SerializationException;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Main interface for writing objects using Serialaser. Allows writing of primitive values, strings, enums, collections, maps and objects.
 *
 * @param <O> type of the output
 *
 * @author vtaravkov
 * @since 1.0
 *
 * @see ObjectReader
 * @see DataType
 */
public interface ObjectWriter<O> {
    /**
     * Write primitive value to the given output.
     *
     * @param value  primitive value to write
     * @param output output to write to
     *
     * @throws SerializationException in the cases when the primitive value can not be written
     */
    void writePrimitive(DataType dataType, Object value, O output) throws SerializationException;

    /**
     * Write string value to the given output.
     *
     * @param value  string value to write
     * @param output output to write to
     *
     * @throws SerializationException in the cases when the string value can not be written
     */
    void writeString(String value, O output) throws SerializationException;

    /**
     * Write enum value to the given output.
     *
     * @param value  enum value to write
     * @param output output to write to
     *
     * @throws SerializationException in the cases when the enum value can not be written
     */
    void writeEnum(Enum value, O output) throws SerializationException;

    /**
     * Write collection to the given output.
     *
     * @param collection collection to write
     * @param output     output to write to
     *
     * @throws SerializationException in the cases when the collection can not be written
     */
    void writeCollection(Collection<?> collection, O output) throws SerializationException;

    /**
     * Write map to the given output.
     *
     * @param map    map to write
     * @param output output to write to
     *
     * @throws SerializationException in the cases when the map can not be written
     */
    void writeMap(Map<?, ?> map, O output) throws SerializationException;

    /**
     * Write object to the given output.
     *
     * @param object object to write
     * @param output output to write to
     *
     * @throws SerializationException in the cases when the object can not be written
     */
    void writeObject(Object object, O output) throws SerializationException;

    /**
     * Write value of unknown type to the given output.
     * <p>
     * This is a shorthand for the methods above that can be used when type of the data is not known in advance.
     *
     * @param object object to write
     * @param output output to write to
     *
     * @throws SerializationException in the cases when the value can not be written
     */
    void write(Object object, O output) throws SerializationException;
}
