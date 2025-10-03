package droids;

import ui.Colors;

import java.util.List;

public class HealerDroid extends Droid {
    private int energy;
    private int healPower;

    public HealerDroid(String name, int health, int damage, int energy, int healPower) {
        super(name, health, damage);
        this.energy = energy;
        this.healPower = healPower;
    }

    @Override
    public void act(List<Droid> allies, List<Droid> enemies) {
        if (this.isAlive() && energy >= 10 && random.nextBoolean()) {
            Droid ally = chooseRandomAlive(allies);
            if (ally != null) {
                this.heal(ally);
                return;
            }
        }
        super.act(allies, enemies);
    }

    public void heal(Droid ally) {
        if (this.isAlive() && ally.isAlive() && energy >= 10) {
            ally.health += healPower;
            energy -= 10;
            System.out.println(Colors.GREEN + this.name + " лікує " + ally.name +
                    " на " + healPower + " HP." + Colors.RESET);
            System.out.println(Colors.CYAN + ally.name + " " + ally.getHealthBar() + Colors.RESET);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [Heal: " + healPower + ", Energy: " + energy + "]";
    }
}
