package ru.taravkov.serialaser.core.schema;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class Schema<T> {
    private final Map<String, FieldMetaInfo> fields;

    public Schema(List<FieldMetaInfo> fields) {
        this.fields = Collections.unmodifiableMap(new TreeMap<>(fields.stream()
                .collect(Collectors.toMap(FieldMetaInfo::getName, Function.identity()))));
    }

    public Collection<FieldMetaInfo> getFields() {
        return fields.values();
    }

    public FieldMetaInfo getField(String name) {
        return fields.get(name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Schema<?> schema = (Schema<?>) object;
        return Objects.equals(fields, schema.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields);
    }

    @Override
    public String toString() {
        return "Schema{" +
                "fields=" + fields +
                '}';
    }
}
