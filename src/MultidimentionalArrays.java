import java.util.Arrays;

public class MultidimentionalArrays {
    static void printTable(int[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void printTableAligned(int[][] a){
        int maxJ=0;
        for (int i = 0; i < a.length; i++) { //кол-во столбцов
            if (maxJ<a[i].length){maxJ=a[i].length;};
        }
        int[] b=new int[maxJ];
        for (int j = 0; j < maxJ; j++) { //длина самого длинного числа в столбцах
            for (int i = 0; i < a.length; i++) {
                try{
                    if (b[j]<String.valueOf(a[i][j]).length()){
                        b[j]=String.valueOf(a[i][j]).length();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
        String FormatLength="";
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (j!=0){
                    FormatLength="%"+(b[j]+2)+"d";
                }
                else{
                    FormatLength="%"+(b[j])+"d";
                }
                //System.out.println(FormatLength);
                System.out.printf(FormatLength,a[i][j]);
            }
            System.out.println();
        }
        //System.out.println(Arrays.toString(b));
    }
    static char[][] createTable(int i, char c) {
        char[][] a = new char[i][i];
        for (int j = 0; j < a.length; j++) {
            Arrays.fill(a[j], c);
        }
        //System.out.println(Arrays.toString(a));
        return a;
    }
    static void printTable(char[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void fillFirstAndLastLines(char[][] c, char c1) {
        Arrays.fill(c[0],0,c[0].length,c1);
        Arrays.fill(c[c[0].length-1],0,c[0].length,c1);
    }
    static void fillFirstAndLastColumns(char[][] c, char c1) {
        for (int i = 0; i < c.length; i++) {
            c[i][0]=c1;
            c[i][c.length-1]=c1;
        }
    }
    public static void main(String[] args){
        //int[][] a = new int[][]{
        //        {10, 1, 1000000, 44, 4244},                // a[0]
        //        {111, 432, 44},    // a[1]
        //        {5, 27, 1, 33, 123523, 6}                   // a[2]
        //};
        //printTableAligned(a);
        char[][] c = createTable(20, '.');
        printTable(c);

        System.out.println("============ Заполним строки: ==========");
        fillFirstAndLastLines(c, '#');
        printTable(c);

        System.out.println("============ Заполним столбцы: =========");
        fillFirstAndLastColumns(c, '#');
        printTable(c);
    }

}
