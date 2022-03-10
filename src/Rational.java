import java.util.Arrays;

public class Rational {
    private int n = 1;
    private int d = 1;
    static Rational ONE = new Rational(1);
    static Rational ZERO = new Rational(0);

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public Rational(int _N) {
        this.n = _N;
    }

    public int getD() {
        return this.d;
    }

    public int getN() {
        return this.n;
    }

    public Rational(int _N, int _D) {
        this.n = _N / (gcd(_N, _D));
        this.d = _D / (gcd(_N, _D));
        if (this.d < 0) {
            if (this.n < 0) {
                this.n = Math.abs(this.n);
            } else {
                this.n = -this.n;
            }
            this.d = Math.abs(_D);
        }
    }

    public Rational add(Rational other) {
        return new Rational((this.n * other.d) + (other.n * this.d), this.d * other.d);
    }

    public Rational sub(Rational other) {
        return new Rational((this.n * other.d) - (other.n * this.d), this.d * other.d);
    }

    public Rational mul(Rational other) {
        return new Rational(this.n * other.n, this.d * other.d);
    }


    public Rational div(Rational other) {
        return new Rational(this.n * other.d, this.d * other.n);
    }

    static Rational add(Rational self, Rational other) {
        return new Rational((self.n * other.d) + (other.n * self.d), self.d * other.d);
    }

    static Rational sub(Rational self, Rational other) {
        return new Rational((self.n * other.d) - (other.n * self.d), self.d * other.d);
    }

    static Rational mul(Rational self, Rational other) {
        return new Rational(self.n * other.n, self.d * other.d);
    }


    static Rational div(Rational self, Rational other) {
        return new Rational(self.n * other.d, self.d * other.n);
    }

    /*
        public void addInPlace(Rational other) {
            this.n = (this.n * other.d) + (other.n * this.d);
            this.d = this.d * other.d;
        }

        public void subInPlace(Rational other) {
            this.n = (this.n * other.d) - (other.n * this.d);
            this.d = this.d * other.d;
        }

        public void mulInPlace(Rational other) {
            this.n = this.n * other.n;
            this.d = this.d * other.d;
        }

        public void divInPlace(Rational other) {
            this.n = this.n * other.d;
            this.d = this.d * other.n;
        }
    */
    public boolean equals(Rational other) {
        return (this.n == other.n) && (this.d == other.d);
    }

    public String toString() {
        if (this.d == 1) {
            return String.valueOf(this.d);
        } else if (this.n == 0) {
            return String.valueOf(this.n);
        } else {
            return this.n + "/" + this.d;
        }
    }

    double toDouble() {
        return (double) this.n / (double) this.d;
    }


}
