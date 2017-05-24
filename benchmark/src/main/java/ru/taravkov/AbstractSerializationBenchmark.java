package ru.taravkov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;


/**
 * @author vtaravkov
 * @since 1.0
 */
@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public abstract class AbstractSerializationBenchmark {
    public abstract Object testSerialize(Blackhole blackhole) throws Exception;

    public abstract Object testDeserialize(Blackhole blackhole) throws Exception;

    public abstract Object testRoundtrip(Blackhole blackhole) throws Exception;
}
