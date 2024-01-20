public class City {
    public String name;
    public int year4, year5, year6;

    public City(String name) {
        this.name = name;
    }

    public void print(int year) {
        if (year == 4) {
            System.out.println(name + ": " + year4);
        }

        if (year == 5) {
            System.out.println(name + ": " + year5);
        }

        if (year == 6) {
            System.out.println(name + ": " + year6);
        }
    }
}
