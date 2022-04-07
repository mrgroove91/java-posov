public class PrintableLetter implements Printable {

    String letter;
    int number;

    public PrintableLetter(String t, int n) {
        letter = t;
        number = n;
    }

    public void print() {
        System.out.println(letter.repeat(number));

    }
}
