import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;


class SLLNode {
    protected int id;
    protected int plata;
    protected SLLNode succ;

    public SLLNode(int id,int plata, SLLNode succ) {
        this.id = id;
        this.plata=plata;
        this.succ = succ;
    }
}

class SLL {
    private SLLNode first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void insertFirst(int id, int plata) {
        SLLNode ins = new SLLNode(id,plata, first);
        first = ins;
    }

    public void insertLast(int id,int plata) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, plata, null);
            tmp.succ = ins;
        } else {
            insertFirst(id,plata);
        }
    }

    public void swap(SLLNode predNode1, SLLNode node1, SLLNode predNode2, SLLNode node2) {
        SLLNode node1Succ = node1.succ;

        node1.succ = node2.succ;

        if (predNode1 != null) {
            predNode1.succ = node2;
        } else {
            first = node2;
        }

        // if the elements are not next to each other
        if (node1Succ != node2) {
            node2.succ = node1Succ;
            predNode2.succ = node1;
        } else {
            node2.succ = node1;
        }
    }


    public SLL brisi_pomali_od(int iznos) {
        // Vasiot kod tuka

        SLLNode tmp = first;
        SLLNode previousTmp = first;

        while (tmp != null) {
            if (tmp.plata < iznos) {
                if (tmp == first) {
                    first = tmp.succ;
                    tmp = previousTmp = first;
                } else {
                    previousTmp.succ = tmp.succ;
                    tmp = tmp.succ;
                }
            } else {
                previousTmp = tmp;
                tmp = tmp.succ;
            }
        }
        return this.first != null ? this : null;
    }

    public SLL sortiraj_opagacki() {
        // Vasiot kod tuka

        SLLNode outerTmp = this.first;
        SLLNode predOuterTmp = null;

        while (outerTmp.succ != null) {

            SLLNode innerTmp = outerTmp.succ;
            SLLNode predInnerTmp = outerTmp;

            while (innerTmp != null) {
                if (innerTmp.id > outerTmp.id) {
                    swap(predOuterTmp, outerTmp, predInnerTmp, innerTmp);

                    SLLNode tmpInnerTmp = innerTmp;

                    predInnerTmp = outerTmp;
                    innerTmp = outerTmp.succ;

                    outerTmp = tmpInnerTmp;

                } else {
                    predInnerTmp = innerTmp;
                    innerTmp = innerTmp.succ;
                }
            }
            predOuterTmp = outerTmp;
            outerTmp = outerTmp.succ;
        }
        return this;
    }

    public void pecati (SLL lista)
    {
        SLLNode p=lista.first;
        while(p!=null)
        {
            System.out.println(p.id+" "+p.plata);
            p=p.succ;
        }
    }
}

public class SLLKompanija {
    public static void main(String[] args) throws IOException {

        SLL lista1 = new SLL();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        for (int i = 0; i < N; i++) {
            s=stdin.readLine();
            String s1=stdin.readLine();
            lista1.insertLast(Integer.parseInt(s),Integer.parseInt(s1));
        }
        s = stdin.readLine();

        lista1=lista1.brisi_pomali_od(Integer.parseInt(s));
        if(lista1!=null) {
            lista1=lista1.sortiraj_opagacki();
            lista1.pecati(lista1);
        } else {
            System.out.println("nema");
        }

    }
}