public class Matrix22 {
    private int a, b, c, d;

    public Matrix22(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Matrix22(int[][] array) {
        this.a = array[0][0];
        this.b = array[0][1];
        this.c = array[1][0];
        this.d = array[1][1];
    }

    public int getTrace() {
        return a + d;
    }

    public int getDiscriminant() {
        return a * d - b * c;
    }

    public int[][] getArray() {
        return new int[][]{{a, b}, {c, d}};
    }

    public String toString() {
        return a + ", " + b + ", " + c + ", " + d;
    }

    public void print() {
        MultidimentionalArrays.printTableAligned(this.getArray());
    }

    public Matrix22 add(Matrix22 b) {
        var aArray = this.getArray();
        var bArray = b.getArray();
        var newA = aArray[0][0] + bArray[0][0];
        var newB = aArray[0][1] + bArray[0][1];
        var newC = aArray[1][0] + bArray[1][0];
        var newD = aArray[1][1] + bArray[1][1];
        return new Matrix22(newA, newB, newC, newD);
    }

    public Matrix22 mul(Matrix22 b) {
        var aArray = this.getArray();
        var bArray = b.getArray();
        var newA = aArray[0][0] * bArray[0][0] + aArray[0][1] * bArray[1][0];
        var newB = aArray[0][0] * bArray[0][1] + aArray[0][1] * bArray[1][1];
        var newC = aArray[1][0] * bArray[0][0] + aArray[1][1] * bArray[1][0];
        var newD = aArray[1][0] * bArray[0][1] + aArray[1][1] * bArray[1][1];
        return new Matrix22(newA, newB, newC, newD);
    }
    public Matrix22 pow(int n) {
        var m = this;
        for (int i = 1; i < n; i++) {
            m=m.mul(m);
        }
        return m;
    }

}
