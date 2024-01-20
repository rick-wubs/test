import java.util.ArrayList;

public class Cities {
    ArrayList<String> names = new ArrayList<>();

    ArrayList<City> cities = new ArrayList<City>();

    public Cities(ArrayList<Row> rows) {

        // create unique city name list
        for (Row row : rows) {
            if (!names.contains(row.name)) {
                names.add(row.name);
            }
        }

        // create each unique city
        for (String name : names) {
            cities.add(new City(name));
        }

        // fill the properties for each city
        for (Row row : rows) {
            for (City city : cities) {
                if (row.name.equals(city.name)) {
                    city.year4 = row.male4 + row.female4;
                    city.year5 = row.male5 + row.female5;
                    city.year6 = row.male6 + row.female6;
                }
            }
        }
    }

    public void print(int year) {
        for (City city : cities) {
            city.print(year);
        }
    }
}
