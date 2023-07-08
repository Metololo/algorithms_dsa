/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class UnionFind {
    private int[] id;
    private int[] idsz;

    public UnionFind(int n) {
        this.id = new int[n];
        this.idsz = new int[n];

        for (int i = 0; i < n; ++i) {
            id[i] = i;
            idsz[i] = 1;
        }
    }

    private int root(int x) {
        while (id[x] != x) {
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }

    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);

        if (proot == qroot) return;
        if (idsz[proot] < idsz[qroot]) {
            id[proot] = id[qroot];
            idsz[qroot] += idsz[proot];
        }
        else {
            id[qroot] = id[proot];
            idsz[proot] += idsz[qroot];
        }
    }


}
