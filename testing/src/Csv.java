import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class Csv {
    private final String CSV_FILE_PATH = "src/file.csv";
    private final String CSV_SEPARATOR_CHARACTER = ";";
    private final int CSV_COLUMN_AMOUNT = 9;

    public ArrayList<Row> read() {
        ArrayList<Row> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(CSV_SEPARATOR_CHARACTER);
                if (values.length >= CSV_COLUMN_AMOUNT) {
                    Row row = new Row(values);
                    rows.add(row);
                }
            }
        } catch (Exception e) {
            System.out.println("Warning: Error reading CSV!");
        }
        return rows;
    }
}
