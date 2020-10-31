class Solution {
  /**
   * Dynamic Programming.
   *
   * Here we calculate the Longest Common Sub Sequence of `Xi` and `Yj` and store
   * it in `c[i][j]`.
   *
   * `Xi` indicates the prefix of `X` with length `i`. (Similarly for `Yj`).
   *
   * We know that the Longest Common Sub Sequence of two strings where any one of
   * them is empty is `0`. Thus we fill `c[i][0]` and `c[0][j]` with `0`.
   *
   * To calculate the value of rest of the table, there are the following cases:
   *
   * - If `x[i] == y[j]`. If the elements are equal, then we have found a match
   * and the value of `c[i][j]` should be `1` greater than that of the previous
   * Longest Sub Sequence which does not include the element `x[i] == y[j]` (i.e.
   * `c[i-1][j-1]`).
   *
   * - If `x[i] != y[j]`. If the elements are not equal, then the value of
   * `c[i][j]` is the maximum of the previous two longest Sub Sequence where one
   * includes the element `y[j]` (i.e. `c[i-1][j]`) and the other includes the
   * element `x[i]` (i.e. `c[i][j-1]`).
   *
   * The longest common sub sequence length is `c[n][m]`.
   *
   * We can then also compute the actual Sub Sequence from `c`.
   *
   * Time Complexity: O(n * m)
   *
   * Space Complexity: O(n * m)
   */
  public String longestCommonSubSeq(String x, String y) {
    int n = x.length();
    int m = y.length();

    int c[][] = new int[n + 1][m + 1];

    for (int i = 0; i <= n; i++)
      c[i][0] = 0;

    for (int i = 0; i <= m; i++)
      c[0][i] = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (x.charAt(i - 1) == y.charAt(j - 1)) {
          c[i][j] = 1 + c[i - 1][j - 1];
        } else {
          c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
        }
      }
    }

    int i = n, j = m;
    String longestSubSeq = "";

    // Complexity O(n + m)
    while (i > 0 && j > 0) {
      if (x.charAt(i - 1) == y.charAt(j - 1)) {
        longestSubSeq = x.charAt(i - 1) + longestSubSeq;
        i--;
        j--;
      } else if (c[i - 1][j] > c[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }

    return longestSubSeq;
  }
}
