import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    private static long calculateMaxPrice(int numberOfAdults, int numberOfChildren, int ticketPrice) {
        long price;
        price = (numberOfAdults + (numberOfChildren > 0 ? numberOfChildren - 1 : 0)) * ticketPrice;
        return price;
    }

    private static long calculateMinPrice(int numberOfAdults, int numberOfChildren, int ticketPrice) {
        if (numberOfAdults > numberOfChildren)
            return numberOfAdults * ticketPrice;
        else
            return numberOfChildren * ticketPrice;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        System.out.println(calculateMinPrice(N, M, 100));
        System.out.println(calculateMaxPrice(N, M, 100));

    }
}