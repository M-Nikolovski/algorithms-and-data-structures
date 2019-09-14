import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;


public class card_trick {
    private static void shuffleCards(Queue<Integer> cardsQueue) {
        LinkedList<Integer> firstSevenCardsReversed = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            firstSevenCardsReversed.addFirst(cardsQueue.remove());
        }

        for (int i = 0; i < 7; i++) {
            cardsQueue.add(firstSevenCardsReversed.remove());
            cardsQueue.add(cardsQueue.remove());
        }
    }

    private static int count(Integer N){
        // Vasiot kod tuka

        Queue<Integer> cardsQueue = new LinkedList<>();
        for (int i = 1; i <= 51; i++) {
            cardsQueue.add(i);
        }

        int count = 0;
        assert cardsQueue.peek() != null;
        while (!Objects.equals(cardsQueue.peek(), N)) {
            shuffleCards(cardsQueue);
            count++;
        }

        return count;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }
}