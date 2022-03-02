public class ExperimentsWithTime {
    public static void main(String[] args) {
        Time utro = new Time(9, 20);
        System.out.println(utro.show());
        utro.add(120);
        System.out.println(utro.say_hello());
        System.out.println(utro.show());

        //тут же эксперименты с Drawing, чтобы в одном месте

        Drawing house = new Drawing(10, 20, '-');
        house.drawRectangle(2, 0, 17, 6, 'H');
        house.drawLine(2, 7, 9, 9, 'x');
        house.drawLine(9, 9, 17, 7, 'x');
        house.drawRectangle(11, 2, 13, 4, 'o');
        house.print();

        Drawing a = new Drawing(15, 30, '.');
        a.draw(2,3,house);
        a.print();
    }
}
