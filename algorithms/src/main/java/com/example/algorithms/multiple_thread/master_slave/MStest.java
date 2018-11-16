package com.example.algorithms.multiple_thread.master_slave;

import android.util.Log;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by fox.hu on 2018/11/16.
 */

public class MStest {
    private static final String TAG = MStest.class.getSimpleName();

    public void test() throws Exception {
        final PrimeGeneratorService primeGeneratorService = new PrimeGeneratorService();
        final Set<BigInteger> result = primeGeneratorService.service(100);
        Log.e(TAG, "result = " + result);
    }

    class Range {
        public final int lowerBound;
        public final int upperBound;

        public Range(int lowerBound, int upperBound) {
            if (upperBound < lowerBound) {
                throw new IllegalArgumentException(
                        "upperBound should not be less than lowerBound!");
            }
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public String toString() {
            return "Range{" + "lowerBound=" + lowerBound + ", upperBound=" + upperBound + '}';
        }
    }

    class PrimeGeneratorService extends AbstractMaster<Range, Set<BigInteger>, Set<BigInteger>> {

        public PrimeGeneratorService() {
            init();
        }


        @Override
        protected Set<BigInteger> combineResults(Iterator<Future<Set<BigInteger>>> subResults) {
            final TreeSet<BigInteger> result = new TreeSet<>();

            while (subResults.hasNext()) {
                try {
                    result.addAll(subResults.next().get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    final Throwable cause = e.getCause();
                    if (SubTaskFailureException.class.isInstance(cause)) {
                        final RetryInfo retryInfo = ((SubTaskFailureException) cause).retryInfo;
                        final Object subTask = retryInfo.subTask;
                        Log.e(TAG, "subTask failed " + subTask);
                    }
                    e.printStackTrace();
                }
            }

            return result;
        }

        @Override
        protected TaskDivideStrategy<Range> newTaskDivideStrategy(Object... params) {
            final int slaveNum = slaveSet.size();
            final int originalTaskScale = (Integer) params[0];
            final int subTaskScale = originalTaskScale / slaveNum;
            final int subTasksCount = (0 == (originalTaskScale % slaveNum))
                                      ? slaveNum
                                      : slaveNum + 1;

            final TaskDivideStrategy<Range> tds = new TaskDivideStrategy<Range>() {
                private int i = 1;

                @Override
                public Range nextChunk() {
                    int upperBound;
                    if (i < subTasksCount) {
                        upperBound = i * subTaskScale;
                    } else if (i == subTasksCount) {
                        upperBound = originalTaskScale;
                    } else {
                        return null;
                    }

                    int lowerBound = (i - 1) * subTaskScale + 1;
                    i++;
                    return new Range(lowerBound, upperBound);
                }
            };
            return tds;
        }

        @Override
        protected Set<? extends Slave<Range, Set<BigInteger>>> createSlaves() {
            Set<PrimeGenerator> slaves = new HashSet<>();
            for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
                slaves.add(new PrimeGenerator(new ArrayBlockingQueue<Runnable>(2)));
            }
            return slaves;
        }
    }

    private static class PrimeGenerator extends WorkThreadSlave<Range, Set<BigInteger>> {

        public PrimeGenerator(BlockingQueue<Runnable> taskQueue) {
            super(taskQueue);
        }

        @Override
        protected Set<BigInteger> doProcess(Range range) {
            Set<BigInteger> result = new TreeSet<>();
            BigInteger start = BigInteger.valueOf(range.lowerBound);
            BigInteger end = BigInteger.valueOf(range.upperBound);
            //从start开始，寻找下一个质数，如果相等则记录并重置start，超过end范围则跳出循环
            while (-1 == (start = start.nextProbablePrime()).compareTo(end)) {
                result.add(start);
            }
            return result;
        }
    }
}
