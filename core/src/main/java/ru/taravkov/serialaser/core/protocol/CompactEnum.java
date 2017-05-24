package ru.taravkov.serialaser.core.protocol;

import ru.taravkov.serialaser.core.annotation.PrivateApi;


/**
 * Compact enums are used to write compact enum representation using one-byte {@code code} instead of four-byte integer ordinal.
 *
 * @author vtaravkov
 * @since 1.0
 */
@PrivateApi
public interface CompactEnum<E extends Enum> {
    /**
     * Returns unique one-byte code for this enum value.
     *
     * @return unique one-byte code for this enum value
     */
    byte getCode();
}
