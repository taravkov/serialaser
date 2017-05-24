package ru.taravkov.serialaser.core.datatype;

/**
 * @author vtaravkov
 * @since 1.0
 */
public interface DataTypeProvider {
    /**
     * Returns data type of the given object.
     *
     * @param value object to get data type for
     *
     * @return data type of the object
     */
    DataType get(Object value);

    /**
     * Returns data type for the given class.
     *
     * @param clazz class to get data type for
     *
     * @return data type for the class
     */
    DataType get(Class clazz);
}
