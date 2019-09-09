import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Range {

    private static long sumOfDigits(long number) {
        long sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private static long calculateEquation(long x) {
        return x * x + sumOfDigits(x) + 200 * x;
    }

    private static long proveri(long N, long A, long B) {

        // Vasiot kod tuka

        if (calculateEquation(A) == N)
            return A;
        if (calculateEquation(B) == N)
            return B;

        if (B - A == 1) {
            return -1;
        }

        long middleNumber = (A + B) / 2;
        long middleNumberEquationResult = calculateEquation(middleNumber);
        if (middleNumberEquationResult == N)
            return middleNumber;
        else if (middleNumberEquationResult > N)
            return proveri(N, A, middleNumber);
        else
            return proveri(N, middleNumber, B);

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long res = proveri(N, A, B);
        System.out.println(res);

        br.close();
    }
}