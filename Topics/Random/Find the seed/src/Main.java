import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seedStart = scanner.nextInt();
        int seedStop = scanner.nextInt();
        int randomNumbers = scanner.nextInt();
        int randomTo = scanner.nextInt();
        int maxInt = 10000000;
        int seed = 0;
        int max = 0;
        Integer[] randomArr = new Integer[randomNumbers];

        for (int j = seedStart; j <= seedStop; j++) {
            Random random = new Random(j);
            for (int i = 0; i < randomNumbers; i++) {
//                System.out.print(random.nextInt(randomTo) + " ");
                randomArr[i] = random.nextInt(randomTo);
            }
            System.out.println();
            max = Collections.max(Arrays.asList(randomArr));
            if (maxInt > max ) {
                seed = j;
                maxInt = max;
            }

        }
        System.out.println(seed);
        System.out.println(maxInt);
        System.out.println();

    }

}