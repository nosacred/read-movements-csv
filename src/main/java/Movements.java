import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {
    List<Operation> move = new ArrayList<>();
    double income = 0;
    double expense = 0;

    public Movements(String pathMovementsCsv) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
        Pattern pattern = Pattern.compile("\"\\d+,\\d+\"");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                int first = line.indexOf("\"");
                int second = line.lastIndexOf("\"");
                String substring = line.substring(first, second);
                String substring2 = substring.replaceAll(",", ".");
                line = line.replace(substring, substring2);
            }

            String[] fragments = line.split(",", 8);
            String b = fragments[6].replace("\"", "");
            String c = b.replace(",", ".");
            fragments[6] = c;
            String s = fragments[7].replace("\"", "");
            String a = s.replace(",", ".");
            fragments[7] = a;
            move.add(new Operation(fragments[0], fragments[1], fragments[2], fragments[3], fragments[4],
                    fragments[5], fragments[6], fragments[7]));
        }
        move.remove(0);
    }


    public double getExpenseSum() {
        for (Operation s : move) {
            expense += Double.parseDouble(s.expense);
        }
        return expense;
    }


    public double getIncomeSum() {
        for (Operation s : move) {
            income += Double.parseDouble(s.income);
        }
        return income;
    }

    public void expenseList() {
        List<Operation> exp = new ArrayList<>();
        HashMap<String, Double> expensesMap = new HashMap<>();
        for (Operation s : move) {
            if (Double.parseDouble(s.expense) != 0) {
                exp.add(s);
            }
        }
        for (Operation s : exp) {
            s.property = s.property.split("\\s{3,}", 3)[1];
            String[] key = s.property.split("\\\\|/");
            s.property = key[key.length - 1];
        }

        for (Operation s : exp) {
            if (expensesMap.containsKey(s.property)) {
                double cash = expensesMap.get(s.property);
                expensesMap.put(s.property, Double.parseDouble(s.expense) + cash);
            } else
                expensesMap.put(s.property, Double.parseDouble(s.expense));
        }
        for (String s : expensesMap.keySet()) {
            System.out.println("Расход на " + s + " составляет " + expensesMap.get(s));
        }
    }
}