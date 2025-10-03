package storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BattleLogger {

    // Зберегти лог у файл
    public static void saveLog(String filename, List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : log) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Лог бою збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні логу: " + e.getMessage());
        }
    }

    // Завантажити лог з файлу
    public static List<String> loadLog(String filename) {
        List<String> log = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.add(line);
            }
            System.out.println("Лог бою завантажено з файлу: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
        return log;
    }

    // Відтворити лог бою у консолі
    public static void replay(String filename) {
        List<String> log = loadLog(filename);
        System.out.println("Відтворення бою з файлу");
        for (String line : log) {
            System.out.println(line);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=== Кінець відтворення ===");
    }
}
