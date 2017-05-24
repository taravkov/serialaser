package ru.taravkov.serialaser.core.schema.impl;

import ru.taravkov.serialaser.core.exception.FieldAccessException;
import ru.taravkov.serialaser.core.exception.SerializationException;
import ru.taravkov.serialaser.core.schema.FieldAccessor;
import ru.taravkov.serialaser.core.schema.FieldMetaInfo;

import java.lang.reflect.Field;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class ReflectionFieldAccessor implements FieldAccessor {
    @Override
    public Object getValue(FieldMetaInfo fieldMetaInfo, Object object) {
        Field field = fieldMetaInfo.getField();
        field.setAccessible(true);
        Class<?> type = field.getType();
        try {
            if (type.isPrimitive()) {
                if (byte.class.equals(type)) {
                    return field.getByte(object);
                } else if (short.class.equals(type)) {
                    return field.getShort(object);
                } else if (char.class.equals(type)) {
                    return field.getChar(object);
                } else if (int.class.equals(type)) {
                    return field.getInt(object);
                } else if (long.class.equals(type)) {
                    return field.getLong(object);
                } else if (float.class.equals(type)) {
                    return field.getFloat(object);
                } else if (double.class.equals(type)) {
                    return field.getDouble(object);
                } else if (boolean.class.equals(type)) {
                    return field.getBoolean(object);
                }
            } else {
                return field.get(object);
            }
        } catch (IllegalAccessException e) {
            throw new FieldAccessException(e);
        }
        throw new FieldAccessException("Can not access field");
    }

    @Override
    public void setValue(FieldMetaInfo fieldMetaInfo, Object object, Object value) {
        Field field = fieldMetaInfo.getField();
        field.setAccessible(true);
        Class<?> type = field.getType();
        try {
            if (type.isPrimitive()) {
                if (byte.class.equals(type)) {
                    field.setByte(object, (Byte) value);
                } else if (short.class.equals(type)) {
                    field.setShort(object, (Short) value);
                } else if (char.class.equals(type)) {
                    field.setChar(object, (Character) value);
                } else if (int.class.equals(type)) {
                    field.setInt(object, (Integer) value);
                } else if (long.class.equals(type)) {
                    field.setLong(object, (Long) value);
                } else if (float.class.equals(type)) {
                    field.setFloat(object, (Float) value);
                } else if (double.class.equals(type)) {
                    field.setDouble(object, (Double) value);
                } else if (boolean.class.equals(type)) {
                    field.setBoolean(object, (Boolean) value);
                }
            } else {
                field.set(object, value);
            }
        } catch (IllegalAccessException e) {
            throw new FieldAccessException(e);
        }
    }
}
