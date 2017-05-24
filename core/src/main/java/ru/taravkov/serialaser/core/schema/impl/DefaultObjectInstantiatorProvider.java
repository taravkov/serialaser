package ru.taravkov.serialaser.core.schema.impl;

import ru.taravkov.serialaser.core.schema.ObjectInstantiator;
import ru.taravkov.serialaser.core.schema.ObjectInstantiatorProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class DefaultObjectInstantiatorProvider implements ObjectInstantiatorProvider {
    private Map<Class, ObjectInstantiator> objectInstantiators;

    private ObjectInstantiator objectInstantiator = new ReflectionObjectInstantiator();

    public DefaultObjectInstantiatorProvider() {
        Map<Class, ObjectInstantiator> objectInstantiators = new HashMap<>();
        this.objectInstantiators = Collections.unmodifiableMap(objectInstantiators);
    }

    @Override
    public ObjectInstantiator get(Class<?> clazz) {
        ObjectInstantiator objectInstantiator = objectInstantiators.get(clazz);
        return objectInstantiator != null ? objectInstantiator : this.objectInstantiator;
    }
}
