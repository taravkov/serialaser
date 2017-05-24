package ru.taravkov.serialaser.core.schema;


import org.junit.Before;
import org.junit.Test;
import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.schema.impl.DefaultSchemaProvider;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class DefaultSchemaProviderTest {
    private SchemaProvider schemaProvider;

    @Before
    public void before() {
        schemaProvider = new DefaultSchemaProvider();
    }

    @Test
    public void test() throws NoSuchFieldException {
        Schema<TestClassB> schema = schemaProvider.getSchema(TestClassB.class);
        assertNotNull(schema);
        Collection<FieldMetaInfo> fields = schema.getFields();
        assertNotNull(fields);
        assertEquals(2, fields.size());
        FieldMetaInfo field1 = new FieldMetaInfo(DataType.STRING, TestClassA.class.getDeclaredField("field1"));
        FieldMetaInfo field2 = new FieldMetaInfo(DataType.INTEGER, TestClassB.class.getDeclaredField("field2"));
        assertEquals(field1, schema.getField("field1"));
        assertEquals(field2, schema.getField("field2"));
    }

    public static class TestClassA {
        private String field1;
    }

    public static class TestClassB extends TestClassA {
        private Integer field2;
    }
}
