package org.redhat.demo;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 100000)
@Measurement(iterations = 5000)
public class JMHclass {

    int n;
    int a;
    int b;

    @Setup
    public void init() {
        n = 12;
        a = 14;
        b = 16;
    }

    @Benchmark
    public int benchmarkModulate() {
        return Main.modulate(12);
    }

    @Benchmark
    public boolean benchmarkIntIsNotNull() {
        return Main.intIsNotNull(a);
    }

    @Benchmark
    public int[] benchmarkSteps() {
        return Main.steps(a,b);
    }

    @Benchmark
    public long benchmarkNthNonZeroMagicalNumber() {
        return Main.nthNonZeroMagicalNumber(n, a, b);
    }

    @Benchmark
    public int benchmarkGreatestCommonDenominator() {
        return Main.greatestCommonDenominator(a,b);
    }

    @Benchmark
    public int benchmarkLowestCommonMultiple(){
        return Main.lowestCommonMultiple(a,b);
    }

    @Benchmark
    public int benchmarkNthMagicalNumber(){
        return Main.nthMagicalNumber(n,a,b);
    }


}
