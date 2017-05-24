package ru.taravkov.serialaser.core.schema;

import ru.taravkov.serialaser.core.datatype.DataType;

import java.lang.reflect.Field;
import java.util.Objects;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class FieldMetaInfo {
    private final String name;

    private final DataType dataType;

    private final Field field;

    public FieldMetaInfo(DataType dataType, Field field) {
        this.name = field.getName();
        this.dataType = dataType;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Field getField() {
        return field;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        FieldMetaInfo that = (FieldMetaInfo) object;
        return Objects.equals(name, that.name) &&
                dataType == that.dataType &&
                Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dataType, field);
    }

    @Override
    public String toString() {
        return "FieldMetaInfo{" +
                "name='" + name + '\'' +
                ", dataType=" + dataType +
                ", field=" + field +
                '}';
    }
}
