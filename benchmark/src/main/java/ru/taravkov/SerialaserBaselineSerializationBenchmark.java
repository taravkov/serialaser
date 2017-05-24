package ru.taravkov;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;
import ru.taravkov.serialaser.core.reader.ObjectReader;
import ru.taravkov.serialaser.core.reader.ObjectWriter;
import ru.taravkov.serialaser.core.reader.impl.StreamObjectReader;
import ru.taravkov.serialaser.core.reader.impl.StreamObjectWriter;
import ru.taravkov.serialization.test.TestClass;

import java.io.*;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class SerialaserBaselineSerializationBenchmark extends AbstractSerializationBenchmark {
    private byte[] rawBytes;

    private ObjectWriter<OutputStream> objectWriter;

    private ObjectReader<InputStream> objectReader;

    @Setup(Level.Trial)
    public void setup() throws IOException {
        objectWriter = new StreamObjectWriter();
        objectReader = new StreamObjectReader();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        objectWriter.write(new TestClass(), byteArrayOutputStream);
        rawBytes = byteArrayOutputStream.toByteArray();
    }

    @Override
    @Benchmark
    public Object testSerialize(Blackhole blackhole) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        objectWriter.write(new TestClass(), byteArrayOutputStream);
        blackhole.consume(byteArrayOutputStream.toByteArray());
        return null;
    }

    @Override
    @Benchmark
    public Object testDeserialize(Blackhole blackhole) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rawBytes);
        TestClass value = (TestClass) objectReader.read(byteArrayInputStream);
        blackhole.consume(value);
        return null;
    }

    @Override
    @Benchmark
    public Object testRoundtrip(Blackhole blackhole) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        objectWriter.write(new TestClass(), byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        TestClass value = (TestClass) objectReader.read(byteArrayInputStream);
        blackhole.consume(value);
        return null;
    }
}
