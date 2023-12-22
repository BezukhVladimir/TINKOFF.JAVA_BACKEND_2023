import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            String s = scanner.next();

            if (n <= k) {
                System.out.println(0);
                return;
            }

            Map<Character, Integer> charCount = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                charCount.compute(c, (key, value) -> (value == null) ? 1 : value + 1);
            }

            List<Integer> counts = new ArrayList<>(charCount.values());
            Collections.sort(counts);

            int removed = 0;
            int removals = k;

            for (int count : counts) {
                if (removals >= count) {
                    removals -= count;
                    ++removed;
                } else {
                    break;
                }
            }

            int result = charCount.size() - removed;
            System.out.println(result);
        }
    }
}
