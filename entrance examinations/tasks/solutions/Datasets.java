import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int n = scanner.nextInt();

        if (s == 1) {
            System.out.println(0);
            return;
        }

        int totalServerCapacity = (1 + s) * s / 2;

        int datasetCounter = n;
        while (datasetCounter >= totalServerCapacity) {
            datasetCounter -= totalServerCapacity;
        }

        if (datasetCounter != 0) {
            int currentServerCapacity = s;

            while (datasetCounter >= currentServerCapacity) {
                datasetCounter -= currentServerCapacity;
                --currentServerCapacity;
            }
        }

        System.out.print(datasetCounter);
    }
}
