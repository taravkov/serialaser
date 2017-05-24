package ru.taravkov;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;
import ru.taravkov.serialaser.core.reader.ObjectReader;
import ru.taravkov.serialaser.core.reader.ObjectWriter;
import ru.taravkov.serialaser.core.reader.impl.ByteBufObjectReader;
import ru.taravkov.serialaser.core.reader.impl.ByteBufObjectWriter;
import ru.taravkov.serialaser.core.reader.impl.StreamObjectWriter;
import ru.taravkov.serialization.test.TestClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author vtaravkov
 */
public class SerialaserOptimizedSerializationBenchmark extends AbstractSerializationBenchmark {
    private ObjectWriter<ByteBufOutputStream> objectWriter;

    private ObjectReader<ByteBufInputStream> objectReader;

    private ByteBuf rawBytes;

    @Setup(Level.Trial)
    public void setup() throws IOException {
        this.objectWriter = new ByteBufObjectWriter();
        this.objectReader = new ByteBufObjectReader();
        this.rawBytes = testSerialize(null);
    }

    @Override
    @Benchmark
    public ByteBuf testSerialize(Blackhole blackhole) {
        ByteBuf buffer = Unpooled.buffer(200);
        ByteBufOutputStream outputStream = new ByteBufOutputStream(buffer);
        objectWriter.write(new TestClass(), outputStream);
        return buffer;
    }

    @Override
    @Benchmark
    public ByteBuf testDeserialize(Blackhole blackhole) {
        ByteBufInputStream inputStream = new ByteBufInputStream(rawBytes);
        TestClass value = (TestClass) objectReader.read(inputStream);
        blackhole.consume(value);
        return null;
    }

    @Override
    @Benchmark
    public ByteBuf testRoundtrip(Blackhole blackhole) {
        ByteBuf buffer = Unpooled.buffer(200);
        ByteBufOutputStream outputStream = new ByteBufOutputStream(buffer);
        objectWriter.write(new TestClass(), outputStream);
        ByteBufInputStream inputStream = new ByteBufInputStream(buffer);
        TestClass value = (TestClass) objectReader.read(inputStream);
        blackhole.consume(value);
        return null;
    }
}
