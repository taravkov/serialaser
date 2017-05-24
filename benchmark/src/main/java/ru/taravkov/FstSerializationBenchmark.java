package ru.taravkov;

import org.junit.Before;
import org.nustaq.serialization.FSTConfiguration;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;
import ru.taravkov.serialization.test.TestClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * @author vtaravkov
 */
public class FstSerializationBenchmark extends AbstractSerializationBenchmark {
    private FSTConfiguration fstConfiguration;

    private byte[] rawBytes;

    @Setup(Level.Trial)
    public void setup() throws Exception {
        this.fstConfiguration = FSTConfiguration.createDefaultConfiguration();
        this.rawBytes = (byte[]) testSerialize(null);
    }

    @Override
    @Benchmark
    public Object testSerialize(Blackhole blackhole) throws Exception {
        return fstConfiguration.asByteArray(new TestClass());
    }

    @Override
    @Benchmark
    public Object testDeserialize(Blackhole blackhole) throws Exception {
        TestClass value = (TestClass) fstConfiguration.asObject(rawBytes);
        blackhole.consume(value);
        return value;
    }

    @Override
    @Benchmark
    public Object testRoundtrip(Blackhole blackhole) throws Exception {
        byte[] bytes = fstConfiguration.asByteArray(new TestClass());
        TestClass value = (TestClass) fstConfiguration.asObject(bytes);
        blackhole.consume(value);
        return value;
    }
}
