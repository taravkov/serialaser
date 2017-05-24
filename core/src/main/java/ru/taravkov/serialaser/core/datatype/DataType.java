package ru.taravkov.serialaser.core.datatype;

import ru.taravkov.serialaser.core.protocol.CompactEnum;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Enumeration of data types supported by the Serialaser.
 *
 * @author vtaravkov
 * @since 1.0
 */
public enum DataType implements CompactEnum<DataType> {
    BYTE(Byte.class, Byte.BYTES, true, 0x00),

    SHORT(Short.class, Short.BYTES, true, 0x01),

    CHARACTER(Character.class, Character.BYTES, true, 0x02),

    INTEGER(Integer.class, Integer.BYTES, true, 0x03),

    LONG(Long.class, Long.BYTES, true, 0x04),

    FLOAT(Float.class, Float.BYTES, true, 0x05),

    DOUBLE(Double.class, Double.BYTES, true, 0x06),

    BOOLEAN(Boolean.class, Byte.BYTES, true, 0x07),

    STRING(String.class, null, false, 0x08),

    ENUM(Enum.class, Integer.BYTES, false, 0x09),

    COLLECTION(List.class, null, false, 0x0A),

    MAP(Map.class, null, false, 0x0B),

    OBJECT(Object.class, null, false, 0x0C);

    private final Class<?> javaType;

    private final Integer size;

    private final Boolean primitive;

    private final byte code;

    DataType(Class<?> javaType, Integer size, Boolean primitive, int code) {
        this.javaType = javaType;
        this.size = size;
        this.primitive = primitive;
        this.code = (byte) code;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public Integer getSize() {
        return size;
    }

    public Boolean isPrimitive() {
        return primitive;
    }

    @Override
    public byte getCode() {
        return code;
    }

    public static DataType getByCode(byte code) {
        return DATA_TYPE_MAP.get(code);
    }

    private final static Map<Byte, DataType> DATA_TYPE_MAP;

    static {
        DATA_TYPE_MAP = Stream.of(DataType.values())
                .collect(Collectors.toMap(DataType::getCode, Function.identity()));
    }
}
