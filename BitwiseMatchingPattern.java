public class BitwiseMatchingPattern {

    public static int nextWithSameOnes(int n) {
        int c = n;
        int c0 = 0, c1 = 0;

        while ((c & 1) == 0 && c != 0) {
            c0++;
            c >>= 1;
        }

        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        if (c0 + c1 == 31 || c0 + c1 == 0)
            return -1;

        int p = c0 + c1;
        n |= (1 << p);
        n &= ~((1 << p) - 1);
        n |= (1 << (c1 - 1)) - 1;

        return n;
    }

    public static void main(String[] args) {
        int n = 13948;
        int next = nextWithSameOnes(n);
        System.out.println("Input: " + n + " (Binary: " + Integer.toBinaryString(n) + ")");
        System.out.println("Output: " + next + " (Binary: " + Integer.toBinaryString(next) + ")");
    }
}
