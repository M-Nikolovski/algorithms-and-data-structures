import java.util.Arrays;
import java.util.Scanner;


public class LDS {

    private static int najdolgaOpagackaSekvenca(int[] a) {
        // Vashiot kod tuka

        int arrayLength = a.length;
        int[] lengthsArray = new int[arrayLength];
        Arrays.fill(lengthsArray, 1);

        for (int i = 1; i < arrayLength ; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j] && lengthsArray[i] <= lengthsArray[j]) {
                    lengthsArray[i] = lengthsArray[j] + 1;
                }
            }
        }

        Arrays.sort(lengthsArray);

        return lengthsArray[arrayLength - 1];
    }


    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }
}