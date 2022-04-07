import java.util.Arrays;

public class PrintableString implements Printable {

    String text;

    public PrintableString(String t) {
        text = t;
    }

    public void print() {
        System.out.println(text);
    }
}
