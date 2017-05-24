package ru.taravkov.serialaser.core.schema;

import ru.taravkov.serialaser.core.exception.FieldAccessException;
import ru.taravkov.serialaser.core.schema.FieldMetaInfo;


/**
 * Implementations of this interface are to be used to access object instance's field values.
 * @author vtaravkov
 * @since 1.0
 */
public interface FieldAccessor {
    /**
     * Retrieve value for object field
     *
     * @param fieldMetaInfo meta information about the filed
     * @param object        object to access the field of
     *
     * @return value of the field
     *
     * @throws FieldAccessException when object field can not be accessed
     */
    Object getValue(FieldMetaInfo fieldMetaInfo, Object object) throws FieldAccessException;

    /**
     * Set the value of an object field.
     *
     * @param fieldMetaInfo meta information about the filed
     * @param object        object to set the value on
     * @param value         value to set
     *
     * @throws FieldAccessException when object field can not be accessed
     */
    void setValue(FieldMetaInfo fieldMetaInfo, Object object, Object value) throws FieldAccessException;
}
