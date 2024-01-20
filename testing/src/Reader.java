import java.util.Scanner;

public class Reader {
    public int read() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.length() != 1) {
            return -1;
        }

        if (!Character.isDigit(input.charAt(0))) {
            return -1;
        }

        return Character.getNumericValue(input.charAt(0));
    }
}
