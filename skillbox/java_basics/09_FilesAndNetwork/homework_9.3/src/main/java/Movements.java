import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Movements {
    public String pathToFile;
    public List<Record> recordList;

    public Movements(String pathMovementsCsv) {
        pathToFile = pathMovementsCsv;
        recordList = new ArrayList<>();
        parseFile(pathToFile);
    }

    public double getExpenseSum() {
        return recordList.stream()
            .map(record -> record.expense)
            .reduce(0.0, Double::sum);
    }

    public double getIncomeSum() {
        return recordList.stream()
            .map(Record::getIncome)
            .reduce(0.0, Double::sum);
    }

    public void parseFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            lines.stream()
                .skip(1)
                .map(this::parseLine)
                .forEach(recordList::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Record parseLine(String line) {
        if (line.contains("\"")) {
            int i = line.indexOf("\"");
            int j = line.indexOf("\"", i+1);
            String element = line.substring(i + 1, j);
            element = element.replaceAll(",", "\\.");
            line = line.replaceAll("\"\\d*,\\d*\"", element);
            return parseLine(line);
        }
        DateFormat format = new SimpleDateFormat("dd.MM.yy");
        String[] x = line.split(",");
        Record record = null;
        try {
            record = new Record(x[0], x[1], x[2], format.parse(x[3]), x[4],
                x[5], Double.parseDouble(x[6]), Double.parseDouble(x[7]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return record;
    }

    public Map<String, Double> getExpenseByOrganization() {
        return recordList.stream()
            .collect(Collectors.groupingBy(Record::truncateDescription, Collectors.summingDouble(Record::getExpense)));
    }

    @Data
    @AllArgsConstructor
    public static class Record {
        private String accountType;
        private String accountNumber;
        private String currency;
        private Date date;
        private String reference;
        private String description;
        private double income;
        private double expense;

        public String truncateDescription() {
            String[] temp = description.split("\\s{4,}", 3);
            if (temp.length < 3) {
                return description;
            }
            int i = temp[1].contains("/") ?
                temp[1].lastIndexOf("/") : temp[1].lastIndexOf("\\");
            return temp[1].substring(i+1).trim();
        }
    }
}
