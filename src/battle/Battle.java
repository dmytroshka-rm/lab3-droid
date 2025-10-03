package battle;

import droids.Droid;
import java.util.List;

public interface Battle {
    void start();                  // запуск бою
    Droid getWinner();             // визначення переможця (для командного може бути null)
    List<String> getLog();         // отримання логу
}
