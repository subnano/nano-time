package io.nano.time.jni;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(5)
public class NativeTimeBench {

    public static void main(String[] args) throws RunnerException {
        System.setProperty("jmh.ignoreLock", "true");
        Options options = new OptionsBuilder()
                .include(NativeTimeBench.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Benchmark
    public long nanoTime() {
        return System.nanoTime();
    }

    @Benchmark
    public long nativeCurrentTimeMicros() {
        return NativeTime.currentTimeMicros();
    }

    @Benchmark
    public long nativeCurrentTimeNanos() {
        return NativeTime.currentTimeNanos();
    }
}