import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "src/resources/movementList.csv";
        Movements mov = new Movements(path);
        System.out.println("Общий приход на карту : " + mov.getIncomeSum());
        System.out.println("Общий расход по карте : " + mov.getExpenseSum());
        mov.expenseList();
    }
}
