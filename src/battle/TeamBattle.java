package battle;

import droids.*;
import java.util.*;

public class TeamBattle implements Battle {
    private List<Droid> team1;
    private List<Droid> team2;
    private List<String> log = new ArrayList<>();
    private Random random = new Random();

    public TeamBattle(List<Droid> team1, List<Droid> team2) {
        this.team1 = new ArrayList<>(team1);
        this.team2 = new ArrayList<>(team2);
    }

    @Override
    public void start() {
        System.out.println("=== Командний бій розпочався! ===");
        log.add("Командний бій: " + team1.size() + " vs " + team2.size());

        boolean turn = true;
        while (isTeamAlive(team1) && isTeamAlive(team2)) {
            List<Droid> attackingTeam = turn ? team1 : team2;
            List<Droid> defendingTeam = turn ? team2 : team1;

            Droid attacker = chooseRandomAlive(attackingTeam);
            if (attacker != null) {
                attacker.act(attackingTeam, defendingTeam);
                log.add(attacker.getName() + " робить хід");
            }

            turn = !turn;

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String winner = isTeamAlive(team1) ? "Команда 1" : "Команда 2";
        System.out.println("Кінець бою! Переможець: " + winner + " ");
        log.add("Переможець: " + winner);
        team1.forEach(Droid::reset);
        team2.forEach(Droid::reset);
    }

    private boolean isTeamAlive(List<Droid> team) {
        return team.stream().anyMatch(Droid::isAlive);
    }

    private Droid chooseRandomAlive(List<Droid> team) {
        List<Droid> alive = team.stream().filter(Droid::isAlive).toList();
        if (alive.isEmpty()) return null;
        return alive.get(random.nextInt(alive.size()));
    }

    @Override
    public Droid getWinner() {
        if (isTeamAlive(team1)) return team1.get(0);
        if (isTeamAlive(team2)) return team2.get(0);
        return null;
    }

    @Override
    public List<String> getLog() {
        return log;
    }

}
