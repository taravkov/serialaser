package ru.taravkov.serialaser.core.reader;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.taravkov.serialaser.core.datatype.DataType;
import ru.taravkov.serialaser.core.protocol.ProtocolMarker;
import ru.taravkov.serialaser.core.reader.impl.StreamObjectWriter;
import ru.taravkov.serialaser.core.serializer.SerializerProvider;
import ru.taravkov.serialaser.core.serializer.impl.DefaultSerializerProvider;
import ru.taravkov.serialization.test.TestClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class StreamObjectWriterTest {
    private ObjectWriter<OutputStream> objectWriter;

    private SerializerProvider serializerProvider;

    @Before
    public void before() {
        objectWriter = new StreamObjectWriter();
        serializerProvider = new DefaultSerializerProvider();
    }

    @Test
    public void test() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TestClass value = new TestClass();
        objectWriter.write(value, outputStream);
        byte[] expected = getSerializedForm(value);
        byte[] actual = outputStream.toByteArray();
        Assert.assertTrue(Arrays.equals(expected, actual));
    }

    @SuppressWarnings("unchecked")
    private byte[] getSerializedForm(TestClass value) throws IOException {
        byte byte1 = value.getByte1();
        short short1 = value.getShort1();
        char char1 = value.getChar1();
        int int1 = value.getInt1();
        long long1 = value.getLong1();
        float float1 = value.getFloat1();
        double double1 = value.getDouble1();
        boolean boolean1 = value.getBoolean1();

        byte byte2 = value.getByte2();
        short short2 = value.getShort2();
        char char2 = value.getChar2();
        int int2 = value.getInt2();
        long long2 = value.getLong2();
        float float2 = value.getFloat2();
        double double2 = value.getDouble2();
        boolean boolean2 = value.getBoolean2();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] className = TestClass.class.getCanonicalName().getBytes();

        outputStream.write(new byte[]{DataType.OBJECT.getCode()});
        outputStream.write(new byte[]{DataType.STRING.getCode()});
        outputStream.write(new byte[]{DataType.INTEGER.getCode()});
        outputStream.write(serializerProvider.get(DataType.INTEGER).serialize(className.length));
        outputStream.write(className);
        outputStream.write(new byte[]{ProtocolMarker.START_OBJECT.getCode()});

        outputStream.write(new byte[]{DataType.BOOLEAN.getCode()});
        outputStream.write(serializerProvider.get(boolean1).serialize(boolean1));
        outputStream.write(new byte[]{DataType.BOOLEAN.getCode()});
        outputStream.write(serializerProvider.get(boolean2).serialize(boolean2));

        outputStream.write(new byte[]{DataType.BYTE.getCode()});
        outputStream.write(serializerProvider.get(byte1).serialize(byte1));
        outputStream.write(new byte[]{DataType.BYTE.getCode()});
        outputStream.write(serializerProvider.get(byte2).serialize(byte2));

        outputStream.write(new byte[]{DataType.CHARACTER.getCode()});
        outputStream.write(serializerProvider.get(char1).serialize(char1));
        outputStream.write(new byte[]{DataType.CHARACTER.getCode()});
        outputStream.write(serializerProvider.get(char2).serialize(char2));

        outputStream.write(new byte[]{DataType.DOUBLE.getCode()});
        outputStream.write(serializerProvider.get(double1).serialize(double1));
        outputStream.write(new byte[]{DataType.DOUBLE.getCode()});
        outputStream.write(serializerProvider.get(double2).serialize(double2));

        outputStream.write(new byte[]{DataType.FLOAT.getCode()});
        outputStream.write(serializerProvider.get(float1).serialize(float1));
        outputStream.write(new byte[]{DataType.FLOAT.getCode()});
        outputStream.write(serializerProvider.get(float2).serialize(float2));

        outputStream.write(new byte[]{DataType.INTEGER.getCode()});
        outputStream.write(serializerProvider.get(int1).serialize(int1));
        outputStream.write(new byte[]{DataType.INTEGER.getCode()});
        outputStream.write(serializerProvider.get(int2).serialize(int2));

        outputStream.write(new byte[]{DataType.LONG.getCode()});
        outputStream.write(serializerProvider.get(long1).serialize(long1));
        outputStream.write(new byte[]{DataType.LONG.getCode()});
        outputStream.write(serializerProvider.get(long2).serialize(long2));

        outputStream.write(new byte[]{DataType.SHORT.getCode()});
        outputStream.write(serializerProvider.get(short1).serialize(short1));
        outputStream.write(new byte[]{DataType.SHORT.getCode()});
        outputStream.write(serializerProvider.get(short2).serialize(short2));

        outputStream.write(new byte[]{ProtocolMarker.END_OBJECT.getCode()});

        return outputStream.toByteArray();
    }
}
