package ru.taravkov.serialaser.core.schema;

/**
 * @author vtaravkov
 * @since 1.0
 */
public interface ObjectInstantiatorProvider {
    /**
     * Returns object instantiator instance for the given class.
     *
     * @param clazz class to get object instantiator instance for
     *
     * @return object instantiator instance for
     */
    ObjectInstantiator get(Class<?> clazz);
}
