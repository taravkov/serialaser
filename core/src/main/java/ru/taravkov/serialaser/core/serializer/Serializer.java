package ru.taravkov.serialaser.core.serializer;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;


/**
 * @author vtaravkov
 * @since 1.0
 */
public interface Serializer<T> {
    /**
     * Serializes the given value into byte sequence.
     *
     * @param value value to serialize
     *
     * @return serialized form of the value
     */
    byte[] serialize(T value);

    /**
     * Deserializes a sequence of bytes into a value.
     *
     * @param bytes sequence of bytes
     *
     * @return deserialized value
     */
    T deserialize(byte[] bytes);

    /**
     * Writes the given value to the output byte buffer.
     *
     * @param value  value to write
     * @param buffer buffer to write to
     */
    void write(T value, ByteBuffer buffer);

    /**
     * Reads the value from the given input byte buffer.
     *
     * @param buffer buffer to read from
     *
     * @return value read
     */
    T read(ByteBuffer buffer);

    /**
     * Writes the given value to the output byte buffer.
     *
     * @param value  value to write
     * @param buffer buffer to write to
     */
    void write(T value, ByteBuf buffer);

    /**
     * Reads the value from the given input byte buffer.
     *
     * @param buffer buffer to read from
     *
     * @return value read
     */
    T read(ByteBuf buffer);
}
