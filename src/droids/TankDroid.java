package droids;

import ui.Colors;

import java.util.List;

public class TankDroid extends Droid {
    private int armor;
    private double blockChance;

    public TankDroid(String name, int health, int damage, int armor, double blockChance) {
        super(name, health, damage);
        this.armor = armor;
        this.blockChance = blockChance;
    }

    @Override
    public void act(List<Droid> allies, List<Droid> enemies) {
        Droid target = chooseRandomAlive(enemies);
        if (target != null) {
            this.attack(target);
        }
    }

    @Override
    public void takeDamage(int amount) {
        if (!this.isAlive()) return;

        if (random.nextDouble() < blockChance) {
            System.out.println(Colors.CYAN + this.name + " повністю заблокував атаку!" + Colors.RESET);
            return;
        }

        int reducedDamage = amount - armor;
        if (reducedDamage < 0) reducedDamage = 0;

        this.health -= reducedDamage;
        if (this.health < 0) this.health = 0;

        System.out.println(Colors.YELLOW + this.name + " отримує " +
                reducedDamage + " шкоди (з урахуванням броні)." + Colors.RESET);
        System.out.println(Colors.RED + this.name + " " + this.getHealthBar() + Colors.RESET);
    }

    @Override
    public String toString() {
        return super.toString() + " [Armor: " + armor + ", BlockChance: " + blockChance + "]";
    }
}
