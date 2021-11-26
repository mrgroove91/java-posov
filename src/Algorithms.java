import java.util.ArrayList;
import java.util.Arrays;

class Algorithms {
    static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, Boolean.TRUE);
        isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i])
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
        }
        return isPrime;
    }

    static int[] primes(int n) {
        boolean[] isPrime = sieveOfEratosthenes(n);
        int j = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                j += 1;
            }
        }
        int k = 0;
        int[] primeNumbers = new int[j];
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primeNumbers[k++] = i;
            }
        }
        return primeNumbers;
    }

    static ArrayList<int[]> factorOut(int n) {
        ArrayList<int[]> factorOutList = new ArrayList<>();
        int[] primeNumbers = primes(n);
        int k = 0;
        for (int d : primeNumbers) {
            while (d * d <= n) {
                //System.out.println("n:"+n+" d:"+d);
                while (n % d == 0) {
                    n /= d;
                    k += 1;
                }
                if (k != 0) {
                    int[] a = new int[]{d, k};
                    factorOutList.add(a);
                    //System.out.println(Arrays.toString(a));
                    k = 0;
                }
                break;
            }
        }
        if (n != 1) {
            int[] a = new int[]{n, 1};
            factorOutList.add(a);
            //System.out.println("у нас остался лишь n "+Arrays.toString(a));
        }

        return factorOutList;
    }

    public static void main(String[] args) {
        //int[] a=primes(13);
        //System.out.println(Arrays.toString(a));
        ArrayList<int[]> test = factorOut(600);
        System.out.println("итого:");
        System.out.println(Arrays.deepToString(test.toArray()));
    }
}
