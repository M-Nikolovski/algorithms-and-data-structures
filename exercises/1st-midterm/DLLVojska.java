import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        return "<-"+element.toString()+"->";
    }
}


class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void setFirst(DLLNode<E> first) {
        this.first = first;
    }

    public void setLast(DLLNode<E> last) {
        this.last = last;
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


public class DLLVojska {

        public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] ids = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(ids[i]));
        }

        s = stdin.readLine();
        String interval[] = s.split(" ");
        int a = Integer.parseInt(interval[0]);
        int b = Integer.parseInt(interval[1]);

        s = stdin.readLine();
        interval = s.split(" ");
        int c = Integer.parseInt(interval[0]);
        int d = Integer.parseInt(interval[1]);


        DLL<Integer> result = vojska(lista, a, b, c, d);


        DLLNode<Integer> node = result.getFirst();
        System.out.print(node.element);
        node = node.succ;
        while(node != null){
            System.out.print(" "+node.element);
            node = node.succ;
        }

    }

    private static DLL<Integer> vojska(DLL<Integer> lista, int a, int b, int c, int d) {

        // Vasiot kod tuka

        DLLNode<Integer> temp;
        temp = lista.getFirst();

        DLLNode<Integer> aNode;
        DLLNode<Integer> bNode;
        DLLNode<Integer> cNode;
        DLLNode<Integer> dNode;

        while (!temp.element.equals(a)) {
            temp = temp.succ;
        }
        aNode = temp;

        while (!temp.element.equals(b)) {
            temp = temp.succ;
        }
        bNode = temp;

        while (!temp.element.equals(c)) {
            temp = temp.succ;
        }
        cNode = temp;

        while (!temp.element.equals(d)) {
            temp = temp.succ;
        }
        dNode = temp;


        DLLNode<Integer> aPred = aNode.pred;
        DLLNode<Integer> bSucc = bNode.succ;
        boolean listsNextToEachOther = bSucc.equals(cNode);
        boolean firstListOnBeginning = aPred == null;
        boolean secondListOnEnd = dNode.succ == null;

        if (!firstListOnBeginning) {
            aNode.pred.succ = cNode;
        } else {
            lista.setFirst(cNode);
        }
        if (listsNextToEachOther) {
            aNode.pred = dNode;
        } else {
            aNode.pred = cNode.pred;
            cNode.pred.succ = aNode;
            bNode.succ.pred = dNode;
        }
        cNode.pred = aPred;
        bNode.succ = dNode.succ;
        if (!secondListOnEnd) {
            dNode.succ.pred = bNode;
        } else {
            lista.setLast(dNode);
        }
        if (listsNextToEachOther) {
            dNode.succ = aNode;
        } else {
            dNode.succ = bSucc;
        }

        return lista;
    }
}