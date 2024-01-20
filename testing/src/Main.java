import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Csv csv = new Csv();
        Menu menu = new Menu();
        Reader reader = new Reader();

        final ArrayList<Row> rows = csv.read();

        Schools schools = new Schools(rows);
        Cities cities = new Cities(rows);

        boolean running = true;
        int input;

       while (running) {
           menu.print();
           input = reader.read();

           if (input == 0) {
               running = false;
           } else if (input == 1) {
               schools.printNames();
           } else if (input == 2) {
               System.out.println("Please select either year 4, 5 or 6");
               input = reader.read();
               cities.print(input);
           } else if (input == 3) {
               schools.printGender();
           }
       }
    }
}