package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        playGame();
    }

    public static void checkNumber(String code, String answer) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == answer.charAt(i)) {
                bulls++;
            }
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(i) == answer.charAt(j)) {
                    if (j != i) {
                        cows++;
                    }
                }
            }
        }
        System.out.print("Grade: ");
        if (code.equals(answer)) {
            System.out.print(bulls + " bulls.\nCongrats!");
        } else if (cows == 0 && bulls == 0) {
            System.out.print("None.");
        } else if (bulls == 1 && cows == 0) {
            System.out.print("1 bull.");
        } else if (bulls > 1 && cows == 0) {
            System.out.print(bulls + " bulls.");
        } else if (bulls == 0 && cows == 1) {
            System.out.print("1 cow.");
        } else if (bulls == 0 && cows > 1) {
            System.out.print(cows + " cows.");
        } else if (bulls == 1 && cows == 1) {
            System.out.print("1 bull and 1 cow.");
        } else if (bulls > 1 && cows == 1) {
            System.out.print(bulls + " bulls and 1 cow.");
        } else if (bulls == 1 && cows > 1) {
            System.out.print("1 bull and " + cows + " cows.");
        } else if (bulls > 1 && cows > 1) {
            System.out.print(bulls + " bulls and " + cows + " cows.");
        }
        System.out.println(" The secret code is " + code + ".");
    }

    public static void playGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int lenCode = readNumber();
        if (lenCode > 36) {
            System.out.println("Error: secret length cannot be greater than 36");
            System.exit(1);
        } else if (lenCode < 1) {
            System.out.println("Error: secret length cannot be less than 1");
            System.exit(1);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int symbolsRange = readNumber();

        if (symbolsRange < lenCode) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n"
            ,lenCode, symbolsRange);
            System.exit(1);
        } else if (symbolsRange > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(1);
        }
        String zakr = "";
        if (symbolsRange < 10) {
            zakr = "(0-" + symbolsRange + ")";
        } else if (symbolsRange == 10) {
            zakr = "(0-9)";
        } else {
            zakr = "(0-9)(a-" + (char)(86+symbolsRange) + ")";
        }
        System.out.println("The secret code is prepared: " + "*".repeat(symbolsRange) + " " + zakr);

        System.out.println("Okay, let's start a game!");
        String secretNumber = getSecretNumber(lenCode, symbolsRange);
        guessNumber(secretNumber);
    }

    private static int readNumber() {
        int number = 0;
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        } else {
            String userInput = scanner.next();
            System.out.printf("Error: %s isn't a valid number.", userInput);
            System.out.println();
            System.exit(1);
        }
        return number;
    }

    public static void guessNumber(String secretNumber ) {
        Scanner sc = new Scanner(System.in);
        String answer;
        int turn = 1;
        do {
            System.out.printf("Turn %d. Answer:\n", turn);
            turn++;
            answer = sc.nextLine();
            checkNumber(secretNumber, answer);
        } while (!answer.equals(secretNumber));
    }


    private static String getSecretNumber(int secretLength, int symbolsRange) {
        List<Character> charSet = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) {
            charSet.add(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            charSet.add(c);
        }

        int maxIndex = symbolsRange > charSet.size() ? charSet.size() : symbolsRange;
        List<Character> selectedCharSet = new ArrayList<>(charSet.subList(0, maxIndex));

        Collections.shuffle(selectedCharSet);

        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < secretLength; i++) {
            int randomIndex = random.nextInt(selectedCharSet.size());
            randomString.append(selectedCharSet.get(randomIndex));
            selectedCharSet.remove(randomIndex);
        }
        return randomString.toString();
    }
}