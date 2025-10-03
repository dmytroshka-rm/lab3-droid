package droids;

import ui.Colors;

import java.util.List;
import java.util.Random;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int damage;

    protected final int maxHealth;
    protected final int baseDamage;
    protected Random random = new Random();

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.maxHealth = health;
        this.baseDamage = damage;
    }

    public void act(List<Droid> allies, List<Droid> enemies) {
        Droid target = chooseRandomAlive(enemies);
        if (target != null) {
            this.attack(target);
        }
    }

    protected Droid chooseRandomAlive(List<Droid> team) {
        List<Droid> alive = team.stream().filter(Droid::isAlive).toList();
        if (alive.isEmpty()) return null;
        return alive.get(random.nextInt(alive.size()));
    }

    public void attack(Droid enemy) {
        if (this.isAlive()) {
            enemy.takeDamage(this.damage);
            System.out.println(Colors.YELLOW + this.name + " атакує " + enemy.name +
                    " на " + this.damage + " шкоди!" + Colors.RESET);
            System.out.println(Colors.RED + enemy.name + " " + enemy.getHealthBar() + Colors.RESET);
        }
    }

    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health < 0) this.health = 0;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getHealthBar() {
        int totalBars = 20;
        int filledBars = (int) ((double) this.health / maxHealth * totalBars);
        if (filledBars < 0) filledBars = 0;
        if (filledBars > totalBars) filledBars = totalBars;
        return "[" + "#".repeat(filledBars) + "-".repeat(totalBars - filledBars) + "] " + this.health + " HP";
    }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public String getName() { return name; }

    public void reset() {
        this.health = maxHealth;
        this.damage = baseDamage;
    }

    public void applyArenaEffect(String arenaName) {
        // за замовчуванням нічого не робить
    }

    @Override
    public String toString() {
        return name + " [HP: " + health + "/" + maxHealth + ", DMG: " + damage + "]";
    }
}
