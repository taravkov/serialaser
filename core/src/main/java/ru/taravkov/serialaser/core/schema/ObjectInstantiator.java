package ru.taravkov.serialaser.core.schema;

import ru.taravkov.serialaser.core.exception.ObjectInstantiationException;


/**
 * The {@code ObjectInstantiator} interface defines methods to create instances of arbitrary classes.
 *
 * @author vtaravkov
 * @since 1.0
 */
public interface ObjectInstantiator {
    /**
     * Creates new instance of the specified class.
     *
     * @param clazz class to create instance of
     *
     * @return new instance of the specified class
     *
     * @throws ObjectInstantiationException when instance can not be created
     */
    Object newInstance(Class<?> clazz) throws ObjectInstantiationException;
}
