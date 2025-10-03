package droids;

import ui.Colors;

import java.util.List;

public class SniperDroid extends Droid {
    private double accuracy;
    private double criticalChance;

    public SniperDroid(String name, int health, int damage, double accuracy, double criticalChance) {
        super(name, health, damage);
        this.accuracy = accuracy;
        this.criticalChance = criticalChance;
    }

    @Override
    public void act(List<Droid> allies, List<Droid> enemies) {
        Droid target = chooseRandomAlive(enemies);
        if (target != null) {
            this.attack(target);
        }
    }

    @Override
    public void attack(Droid enemy) {
        if (this.isAlive()) {
            double roll = random.nextDouble();

            if (roll > accuracy) {
                System.out.println(Colors.CYAN + this.name + " промахнувся!" + Colors.RESET);
                return;
            }

            int actualDamage = this.damage;

            if (random.nextDouble() < criticalChance) {
                actualDamage *= 2;
                System.out.println(Colors.RED + this.name + " робить критичний постріл! 🔥" + Colors.RESET);
            } else {
                System.out.println(Colors.YELLOW + this.name + " робить постріл!" + Colors.RESET);
            }

            enemy.takeDamage(actualDamage);
            System.out.println(Colors.RED + enemy.name + " отримує " + actualDamage + " шкоди!" + Colors.RESET);
            System.out.println(Colors.RED + enemy.name + " " + enemy.getHealthBar() + Colors.RESET);
        }
    }

    public void reduceAccuracy(double amount) {
        this.accuracy -= amount;
        if (this.accuracy < 0) this.accuracy = 0;
    }
    @Override
    public void applyArenaEffect(String arenaName) {
        if (arenaName.equals("Туман")) {
            reduceAccuracy(0.2);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [Accuracy: " + accuracy + ", Crit: " + criticalChance + "]";
    }
}
