import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * Just read all GREEN comments from top to bottom.
     * Grey comments explain why/what it does, green comments
     * instead explain the though process.
     *
     * I suggest opening your own project on the side and try it, and if you get stuck
     * follow this.
     */
    public static void main(String[] args) {
        // just to load the class containing the exercise.
        // must be a different class since main is static ;)
        My my = new My();

        // Read out the CSV.
        ArrayList<Row> rows = My.readCSV();

        // let's run the program. Please check the 'my' class for
        // the order in which I created this program, as well as an explanation
        // of the thought process.
        while(my.programMustStop == false) {
            // let's print the initial menu
            my.printMenu();

            // and wait for user input.
            my.input = my.getUserInput();
            System.out.println(my.input);

            // stop reading the grey comments first :p.
            // go from green to green, then scan all of this after each one to see the changes compared to your file.

            // make sure this comes AFTER this first getUserInput, since we didn't give
            // input an initial value in the 'my' class. This means it gets its default value,
            // which for an int is 0. Oops!.
            if (my.input == 0) {
                my.programMustStop = true;
            }

            if (my.input == 1) {
                for (String line : my.getUniqueSchoolNames()) {
                    System.out.println(line);
                }
            }

            if (my.input == 2) {
                // print a new menu to the screen
                System.out.println("Please select either year 4, 5 or 6");
                // reset the my.input, so that the user can select the school year.
                int newInput = my.getUserInput();

                // the options were here first, but it became complicated,
                // so you guessed it.. another function!
                my.printStudentTotalPerCityPerYearWithMenuOptions(newInput);
            }

            if (my.input == 3) {
                my.printMaleFemaleStudentsPerSchool();
            }
        }
    }
}

class My {
    /**
     * Prints the menu on the screen.
     * We write this method first, for a couple of reasons:
     * - It requires no user interaction to print
     * - It was the first exercise
     * - It is very simple
     * - It is the start of the program run
     * - We immediately can visually test something!
     */
    public void printMenu() {
        System.out.println("Menu options");
        System.out.println("-------------------------");
        System.out.println("1. Print a list of names.");
        System.out.println("2. Print a list of students per selected year per city.");
        System.out.println("3. Print a graphic of students for each school.");
        System.out.println("0. Quit.");
    }

    /**
     * Now that we've run the program, we see that it immediately stops.
     * This is not what needs to happen, it needs to listen to user input.
     * So let's write a variable to store this user input.
     */
    public int input;

    /**
     * So, let's get the user input. We create a scanner, read the next line of input.
     * Since it must be a single digit, we check that:
     * - it is of length 1
     * - it is a digit
     *
     * If it succeeds both checks, we get the digit from the input and return it.
     * If it fails one of the checks, we return -1. This is a convention of an error code,
     * although you have to manually make it stop the program later.
     */
    public int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // this is a guard clause. it immediately returns or throws an exception
        // (depending on what you've coded, to prevent illegal state from propagating
        if (input.length() != 1) {
            return -1;
        }

        // also a guard clause.
        if (!Character.isDigit(input.charAt(0))) {
            return -1;
        }

