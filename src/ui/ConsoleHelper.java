package ui;

import java.util.Scanner;

public class ConsoleHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Введіть число: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    // читання рядка
    public static String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }
}
