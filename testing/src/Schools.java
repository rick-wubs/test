import java.util.ArrayList;

public class Schools {
    ArrayList<String> names = new ArrayList<>();

    ArrayList<School> schools = new ArrayList<School>();

    public Schools(ArrayList<Row> rows) {

        // create unique school name list
        for (Row row : rows) {
            if (!names.contains(row.name)) {
                names.add(row.name);
            }
        }

        // create each unique school
        for (String name : names) {
            schools.add(new School(name));
        }

        // fill the properties for each school
        for (Row row : rows) {
            for (School school : schools) {
                if (row.name.equals(school.name)) {
                    school.maleStudents += row.male;
                    school.femaleStudents += row.female;
                }
            }
        }
    }

    public void printNames() {
        for(String name : names) {
            System.out.println(name);
        }
    }

    public void printGender() {
        for (School school : schools) {
            System.out.print(school.name + ": ");

            for (int m = 0; m < school.maleStudents; m++) {
                System.out.print("M");
            }
            for (int f = 0; f < school.femaleStudents; f++) {
                System.out.print("F");
            }

            System.out.println();
        }
    }
}
