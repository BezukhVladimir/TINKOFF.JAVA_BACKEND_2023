import java.util.*;

public class Main {
    static class Data {
        int startPosition;
        long value;

        public Data(int sp, long v) {
            this.startPosition = sp;
            this.value = v;
        }

        public boolean isPositive() {
            return (this.value >= 0);
        }

        public boolean isNegative() {
            return (this.value < 0);
        }

        public long getAbs() {
            return Math.abs(this.value);
        }

        public void increase(long d) {
            this.value += d;
        }

        public void decrease(long d) {
            this.value -= d;
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int c = scanner.nextInt();
            int d = scanner.nextInt();
            Data[] errorCounts = new Data[n];

            int minPositiveNumberId = 0;
            long minPositiveNumber = 1000000001;

            int negativeNumberCounter = 0;

            for (int i = 0; i < n; ++i) {
                errorCounts[i] = new Data(i, scanner.nextLong());

                if (errorCounts[i].isNegative()) {
                    ++negativeNumberCounter;
                } else if (errorCounts[i].value < minPositiveNumber) {
                    minPositiveNumber = errorCounts[i].value;
                    minPositiveNumberId = i;
                }
            }

            if (negativeNumberCounter % 2 == 0) {
                while (c > 0) {
                    errorCounts[minPositiveNumberId].decrease(d);
                    --c;

                    if (errorCounts[minPositiveNumberId].isNegative()) {
                        break;
                    }
                }
            }

            PriorityQueue<Data> minHeap = new PriorityQueue<>(new Comparator<Data>() {
                @Override
                public int compare(Data data1, Data data2) {
                    return Long.compare(data1.getAbs(), data2.getAbs());
                }
            });

            for (int i = 0; i < n; ++i) {
                minHeap.offer(errorCounts[i]);
            }

            while (c > 0) {
                Data minMultiplier = minHeap.poll();

                if (minMultiplier.isPositive()) {
                    minMultiplier.increase(d);
                    --c;
                } else {
                    minMultiplier.decrease(d);
                    --c;
                }

                errorCounts[minMultiplier.startPosition].value = minMultiplier.value;
                minHeap.offer(minMultiplier);
            }

            for (int i = 0; i < n; ++i) {
                System.out.print(errorCounts[i].value);
                if (i != n - 1) {
                    System.out.print(' ');
                }
            }
        }
    }
}