        // return the actual int 1, instead of a '1' char (for example).
        return Character.getNumericValue(input.charAt(0));
    }

    /**
     * Even though it listens to user input, afterwards it still stops.
     * This only needs to happen for when getUserInput() returns 0.
     *
     * So let's fix that in the program above. We will write a while loop.
     * Again, under normal circumstances, we NEVER write a while loop, EVER.
     * This is because while loops can run FOREVER. So if we do this on a server somewhere,
     * we get a crazy high bill at best, or send a billion payments or worse.
     *
     * However, in this specific case, we actually DO want the program to run forever, unless
     * we specifically stop it.
     *
     * To make sure this works, we very clearly specify the name of the variable, so we don't forget
     * to check this during other parts of the program.
     */
    public boolean programMustStop = false;

    /**
     * Okay, great. We have the menu set up, and the program loop working.
     * It even stops when the user enters 0.
     *
     * Now, let's read out the CSV file, and store it in a 'Row' class.
     * We place this at the top of our program.
     */
    public static ArrayList<Row> readCSV() {
        ArrayList<Row> rows = new ArrayList<>();

        // set these constants to match your file, separator and amount of columns!
        final String CSV_FILE_PATH = "src/file.csv";
        final String CSV_SEPARATOR_CHARACTER = ";";
        final int CSV_COLUMN_AMOUNT = 9;

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;

            reader.readLine(); // we make sure to skip the header

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(CSV_SEPARATOR_CHARACTER);
                if (values.length >= CSV_COLUMN_AMOUNT) {
                    Row row = new Row();
                    // set all the columns here with the correct name and index.
                    // also update them in the 'Row' class!
                    row.Name = values[0];
                    row.City = values[1];
                    row.Type = values[2];
                    row.Male4 = Integer.parseInt(values[3]);
                    row.Female4 = Integer.parseInt(values[4]);
                    row.Male5 = Integer.parseInt(values[5]);
                    row.Female5 = Integer.parseInt(values[6]);
                    row.Male6 = Integer.parseInt(values[7]);
                    row.Female6 = Integer.parseInt(values[8]);
                    rows.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * Now, we can print out a list of school names.
     * And we can use this in the program above to print them out when the user presses 1.
     *
     * Great, another one done!
     */
    public ArrayList<String> getUniqueSchoolNames() {
        ArrayList<Row> rows = readCSV();
        ArrayList<String> names = new ArrayList<>();

        for (Row row : rows) {
            if (!names.contains(row.Name)) {
                names.add(row.Name);
            }
        }

        return names;
    }

    /**
     * Print total students per selected year, per city.
     * Okay, that's a lot of stuff.
     *
     * Let's first try to break this down a bit. What is being asked of us?
     */
    public ArrayList<String> getTotalStudentsPerCity(int year) {

        // First we need to somehow get a list of unique cities.
        // Well, we already did pretty much the same for school names, so lets use this!
        ArrayList<String> cities = getUniqueCityNames();

        // Okay, now what?
        // Well, we need to print out something.
        // We can use an ArrayList<String> to form the list of strings,
        // which we can print later in the main program or another function, using
        // System.out.println() on each of the strings.
        // So let's make this!

        // Now we also know what the return type of the method is.
        // Before I just made it 'void' because we didn't know yet.
        ArrayList<String> result = new ArrayList<>();

        for (String city : cities) {
            // okay, how to get the student population for a city for a specific year?
            String string = city + ": " + getPopulationForCityForYear(city, year);
            result.add(string);
        }

        return result;
    }

    /**
     * Just copy the logic from getUniqueSchoolNames,
     * and modify it to get the cities :)
     */
    public ArrayList<String> getUniqueCityNames() {
        ArrayList<Row> rows = readCSV();
        ArrayList<String> names = new ArrayList<>();

        for (Row row : rows) {
            if (!names.contains(row.City)) {
                names.add(row.City);
            }
        }

        return names;
    }

    /**
     * Okay, again a tricky one!
     *
     * From the previous functions we wrote, we know we at least need to read out the csv first.
     * BTW, at this point you might've hopefully noticed it would be better to simply make
     * that a shared variable and call it once. If so, good. If not, please do that.
     * I won't, because it breaks the flow of the document, but you definitely should.
     */
    public String getPopulationForCityForYear(String city, int year) {
        // Okay, again we kind of want something unique, right?
        // We've done that before at least, so let's copy pasta it again!
        ArrayList<Row> rows = readCSV();

        int count = 0;

        for (Row row : rows) {
            if (city.equals(row.City)) {
                // we are now iterating (looping) over the csv, one row at a time.
                // so, we need to get for the selected city,
                // for the selected year, for this row, both male and female
                // student counts. (so, if year = 4, then Male4+Female4)
                // so, again a function!
                count += getStudentsPerSelectedYearForSingleRow(row, year);
            }
        }

        return String.valueOf(count);
    }

    /**
     * Okay, so how do we do this? Well, we need the data from the row, so we pass this.
     * We also need to know what year is selected. It can be either 4, 5 or 6.
     * Or an else case, because users never input what you want them..
     */
    public int getStudentsPerSelectedYearForSingleRow(Row row, int year) {
        if (year == 4) {
            return row.Male4 + row.Female4;
        } else if (year == 5) {
            return row.Male5 + row.Female5;
        } else if (year == 6) {
            return row.Male6 + row.Female6;
        } else {
            return 0;
        }
    }

    /**
     * Okay, so let's write the function for the submenu option 2.
     * It too has loops, so again we write a sub function.
     * The smaller the merrier.
     */
    public void printStudentTotalPerCityPerYearWithMenuOptions(int year)
    {
        if (year == 4) {
            printCityTotalStudentsForYear(4);
        } else if (year == 5) {
            printCityTotalStudentsForYear(5);
        } else if (year == 6) {
            printCityTotalStudentsForYear(6);
        } else {
            System.out.println("Please select either 4, 5 or 6.");
        }
    }

    /**
     * this is printing, make sure you understand why there are so many functions for all of this.
     * - some only calculate
     * - some only print
     * - some are just different version of the same one, but kept in order to show the thought
     *   process. Feel free to combine/cleanup functions along the way.
     */
    public void printCityTotalStudentsForYear(int year) {
        for (String city : getTotalStudentsPerCity(year)) {
            System.out.println(city);
        }
    }

    /**
     * Alright, most of it done. But now we need to print graphics
     *  to the console.
     * Well, no worries. Because technically it's the same as a string.
     * A string is block of words, a csv is a block of columns.
     *
     * In the same way the display is a block of pixels.
     * And your graphic will be a block of units (circles I suppose?).
     *
     * So it works pretty much the same. I just don't remember exactly per what you
     * need to print it, so I assume for all years and all types per school.
     */
    public void printMaleFemaleStudentsPerSchool() {
        for (String school : getUniqueSchoolNames()) {
            System.out.print(school + ": ");
            printMaleStudentsPerSchool(school);
            printFemaleStudentsPerSchool(school);
            System.out.println();
        }
    }

   public void printMaleStudentsPerSchool(String school) {
        for (int i = 0; i < calculateMaleStudentsPerSchool(school); i++) {
            System.out.print("B");
        }
   }

   public void printFemaleStudentsPerSchool(String school) {
       for (int i = 0; i < calculateFemaleStudentsPerSchool(school); i++) {
           System.out.print("G");
       }
   }

    /**
     * Again we need something unique, so just copy and paste the code
     * from the previous methods like this.
     */
    public int calculateMaleStudentsPerSchool(String school) {
        ArrayList<Row> rows = readCSV();

        int count = 0;

        for (Row row : rows) {
            if (school.equals(row.Name)) {
               count += row.Male4 + row.Male5 + row.Male6;
            }
        }

        return count;
    }

    public int calculateFemaleStudentsPerSchool(String school) {
        ArrayList<Row> rows = readCSV();

        int count = 0;

        for (Row row : rows) {
            if (school.equals(row.Name)) {
                count += row.Female4 + row.Female5 + row.Female6;
            }
        }

        return count;
    }
}