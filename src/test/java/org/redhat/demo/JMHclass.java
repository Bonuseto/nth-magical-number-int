package org.redhat.demo;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 100000)
@Measurement(iterations = 5000)
public class JMHclass {

    final static int N = 12;
    final static int A = 14;
    final static int B = 16;

    @Benchmark
    public int[] benchmarkSteps() {
        return Main.steps(A, B);
    }

    @Benchmark
    public long benchmarkNthNonZeroMagicalNumber() {
        return Main.nthNonZeroMagicalNumber(N, A, B);
    }

    @Benchmark
    public int benchmarkNthMagicalNumber() {
        return Main.nthMagicalNumber(N, A, B);
    }
}
