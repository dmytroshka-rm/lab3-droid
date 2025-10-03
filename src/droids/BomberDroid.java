package droids;

import ui.Colors;

import java.util.List;

public class BomberDroid extends Droid {
    private int explosionDamage;

    public BomberDroid(String name, int health, int damage, int explosionDamage) {
        super(name, health, damage);
        this.explosionDamage = explosionDamage;
    }

    @Override
    public void act(List<Droid> allies, List<Droid> enemies) {
        super.act(allies, enemies);
        if (!this.isAlive()) {
            this.explode(enemies);
        }
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (!this.isAlive()) {
            System.out.println(Colors.RED + this.name + " вибухає при знищенні!" + Colors.RESET);
        }
    }

    public void explode(List<Droid> enemies) {
        if (!this.isAlive()) {
            for (Droid enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.takeDamage(explosionDamage);
                    System.out.println(Colors.RED + enemy.name + " отримує " +
                            explosionDamage + " шкоди від вибуху!" + Colors.RESET);
                    System.out.println(Colors.RED + enemy.name + " " + enemy.getHealthBar() + Colors.RESET);
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [Explosion: " + explosionDamage + "]";
    }
}
