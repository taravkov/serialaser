package ru.taravkov.serialaser.core.protocol;

import ru.taravkov.serialaser.core.datatype.DataType;


/**
 * The {@code ProtocolReader} interface defines methods that can be used when reading serialized data from protocol stream.
 *
 * @param <I> type of the input
 *
 * @author vtaravkov
 * @since 1.0
 *
 * @see ProtocolWriter
 */
public interface ProtocolReader<I> {
    /**
     * Read length prefix for a variable length value that follows in the protocol stream.
     *
     * @param input input
     *
     * @return length of the variable length value
     */
    int readLengthPrefix(I input);

    /**
     * Read the information about the {@code Class} of the object that follows in the protocol stream.
     * <p>
     * Usually this could be FQN string.
     *
     * @param input input
     *
     * @return class of the object that follows in the protocol stream
     */
    Class readClass(I input);

    <E extends Enum> E readCompactEnum(Class<E> clazz, I input);

    /**
     * Read the data type of the data that follows in the protocol stream.
     *
     * @param input input
     *
     * @return data type of the data that follows in the protocol stream
     */
    DataType readDataType(I input);

    /**
     * Read protocol marker that follows from the protocol stream.
     *
     * @param input input
     *
     * @return protocol marker
     */
    ProtocolMarker readProtocolMarker(I input);
}
