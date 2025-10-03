package droids;

import ui.Colors;

import java.util.List;

public class SupportDroid extends Droid {
    private int buffPower;
    private int energy;

    public SupportDroid(String name, int health, int damage, int buffPower, int energy) {
        super(name, health, damage);
        this.buffPower = buffPower;
        this.energy = energy;
    }

    @Override
    public void act(List<Droid> allies, List<Droid> enemies) {
        if (this.isAlive() && energy >= 10 && random.nextDouble() < 0.4) {
            Droid ally = chooseRandomAlive(allies);
            if (ally != null) {
                this.buff(ally);
                return;
            }
        }
        super.act(allies, enemies);
    }

    public void buff(Droid ally) {
        if (this.isAlive() && ally.isAlive() && energy >= 10) {
            ally.setDamage(ally.getDamage() + buffPower);
            energy -= 10;
            System.out.println(Colors.GREEN + this.name + " підсилює " + ally.getName() +
                    " на +" + buffPower + " шкоди!" + Colors.RESET);
            System.out.println(Colors.CYAN + ally.getName() + " тепер має урон " + ally.getDamage() + Colors.RESET);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [Buff: +" + buffPower + ", Energy: " + energy + "]";
    }
}
