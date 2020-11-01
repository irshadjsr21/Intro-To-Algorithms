class Solution {
  /**
   * Dynamic Programming.
   *
   * In this solution, we store the total probability of all the elements from
   * (`k[i]`, `k[j]`] and all the `dummy elements` in between in `w[i][j]`.
   *
   *
   * We store the minimum cost of searching in the tree from (`k[i], k[j]`] and
   * all the `dummy elements` in between in `c[i][j]`.
   *
   * We store the root of the tree from (`k[i], k[j]`] in `r[i][j]`.
   *
   * We first fill the diagonal of `w` and `c` where `i == j` to `q[i]`. This
   * indicates the trees with `0` nodes.
   *
   * Then we start filling the next upper diagonals which will indicate the trees
   * with `1, 2, 3 ... n` length.
   *
   * We can find the value of `w[i][j]` by adding `p[j] + q[j]` to `w[i][j-1]`.
   * This logic behind this formula is that the sum of probabilities of nodes from
   * `i` to `j` will be the sum of probabilities of nodes from `i` to `j - 1` plus
   * the probability of the `j`th node and the probability of `dummy element`
   * after `j`.
   *
   * To find the `c[i][j]`, we choose a root (say `k`) such that `k is greater
   * than i` and `k is less than or equal to j`. Then we find the minimum cost of
   * searching of the two subtrees of the root `k`, which will be `c[i][k-1]` and
   * `c[k][j]` respectively. We choose a `k` such that the sum of minimum of cost
   * of searching in the two subtrees are minimum. Then we add `w[i][j]` to the
   * computed sum which gives us the minimum cost of searching for `c[i][j]`.
   *
   * We also store the root `k` in `r[i][j]` so that the optimal binary search
   * tree can be easily computed.
   *
   * The value of `c[0][n]` gives the minimum cost of searching.
   *
   * Time Complexity: O(n^3)
   *
   * Space Complexity: O(n^2)
   */
  public double optimalBST(double p[], double q[]) {
    int n = p.length - 1;
    double w[][] = new double[n + 1][n + 1];
    double c[][] = new double[n + 1][n + 1];
    int r[][] = new int[n + 1][n + 1];

    for (int i = 0; i <= n; i++) {
      w[i][i] = q[i];
      c[i][i] = q[i];
    }

    for (int l = 1; l <= n; l++) {

      for (int i = 0; i <= n - l; i++) {
        int j = i + l;
        w[i][j] = w[i][j - 1] + p[j] + q[j];
        c[i][j] = Integer.MAX_VALUE;
        for (int k = i + 1; k <= j; k++) {
          double newCost = c[i][k - 1] + c[k][j];

          if (newCost < c[i][j]) {
            c[i][j] = newCost;
            r[i][j] = k;
          }
        }

        c[i][j] += w[i][j];
      }
    }

    printBST(r, 0, n, 1);

    return c[0][n];
  }

  public void printBST(int r[][], int i, int j, int level) {
    if (i >= 0 && j < r.length && r[i][j] != 0) {
      System.out.println("Level " + level + " contains k[" + r[i][j] + "]");
      printBST(r, i, r[i][j] - 1, level + 1);
      printBST(r, r[i][j], j, level + 1);
    }
  }
}
