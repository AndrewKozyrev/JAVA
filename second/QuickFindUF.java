import java.util.Arrays;

public class QuickFindUF {
    private int[] id;

    QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;    
        }
    }
    public void union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++)
            if (id[i] == pid)
            {
                id[i] = qid;
            }
    }

    public boolean connected(int p, int q)
    {

        return id[p] == id[q];
    }
    
    public void display()
    {
        StdOut.println(Arrays.toString(id));
    }

    public static void main(String[] args) 
    {
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
        while ( !StdIn.isEmpty() )
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q))
            {
                uf.union(p, q);
                StdOut.println("\t(" + p + "; " + q + ")");
            }
        }

        uf.display();
    }
}