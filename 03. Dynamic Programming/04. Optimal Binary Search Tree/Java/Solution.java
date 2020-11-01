class Solution {
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
