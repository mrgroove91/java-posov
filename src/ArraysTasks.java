import java.util.Arrays;

class ArraysTasks {
    static int[] even(int n){
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i]=(i+1)*2;
        }
        return a;
    }
    static boolean allSimilar(int[] a){
        boolean isSimilar=true;
        if (a.length>1) {
            for (int i = 1; i < a.length; i++) {
                if (a[i] != a[i - 1]) {
                    isSimilar = false;
                    break;
                }
            }
        }
        return isSimilar;
    }
    static boolean hasSimilar(int[] a){
        boolean has=false;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if ((a[i] == a[j]) && (i != j)) {
                    has = true;
                    break;
                }
            }
        }
        return has;
    }
    static double mean(int[] a){
        double b = 0;
        for (int j : a) {
            b += j;
        }
        return b/a.length;
    }
    static void shift(int[] a){
        int last=a[a.length-1];
        for (int i = a.length-2; i >= 0; i--) {
            a[i+1] = a[i];
        }
        a[0]=last;
        //System.out.println(Arrays.toString(a));
    }
    static int[] copyShift(int[] a){
        int[] b=new int[a.length];
        int last=a[a.length-1];
        for (int i = a.length-2; i >= 0; i--) {
            b[i+1] = a[i];
        }
        b[0]=last;
        return b;
    }
    public static void main(String[] args){
        //System.out.println(Arrays.toString(even(5)));

        //System.out.println(allSimilar(new int[]{1,2}));
        //System.out.println(hasSimilar(new int[]{2}));
        //System.out.println(mean(new int[]{3,4,5}));

        //int[] a = new int[]{0,1,2,3};
        //shift(a);
        //System.out.println(Arrays.toString(a));

        //int[] a = new int[]{0,1,2,3};
        //System.out.println(Arrays.toString(copyShift(a)));
        //System.out.println(Arrays.toString(a));
    }
}
