public class CheckIsPrime {
    static int factoSum(int n) {
        int a = 2, sum = 0;
        while (n > 1) {
            while (n % a == 0) {
                sum += a;
                n /= a;
            }
            a++;
        }
        return sum;
    }
    static int solve(int n){
        while (n != factoSum(n))
        n = factoSum(n);
                return n;
    }
    public static void main(String[] args) {
        System.out.println(solve(24));
        System.out.println(solve(35));
        System.out.println(solve(156));
    }

}
