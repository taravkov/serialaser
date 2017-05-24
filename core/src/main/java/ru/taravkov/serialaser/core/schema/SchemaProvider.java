package ru.taravkov.serialaser.core.schema;


/**
 * @author vtaravkov
 * @since 1.0
 */
public interface SchemaProvider {
    /**
     * Return schema object for the given object value.
     *
     * @param object object value
     *
     * @return schema object
     */
    Schema<?> getSchema(Object object);

    /**
     * Return schema object for the given class.
     *
     * @param clazz class
     *
     * @return schema object
     */
    <T> Schema<T> getSchema(Class<T> clazz);
}
