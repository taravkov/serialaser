package ru.taravkov.serialaser.core.reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.taravkov.serialaser.core.reader.impl.StreamObjectReader;
import ru.taravkov.serialaser.core.reader.impl.StreamObjectWriter;
import ru.taravkov.serialization.test.TestClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class ProtocolTest {
    private ObjectWriter<OutputStream> objectWriter;

    private ObjectReader<InputStream> objectReader;

    @Before
    public void before() {
        objectWriter = new StreamObjectWriter();
        objectReader = new StreamObjectReader();
    }

    @Test
    public void test() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TestClass expected = new TestClass();
        objectWriter.write(expected, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        TestClass actual = (TestClass) objectReader.read(inputStream);
        Assert.assertEquals("equal values", expected, actual);
    }
}
