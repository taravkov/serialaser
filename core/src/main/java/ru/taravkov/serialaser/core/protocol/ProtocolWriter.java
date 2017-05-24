package ru.taravkov.serialaser.core.protocol;

import ru.taravkov.serialaser.core.annotation.PrivateApi;
import ru.taravkov.serialaser.core.datatype.DataType;


/**
 * The {@code ProtocolWriter} interface defines methods that are used when writing serialized data to protocol stream.
 *
 * @author vtaravkov
 * @since 1.0
 *
 * @param <O> type of the output
 *
 * @see ProtocolReader
 */
@PrivateApi
public interface ProtocolWriter<O> {
    /**
     * Write start object marker.
     *
     * @param output output
     */
    void writeStartObject(O output);

    /**
     * Write end object marker.
     *
     * @param output output
     */
    void writeEndObject(O output);

    /**
     * Write start collection marker.
     *
     * @param output output
     */
    void writeStartCollection(O output);

    /**
     * Write end collection marker.
     *
     * @param output output
     */
    void writeEndCollection(O output);

    /**
     * Write start map marker.
     *
     * @param output output
     */
    void writeStartMap(O output);

    /**
     * Write end map marker.
     *
     * @param output output
     */
    void writeEndMap(O output);

    /**
     * Write null pointer marker.
     *
     * @param output output
     */
    void writeNullPointer(O output);

    /**
     * Write Class information that is sufficient to recognise it on the receiver side.
     * <p>
     * Usually this can be class FQN string.
     *
     * @param clazz  class
     * @param output output
     */
    void writeClass(Class clazz, O output);

    /**
     * Write length prefix for variable length values.
     *
     * @param length variable length value length
     * @param output output
     */
    void writeLengthPrefix(int length, O output);

    /**
     * Write data type to identify the data type of the data that follows in the protocol stream.
     *
     * @param dataType data type
     * @param output   output
     */
    void writeDataType(DataType dataType, O output);

    /**
     * Write compact enum to output using {@link CompactEnum#getCode()}.
     *
     * @param value  compact enum value to write
     * @param output outpit
     */
    void writeCompactEnum(CompactEnum value, O output);
}
