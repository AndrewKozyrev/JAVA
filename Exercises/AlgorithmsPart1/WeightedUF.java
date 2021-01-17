import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WeightedUF {
    private int[] id;
    private int[] sz;
    WeightedUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        Arrays.fill(sz, 1);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j])  { id[i] = j; sz[j] += sz[i]; }
        else                {id[j] = i; sz[i] += sz[j]; }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public String display() {
        String tree = new String("\n");
        List<Integer> tree_roots = new ArrayList<Integer>();
        for (int i = 0; i < id.length; i++) {
            // find all roots
            if (!tree_roots.contains(root(i))) {
                tree_roots.add(root(i));
            }
        }

        // find all terminal nodes
        for (int k : tree_roots) {
            tree += "\t" + k + "\n";
            List<String> temp1 = new ArrayList<String>();
            for (int j = 0; j < id.length; j++) {
                if (root(j) == k && !tree.contains(showPath(j))) {
                    temp1.add(showPath(j));
                }
            }

            List<String> temp2 = new ArrayList<String>(temp1);

            for (String e : temp2) {
                temp2.forEach(alpha -> {
                    if (e.contains(alpha) && !e.equals(alpha))
                        temp1.remove(alpha);
                });
            }

            for (String f : temp1) {
                tree += f + "\n";
            }
        }
        StdOut.println(tree);
        return tree;
    }

    public String showPath(int k) {
        int index = k;
        String path = Integer.toString(index);
        while (index != id[index]) {
            index = id[index];
            path += " -> " + Integer.toString(index);
        }

        return path;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedUF uf = new WeightedUF(N);
        StdOut.println("Populate randomly?: \n");
        String option = StdIn.readString();

        if (!option.equals("Yes"))
        {
                while (!StdIn.isEmpty())
            {
                int p = StdIn.readInt();
                int q = StdIn.readInt();
                if (!uf.connected(p, q))
                {
                    uf.union(p, q);
                    StdOut.println("\t(" + p + "; " + q + ")");
                }
            }
        }

        else if (option.equals("Yes"))
        {
            Random rnd = new Random();
            for (int i = 0; i < uf.id.length; i++)
            {
                int p = rnd.nextInt(uf.id.length);
                int q = rnd.nextInt(uf.id.length);
                if (!uf.connected(p, q)) {
                    uf.union(p, q); 
                    //StdOut.println("\t(" + p + "; " + q + ")");
                }
            }
        }

        uf.display();
    }
} 