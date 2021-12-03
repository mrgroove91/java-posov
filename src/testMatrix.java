import java.util.Arrays;

public class testMatrix {
    public static void main(String[] args) {
        Matrix22 a = new Matrix22(123, 2, -3, -456);
        Matrix22 b = new Matrix22(1, 2, 3, 4);
        Matrix22 c=b.pow(2);
        c.getArray()[0]= new int[]{22, 33}; //ничего страшного
        System.out.println(b.toString());
        c.print();
    }

}
