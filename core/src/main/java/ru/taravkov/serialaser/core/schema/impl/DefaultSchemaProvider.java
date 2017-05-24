package ru.taravkov.serialaser.core.schema.impl;

import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.datatype.impl.DefaultDataTypeProvider;
import ru.taravkov.serialaser.core.datatype.DataTypeProvider;
import ru.taravkov.serialaser.core.schema.FieldMetaInfo;
import ru.taravkov.serialaser.core.schema.Schema;
import ru.taravkov.serialaser.core.schema.SchemaProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class DefaultSchemaProvider implements SchemaProvider {
    private DataTypeProvider dataTypeProvider = new DefaultDataTypeProvider();

    private Map<Class<?>, Schema> classSchemas = new HashMap<>();

    @Override
    public Schema<?> getSchema(Object object) {
        return getSchema(object.getClass());
    }

    @Override
    public <T> Schema<T> getSchema(Class<T> clazz) {
        if (classSchemas.containsKey(clazz)) {
            return classSchemas.get(clazz);
        }
        Class next = clazz;
        List<FieldMetaInfo> fields = new ArrayList<>();
        while (next != null) {
            Field[] declaredFields = next.getDeclaredFields();
            for (Field field : declaredFields) {
                Class<?> type = field.getType();
                DataType dataType = dataTypeProvider.get(type);
                fields.add(new FieldMetaInfo(dataType, field));
            }
            next = next.getSuperclass();
        }
        Schema<T> schema = new Schema<>(fields);
        classSchemas.put(clazz, schema);
        return schema;
    }
}
