package org.redhat.demo;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;


public class Main {
public static final int MODULO = (int) (Math.pow(10, 9) + 7);


    public static int modulate(final long result) {
        return (int) (result % MODULO);
    }

    public static boolean intIsNotNull(final int a) {
        return a != 0;
    }

public static int[] steps(final int smaller, final int larger) {
        final int lcm = lowestCommonMultiple(smaller, larger);
        final int max = lcm / smaller;
        final int min = lcm / larger;
        final int[] result = new int[max * 2];

        int pos = 0;
        for (int i = 1; i <= max; i++) {
            result[pos++] = (i * smaller);
            if (i <= min) {
                result[pos++] = (i * larger);
            }
        }

        return Arrays.stream(result)
                .filter(x -> x!=0)////Reason of slow perfomance
                .sorted()
                .distinct()
                .toArray();
    }


    public static long nthNonZeroMagicalNumber(final int N, final int smaller, final int larger) {
        final int[] stepsInCycle = steps(smaller, larger);
        final long lcm = stepsInCycle[stepsInCycle.length - 1];
        final int inOneCycle = stepsInCycle.length;
        final int fullCycleCount = N / inOneCycle;
        int count = fullCycleCount * inOneCycle;
        final long evaluated = fullCycleCount * lcm;
        if (count == N) {
            return evaluated;
        }
        final int remainder = N - count - 1;
        return stepsInCycle[remainder] + evaluated;
    }


    public static int greatestCommonDenominator(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    public static int lowestCommonMultiple(final int a, final int b) {
        return a * (b / greatestCommonDenominator(a, b));
    }


    public static int nthMagicalNumber(final int N, final int A, final int B) {
        if (N == 0) {
            return 0;
        } else if (A == B) {
            final long result = (long) A * (long) N;
            return modulate(result);
        } else if (N == 1) {
            return modulate(Math.min(A, B));
        }
        return modulate(nthNonZeroMagicalNumber(N, Math.min(A, B), Math.max(A, B)));
    }


    public static void main(String[] args) {
        int result = nthMagicalNumber(4, 2, 3);
        System.out.println(result);
    }
}
