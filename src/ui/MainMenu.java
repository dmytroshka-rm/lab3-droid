package ui;

import battle.*;
import droids.*;
import storage.BattleLogger;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    private List<Droid> droids = new ArrayList<>();
    private List<String> lastLog = new ArrayList<>();

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = ConsoleHelper.readInt("Ваш вибір: ");

            switch (choice) {
                case 1 -> createDroid();
                case 2 -> showDroids();
                case 3 -> startOneOnOne();
                case 4 -> startTeamBattle();
                case 5 -> saveBattleLog();
                case 6 -> loadBattleLog();
                case 7 -> running = false;
                default -> System.out.println("Невірний вибір!");
            }
        }
        System.out.println("Вихід з гри...");
    }

    private void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Створити дроїда");
        System.out.println("2. Показати список дроїдів");
        System.out.println("3. Запустити бій 1 на 1");
        System.out.println("4. Запустити командний бій");
        System.out.println("5. Записати проведений бій у файл");
        System.out.println("6. Відтворити бій із файлу");
        System.out.println("7. Вийти");
    }

    // створення дроїда
    private void createDroid() {
        System.out.println("Оберіть тип дроїда:");
        System.out.println("1. TankDroid");
        System.out.println("2. SniperDroid");
        System.out.println("3. HealerDroid");
        System.out.println("4. AssassinDroid");
        System.out.println("5. BomberDroid");
        System.out.println("6. SupportDroid");

        int type = ConsoleHelper.readInt("Ваш вибір: ");
        String name = ConsoleHelper.readString("Введіть ім'я дроїда: ");

        Droid droid = null;

        switch (type) {
            case 1 -> droid = new TankDroid(name, 150, 20, 10, 0.2);
            case 2 -> droid = new SniperDroid(name, 80, 40, 0.7, 0.25);
            case 3 -> droid = new HealerDroid(name, 90, 15, 50, 25);
            case 4 -> droid = new AssassinDroid(name, 70, 25, 0.3);
            case 5 -> droid = new BomberDroid(name, 100, 25, 30);
            case 6 -> droid = new SupportDroid(name, 90, 15, 5, 50);
            default -> System.out.println("Невірний вибір!");
        }

        if (droid != null) {
            droids.add(droid);
            System.out.println("Додано: " + droid);
        }
    }

    //  список дроїдів
    private void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("Дроїдів ще не створено.");
            return;
        }
        System.out.println("=== Список дроїдів ===");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i));
        }
    }

    // бій 1 на 1
    private void startOneOnOne() {
        if (droids.size() < 2) {
            System.out.println("Недостатньо дроїдів!");
            return;
        }
        showDroids();
        int i1 = ConsoleHelper.readInt("Оберіть дроїда 1: ") - 1;
        int i2 = ConsoleHelper.readInt("Оберіть дроїда 2: ") - 1;

        if (i1 == i2) {
            System.out.println("Неможливо вибрати одного й того ж дроїда!");
            return;
        }
        Arena arena = Arena.getRandomArena();
        System.out.println("Обрана арена: " + arena);
        arena.applyEffect(List.of(droids.get(i1)), List.of(droids.get(i2)));

        Battle battle = new OneOnOneBattle(droids.get(i1), droids.get(i2));
        battle.start();
        lastLog = battle.getLog();
    }

    // командний бій
    private void startTeamBattle() {
        if (droids.size() < 4) {
            System.out.println("Потрібно хоча б 4 дроїди для командного бою!");
            return;
        }
        showDroids();
        System.out.println("Виберіть номери дроїдів для команди 1 (через пробіл, кінець - 0):");
        List<Droid> team1 = selectTeam();
        System.out.println("Виберіть номери дроїдів для команди 2 (через пробіл, кінець - 0):");
        List<Droid> team2 = selectTeam();

        if (team1.isEmpty() || team2.isEmpty()) {
            System.out.println("Команди не можуть бути порожніми!");
            return;
        }

        for (Droid d1 : team1) {
            if (team2.contains(d1)) {
                System.out.println(" Один і той самий дроїд не може бути в обох командах!");
                return;
            }
        }
        Arena arena = Arena.getRandomArena();
        System.out.println("Обрана арена: " + arena);
        arena.applyEffect(team1, team2);

        Battle battle = new TeamBattle(team1, team2);
        battle.start();
        lastLog = battle.getLog();
    }

    private List<Droid> selectTeam() {
        List<Droid> team = new ArrayList<>();
        while (true) {
            int idx = ConsoleHelper.readInt("");
            if (idx == 0) break;
            if (idx > 0 && idx <= droids.size()) {
                Droid d = droids.get(idx - 1);
                if (!team.contains(d)) {
                    team.add(d);
                }
            }
        }
        return team;
    }

    // збереження / відтворення
    private void saveBattleLog() {
        if (lastLog.isEmpty()) {
            System.out.println("Логу ще немає!");
            return;
        }
        String filename = ConsoleHelper.readString("Введіть ім'я файлу: ");
        BattleLogger.saveLog(filename, lastLog);
    }

    private void loadBattleLog() {
        String filename = ConsoleHelper.readString("Введіть ім'я файлу: ");
        BattleLogger.replay(filename);
    }
}
