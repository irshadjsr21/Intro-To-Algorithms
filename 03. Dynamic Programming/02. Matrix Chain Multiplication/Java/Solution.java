class Solution {
  /**
   * Dynamic Programming. (Bottom - Up)
   *
   * If we multiply `A1 A2` where the dimentions of `A1` and `A2` are `i * j` and
   * `j * k` respectively, then the total multiplications required will be `i * j
   * * k`.
   *
   * To solve this problem, we first divide it into sub problems. When we are
   * given a chain of matrix `A1, A2, A3 . . . An`, then we can mark a break
   * position (say `k`) which will indicate the paranthesis. So if `k=1`, then the
   * paranthesis will be as `(A1) (A2, A3, A4 ... An)` and if `k=3` then it will
   * be `(A1, A2 A3) (A4, A5 ... An)`. We can then calculate the multiplications
   * required to multiply the two matrix in paranthesis.
   *
   * We can further break down the matrix in the paranthesis individually, which
   * will give us the same problem but with smaller length.
   *
   * Now we have successfully divided the problem into sub problems.
   *
   * We now take a matrix (say `m[i][j]`) which will store the minimum
   * multiplications required to multiply the matrix from `i` to `j`.
   *
   * The index in `m` where `i` == `j` will be `0`, since no multiplications are
   * required if only 1 matrix is there.
   *
   * For the rest of the matrix, we first choose a length of the matrix chain (say
   * `l`). `l` will start from `2` end at `n`, since the minimum length of the
   * chain is `2` and we have already covered the length `1`.
   *
   * For each length we then choose a starting matrix (say `i`), and then we
   * calculate the end point of that chain (say `j`) which will give us the length
   * `l`. Then we look for all possible break points (say `k`) for which the
   * multiplications required are minimum.
   *
   * The total multiplications can be found as the sum of:
   *
   * - Multiplications required for multiplying the two parts of the matrix chain
   * which has `k` as it's breaking point. Eg: Multiplying `A1, A2, A3` with `A4,
   * A5, ... An`, if break point is `3`.
   *
   * - The minimum multiplications required for the 1st part. Eg: `A1, A2, A3`. We
   * can get this from the matrix `m`.
   *
   * - The minimum multiplications required for the 2nd part. Eg: `A4, A5, ...
   * An`. We can get this from the matrix `m`.
   * 
   * After this, `m[0,n]` will give us the minimum multiplications required to
   * multiply the chain.
   * 
   * To get the actual solution of where the paranthesis should be, we take
   * another matrix (say `s`) such that `s[i][j]` stores the break point (`k`) of
   * the matrix chain from `i` to `j`.
   *
   * Then we can recursively print the answer.
   * 
   * Time Complexity: O(n^3)
   *
   * Space Complexity: O(n^2)
   */
  public int matrixChain(int dimentions[]) {
    int n = dimentions.length - 1;
    int minMultiplications[][] = new int[n][n];
    int s[][] = new int[n][n];

    for (int i = 0; i < n; i++) {
      minMultiplications[i][i] = 0;
    }

    for (int l = 2; l <= n; l++) {
      for (int i = 0; i < n - l + 1; i++) {
        int minMul = Integer.MAX_VALUE;
        int j = i + l - 1;

        for (int k = i; k < j; k++) {
          int multiplications = (dimentions[i] * dimentions[k + 1] * dimentions[j + 1]) + minMultiplications[i][k]
              + minMultiplications[k + 1][j];

          if (multiplications < minMul) {
            minMul = multiplications;
            s[i][j] = k;
          }
        }

        minMultiplications[i][j] = minMul;
      }
    }

    System.out.println();
    printSol(s, 0, n - 1);
    System.out.println();
    System.out.println();
    return minMultiplications[0][n - 1];
  }

  public void printSol(int s[][], int i, int j) {
    if (i == j) {
      System.out.print("A" + (i + 1) + " ");
    } else {
      System.out.print("( ");
      printSol(s, i, s[i][j]);
      printSol(s, s[i][j] + 1, j);
      System.out.print(") ");
    }
  }
}
