import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder onlyLetters = new StringBuilder();
        for (String word : strings) {
            onlyLetters.append(word);
        }
        for (int i = 0; i < onlyLetters.length(); i++) {
            if (onlyLetters.charAt(i) > 47 && onlyLetters.charAt(i) < 58) {
                onlyLetters.replace(i, i + 1 , "");
                i--;
            }
        }
        return onlyLetters.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}