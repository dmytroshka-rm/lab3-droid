package battle;

import droids.Droid;
import java.util.ArrayList;
import java.util.List;

public class OneOnOneBattle implements Battle {
    private Droid droid1;
    private Droid droid2;
    private List<String> log = new ArrayList<>();

    public OneOnOneBattle(Droid droid1, Droid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
    }

    @Override
    public void start() {
        System.out.println("=== Початок бою: " + droid1.getName() + " VS " + droid2.getName() + " ===");
        log.add("Бій почався: " + droid1.getName() + " VS " + droid2.getName());

        boolean turn = true; // true → droid1 ходить, false → droid2
        while (droid1.isAlive() && droid2.isAlive()) {
            if (turn) {
                droid1.act(List.of(droid1), List.of(droid2));
                log.add(droid1.getName() + " робить хід");
            } else {
                droid2.act(List.of(droid2), List.of(droid1));
                log.add(droid2.getName() + " робить хід");
            }
            turn = !turn;

            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }

        Droid winner = getWinner();
        if (winner != null) {
            System.out.println("=== Кінець бою! Переможець: " + winner.getName() + " ===");
            log.add("Переможець: " + winner.getName());
        } else {
            System.out.println("=== Кінець бою! Нічия ===");
            log.add("Нічия");
        }

        droid1.reset();
        droid2.reset();
    }

    @Override
    public Droid getWinner() {
        if (droid1.isAlive() && !droid2.isAlive()) return droid1;
        if (droid2.isAlive() && !droid1.isAlive()) return droid2;
        return null;
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
