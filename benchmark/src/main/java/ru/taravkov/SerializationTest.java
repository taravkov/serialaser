package ru.taravkov;

import org.junit.Test;
import ru.taravkov.serialaser.core.reader.ObjectWriter;
import ru.taravkov.serialaser.core.reader.impl.StreamObjectWriter;
import ru.taravkov.serialization.test.TestClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


/**
 * @author vtaravkov
 */
public class SerializationTest {
    @Test
    public void testBuiltInSize() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(new TestClass());
        System.out.println(byteArrayOutputStream.toByteArray().length);
    }

    @Test
    public void testAlternativeSize() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectWriter<OutputStream> objectWriter = new StreamObjectWriter();
        objectWriter.write(new TestClass(), byteArrayOutputStream);
        System.out.println(byteArrayOutputStream.toByteArray().length);
    }
}
