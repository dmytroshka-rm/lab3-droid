package battle;

import droids.Droid;
import java.util.List;
import java.util.Random;

public class Arena {
    private String name;
    private String effectDescription;

    public Arena(String name, String effectDescription) {
        this.name = name;
        this.effectDescription = effectDescription;
    }

    public String getName() {
        return name;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void applyEffect(List<Droid> team1, List<Droid> team2) {
        switch (name) {
            case "Вулкан" -> {
                team1.forEach(d -> d.setDamage((int) (d.getDamage() * 1.2)));
                team2.forEach(d -> d.setDamage((int) (d.getDamage() * 1.2)));
                System.out.println("Арена Вулкан: усі дроїди наносять +20% шкоди!");
            }
            case "Крига" -> {
                team1.forEach(d -> d.setDamage((int) (d.getDamage() * 0.8)));
                team2.forEach(d -> d.setDamage((int) (d.getDamage() * 0.8)));
                System.out.println("❄ Арена Крига: усі дроїди наносять на 20% менше шкоди!");
            }
            case "Арена хаосу" -> {
                Random rand = new Random();
                if (rand.nextBoolean()) {
                    team1.forEach(d -> d.setDamage(d.getDamage() + 5));
                    team2.forEach(d -> d.setDamage(d.getDamage() + 5));
                    System.out.println("⚡ Арена хаосу: +5 до урону для всіх!");
                } else {
                    team1.forEach(d -> d.takeDamage(10));
                    team2.forEach(d -> d.takeDamage(10));
                    System.out.println("⚡ Арена хаосу: всі отримують 10 шкоди на старті!");
                }
            }
            case "Туман" -> {
                System.out.println("🌫 Арена Туман: точність снайперів знижена на 20%!");
            }
        }

        team1.forEach(d -> d.applyArenaEffect(name));
        team2.forEach(d -> d.applyArenaEffect(name));
    }

    public static Arena getRandomArena() {
        List<Arena> arenas = List.of(
                new Arena("Вулкан", "Усі дроїди наносять +20% шкоди"),
                new Arena("Туман", "Точність снайперів знижена на 20%"),
                new Arena("Крига", "Усі дроїди наносять на 20% менше шкоди"),
                new Arena("Арена хаосу", "Несподівані ефекти на старті бою")
        );

        Random rand = new Random();
        return arenas.get(rand.nextInt(arenas.size()));
    }

    @Override
    public String toString() {
        return name + " (" + effectDescription + ")";
    }
}
