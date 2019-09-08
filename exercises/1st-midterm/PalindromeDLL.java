import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return "<-" + element.toString() + "->";
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

}

public class PalindromeDLL {

    public static int isItPalindrome(DLL<Integer> list){
        // Variant 1
        DLLNode<Integer> front = list.getFirst();
        DLLNode<Integer> back = list.getLast();
        while (front != null && back != null) {
            System.out.println("front: " + front);
            System.out.println("back: " + back);
            if (!front.element.equals(back.element))
                return -1;

            // to stop checking if it reaches the middle of the list
            // for lists with even number of elements
            if (front.pred != null && front.pred.equals(back))
                return 1;
            // to stop checking if it reaches the middle of the list
            // for lists with odd number of elements
            if (front.equals(back))
                return 1;
            front = front.succ;
            back = back.pred;
        }
        return 1;

        // Variant 2
//        StringBuilder sb = new StringBuilder();
//        DLLNode<Integer> tmp = list.getFirst();
//        while (tmp != null) {
//            sb.append(Character.toChars(tmp.element));
//            tmp = tmp.succ;
//        }
//        if (sb.toString().equals(sb.reverse().toString()))
//            return 1;
//        else return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        in.close();
        System.out.println(isItPalindrome(list));
    }

}
