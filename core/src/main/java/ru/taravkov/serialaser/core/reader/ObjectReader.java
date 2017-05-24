package ru.taravkov.serialaser.core.reader;

import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.exception.DeserializationException;
import ru.taravkov.serialaser.core.exception.SerializationException;

import java.util.Collection;
import java.util.Map;


/**
 * Main interface for reading objects using Serialaser. Allows reading of primitive values, strings, enums, collections, maps and objects.
 *
 * @param <I> type of the input
 *
 * @author vtaravkov
 * @since 1.0
 *
 * @see ObjectWriter
 * @see DataType
 */
public interface ObjectReader<I> {
    /**
     * Read primitive value from the given input.
     *
     * @param input input to read from
     *
     * @return read primitive value
     *
     * @throws DeserializationException in the cases when the primitive value can not be read
     */
    Object readPrimitive(I input) throws DeserializationException;

    /**
     * Read string value from the given input.
     *
     * @param input input to read from
     *
     * @return read string value
     *
     * @throws DeserializationException in the cases when the string value can not be read
     */
    String readString(I input) throws DeserializationException;

    /**
     * Read enum from the given input.
     *
     * @param input input to read from
     *
     * @return read enum
     *
     * @throws DeserializationException in the cases when the enum can not be read
     */
    Enum readEnum(I input) throws DeserializationException;

    /**
     * Read collection from the given input.
     *
     * @param input input to read from
     *
     * @return read collection
     *
     * @throws DeserializationException in the cases when the collection can not be read
     */
    Collection<?> readCollection(I input) throws DeserializationException;

    /**
     * Read map from the given input.
     *
     * @param input input to read from
     *
     * @return read map
     *
     * @throws DeserializationException in the cases when the map can not be read
     */
    Map<?, ?> readMap(I input) throws DeserializationException;

    /**
     * Read object from the given input.
     *
     * @param input input to read from
     *
     * @return read object
     *
     * @throws DeserializationException in the cases when the object can not be read
     */
    Object readObject(I input) throws DeserializationException;

    /**
     * Read value of unknown type from the given input.
     * <p>
     * This is a shorthand for the methods above that can be used when type of the data is not known in advance.
     *
     * @param input input to read from
     *
     * @return read value
     *
     * @throws DeserializationException in the cases when the value can not be read
     */
    Object read(I input) throws DeserializationException;
}
