package droids;

import ui.Colors;

import java.util.List;

public class AssassinDroid extends Droid {
    private double doubleStrikeChance;

    public AssassinDroid(String name, int health, int damage, double doubleStrikeChance) {
        super(name, health, damage);
        this.doubleStrikeChance = doubleStrikeChance;
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
            // перший удар
            enemy.takeDamage(this.damage);
            System.out.println(Colors.YELLOW + this.name + " атакує " + enemy.name +
                    " на " + this.damage + " шкоди!" + Colors.RESET);
            System.out.println(Colors.RED + enemy.name + " " + enemy.getHealthBar() + Colors.RESET);

            // шанс на другий удар
            if (enemy.isAlive() && random.nextDouble() < doubleStrikeChance) {
                enemy.takeDamage(this.damage);
                System.out.println(Colors.RED + this.name + " наносить смертельний другий удар!" + Colors.RESET);
                System.out.println(Colors.RED + enemy.name + " " + enemy.getHealthBar() + Colors.RESET);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [Double Strike Chance: " + doubleStrikeChance + "]";
    }
}
