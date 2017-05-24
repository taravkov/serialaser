package ru.taravkov.serialaser.core.datatype.impl;

import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.datatype.DataTypeProvider;

import java.util.*;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class DefaultDataTypeProvider implements DataTypeProvider {
    private final Map<Class, DataType> primitiveTypes;

    public DefaultDataTypeProvider() {
        Map<Class, DataType> primitiveTypes = new HashMap<>();

        primitiveTypes.put(byte.class, DataType.BYTE);
        primitiveTypes.put(short.class, DataType.SHORT);
        primitiveTypes.put(char.class, DataType.CHARACTER);
        primitiveTypes.put(int.class, DataType.INTEGER);
        primitiveTypes.put(long.class, DataType.LONG);
        primitiveTypes.put(float.class, DataType.FLOAT);
        primitiveTypes.put(double.class, DataType.DOUBLE);
        primitiveTypes.put(boolean.class, DataType.BOOLEAN);

        primitiveTypes.put(Byte.class, DataType.BYTE);
        primitiveTypes.put(Short.class, DataType.SHORT);
        primitiveTypes.put(Character.class, DataType.CHARACTER);
        primitiveTypes.put(Integer.class, DataType.INTEGER);
        primitiveTypes.put(Long.class, DataType.LONG);
        primitiveTypes.put(Float.class, DataType.FLOAT);
        primitiveTypes.put(Double.class, DataType.DOUBLE);
        primitiveTypes.put(Boolean.class, DataType.BOOLEAN);

        this.primitiveTypes = Collections.unmodifiableMap(primitiveTypes);
    }

    @Override
    public DataType get(Object value) {
        return get(value.getClass());
    }

    @Override
    public DataType get(Class clazz) {
        if (isPrimitive(clazz)) {
            return primitiveTypes.get(clazz);
        } else if (String.class.equals(clazz)) {
            return DataType.STRING;
        } else if (Enum.class.isAssignableFrom(clazz)) {
            return DataType.ENUM;
        } else if (Collection.class.isAssignableFrom(clazz)) {
            return DataType.COLLECTION;
        } else if (Map.class.isAssignableFrom(clazz)) {
            return DataType.MAP;
        } else {
            return DataType.OBJECT;
        }
    }

    private boolean isPrimitive(Class clazz) {
        return clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) ||
                Character.class.equals(clazz) || Boolean.class.equals(clazz);
    }
}
