import java.util.*;

static class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first  = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] errorCounts = new int[n];

            for (int i = 0; i < n; ++i) {
                errorCounts[i] = scanner.nextInt();
            }

            int m = scanner.nextInt();

            List<Pair<Integer, Integer>> goodPeriod = new ArrayList<>();

            int left = 0;
            boolean isDecreasing = false;
            for (int i = 0; i < n - 1; ++i) {
                if (!isDecreasing && errorCounts[i] > errorCounts[i + 1]) {
                    left = i;
                    isDecreasing = true;
                } else if (isDecreasing && errorCounts[i] > errorCounts[i + 1]) {
                    left = i;
                } else if (isDecreasing && errorCounts[i] < errorCounts[i + 1]) {
                    goodPeriod.add(new Pair<>(left, i + 1));
                    isDecreasing = false;
                }
            }

            for (int i = 0; i < m; ++i) {
                int l = scanner.nextInt();
                int r = scanner.nextInt();

                boolean isFailure = true;
                for (Pair<Integer, Integer> pair : goodPeriod) {
                    if (pair.getFirst() >= l - 1 && pair.getSecond() <= r - 1) {
                        isFailure = false;
                        break;
                    }
                }

                if (isFailure) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }
    }
}
