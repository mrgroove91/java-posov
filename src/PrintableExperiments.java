public class PrintableExperiments {
    public static void main(String[] args) {
        PrintableLetter pl = new PrintableLetter("x", 10);
        pl.print(); //печатает букву x 10 раз

        PrintableString ps = new PrintableString("asdf");
        ps.print(); //печатает asdf

        Drawing house = new Drawing(10, 20, '-');
        house.drawRectangle(2, 0, 17, 6, 'H');
        house.drawLine(2, 7, 9, 9, 'x');
        house.drawLine(9, 9, 17, 7, 'x');
        house.drawRectangle(11, 2, 13, 4, 'o');

        Printable cow = new Printable() {
            @Override
            public void print() {
                System.out.println("Я корова, потому что произошла из примера анонимного класса, связанного с животными");
            }
        };

        // объявление ссылки на MyInterface
        Printable ref;

        // лямбда-выражение
        ref = () -> house.print();
        Printable[] listik = {house, pl, ps, cow, ref};
        for (int i = 0; i < listik.length; i++) {
            listik[i].print();
        }
    }
}
