package ru.taravkov.serialaser.core.protocol;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Protocol markers are used to identify different types of data that appear in protocol stream.
 *
 * @author vtaravkov
 * @since 1.0
 *
 * @see ProtocolReader
 * @see ProtocolWriter
 */
public enum ProtocolMarker implements CompactEnum<ProtocolMarker> {
    START_OBJECT(0x00),

    END_OBJECT(0x01),

    START_COLLECTION(0x02),

    END_COLLECTION(0x03),

    START_MAP(0x04),

    END_MAP(0x05),

    NULL_POINTER(0x06);

    private final byte code;

    ProtocolMarker(int code) {
        this.code = (byte) code;
    }

    @Override
    public byte getCode() {
        return code;
    }

    public static ProtocolMarker getByCode(byte code) {
        return PROTOCOL_TOKEN_MAP.get(code);
    }

    private final static Map<Byte, ProtocolMarker> PROTOCOL_TOKEN_MAP;

    static {
        PROTOCOL_TOKEN_MAP = Stream.of(ProtocolMarker.values())
                .collect(Collectors.toMap(ProtocolMarker::getCode, Function.identity()));
    }
}
