public class ExperimentsWithRational {
    public static double f(int n){
        Rational s = new Rational(0);
        for (int i = 1; i < n + 1; i++) {
            s=s.add(new Rational(1, i));
        }
        return s.toDouble();
    }
    public static void main(String[] args) {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 3);
        Rational r3 = r1.add(r2);
        Rational r4 = new Rational(21, 18);
        Rational r5 = new Rational(1, 6);

        System.out.println(r3.toString()); // 1/2 + 1/3 = 5/6
        //r2.addInPlace(r3); // r2 = 1/3 + (1/2 + 1/3) = 21/18
        System.out.println(r2.toString()); // 1/3 + (1/2 + 1/3) = 21/18

        System.out.println(r4.toString()); // 21/18 = 7/6

        Rational r6 = new Rational(1, 3);
        System.out.println(r6.mul(r1).equals(r5)); // 1/3 * 1/2 = 1/6
        System.out.println(f(3));
    }
}
