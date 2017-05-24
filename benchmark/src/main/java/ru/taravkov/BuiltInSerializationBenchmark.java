package ru.taravkov;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;
import ru.taravkov.serialaser.core.reader.ObjectWriter;
import ru.taravkov.serialaser.core.reader.impl.ByteBufObjectWriter;
import ru.taravkov.serialaser.core.reader.impl.StreamObjectWriter;
import ru.taravkov.serialization.test.TestClass;

import java.io.*;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class BuiltInSerializationBenchmark extends AbstractSerializationBenchmark {
    private byte[] rawBuiltInBytes;

    @Setup(Level.Trial)
    public void setup() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(new TestClass());
        rawBuiltInBytes = byteArrayOutputStream.toByteArray();
    }

    @Override
    @Benchmark
    public Object testSerialize(Blackhole blackhole) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(new TestClass());
        blackhole.consume(byteArrayOutputStream.toByteArray());
        return null;
    }

    @Override
    @Benchmark
    public Object testDeserialize(Blackhole blackhole) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rawBuiltInBytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        TestClass value = (TestClass) objectInputStream.readObject();
        blackhole.consume(value);
        return null;
    }

    @Override
    @Benchmark
    public Object testRoundtrip(Blackhole blackhole) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(new TestClass());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        TestClass value = (TestClass) objectInputStream.readObject();
        blackhole.consume(value);
        return null;
    }

    /**

     # Run complete. Total time: 00:00:47

     Benchmark                                      Mode  Cnt      Score      Error  Units
     BuiltInSerializationBenchmark.testDeserialize  avgt   10  38113.511 ±  843.342  ns/op
     BuiltInSerializationBenchmark.testRoundtrip    avgt   10  44814.755 ± 1095.306  ns/op
     BuiltInSerializationBenchmark.testSerialize    avgt   10   5894.777 ±   96.962  ns/op

     */
}
