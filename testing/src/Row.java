public class Row {
    String name, city , type;
    int male, female, male4, female4, male5, female5, male6, female6;

    public Row(String[] values) {
        this.name = values[0];
        this.city = values[1];
        this.type = values[2];
        this.male4 = Integer.parseInt(values[3]);
        this.female4 = Integer.parseInt(values[4]);
        this.male5 = Integer.parseInt(values[5]);
        this.female5 = Integer.parseInt(values[6]);
        this.male6 = Integer.parseInt(values[7]);
        this.female6 = Integer.parseInt(values[8]);

        this.male = this.male4 + this.male5 + this.male6;
        this.female = this.female4 + this.female5 + this.female6;
    }
}
