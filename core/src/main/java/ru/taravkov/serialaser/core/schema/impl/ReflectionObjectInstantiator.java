package ru.taravkov.serialaser.core.schema.impl;

import ru.taravkov.serialaser.core.exception.ObjectInstantiationException;
import ru.taravkov.serialaser.core.schema.ObjectInstantiator;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class ReflectionObjectInstantiator implements ObjectInstantiator {
    @Override
    public Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ObjectInstantiationException(e);
        }
    }
}
