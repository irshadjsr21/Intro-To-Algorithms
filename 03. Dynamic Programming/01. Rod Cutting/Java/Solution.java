class Solution {
  /**
   * Divide And Conquere.
   *
   * This solution divides the problem into smaller problems.
   *
   * Here we have to find the maximum revenue generated for the rod of length `n`.
   * We cut the rod at all the possible positions (Eg: `1,n-1`, `2, n-2` etc.). We
   * can also cut the rod further than just 1 cut, so we will find the maximum
   * revenue generated when we cut the rod of length `n-1`, `n-2` etc.
   *
   * The maximum of all of the above cuts will be our answer.
   *
   * Hence we have divided the problem into smaller sub problems, which we can
   * write recursively.
   *
   * Complexity: O(2^n)
   */
  public int maxRevenueDC(int prices[]) {
    return recursiveCutRod(prices, prices.length);
  }

  public int recursiveCutRod(int prices[], int n) {
    if (n <= 0)
      return 0;

    int max = Integer.MIN_VALUE;

    for (int i = 1; i <= n; i++) {
      max = Math.max(max, prices[i - 1] + recursiveCutRod(prices, n - i));
    }

    return max;
  }

  /**
   * Dynamic Programming. (Top-Down)
   *
   * In the above solution, we compute the same sub problems again and again.
   *
   * To avoid this, now we store the computed value in an array `r`. When the
   * recursive problem is called, we see if we already have the computed result
   * stored in `r`. If so, then we return it, otherwise we compute the result and
   * store it in `r`.
   *
   * This is known as `Top-Down` approach to Dynamic Programming, since we solve
   * the topmost problem first and in the process we solve the sub problems.
   *
   * Time Complexity: O(n^2)
   *
   * Space Complexity: O(n)
   */
  public int maxRevenueDP(int prices[]) {
    int r[] = new int[prices.length];
    for (int i = 0; i < prices.length; i++)
      r[i] = Integer.MIN_VALUE;

    return recursiveCutRodMemorize(prices, r, prices.length);
  }

  public int recursiveCutRodMemorize(int prices[], int r[], int n) {
    if (n <= 0)
      return 0;

    if (r[n - 1] > 0)
      return r[n - 1];

    int max = Integer.MIN_VALUE;

    for (int i = 1; i <= n; i++) {
      max = Math.max(max, prices[i - 1] + recursiveCutRodMemorize(prices, r, n - i));
    }

    r[n - 1] = max;
    return max;
  }

  /**
   * Dynamic Programming. (Bottom - Up)
   *
   * This uses the same approach as above, but instead of using recursion, it is
   * implemented with loops.
   *
   * This is known as bottom-up Approach because the sub problems are solved from
   * smallest to largest.
   *
   * In this solution we also compute which cut's are to be made in order to get
   * the maximum revenue. For this, we store the cut required to get the maximum
   * revenue for the rod of length `i` in `s[i]`.
   * 
   * Time Complexity: O(n^2)
   *
   * Space Complexity: O(n)
   */
  public int maxRevenue(int prices[]) {
    int n = prices.length;
    int r[] = new int[n];
    int s[] = new int[n];

    for (int i = 0; i < n; i++) {
      int index = i + 1;

      int max = Integer.MIN_VALUE;
      for (int j = 1; j <= index; j++) {
        int remainingLen = index - j;
        int remainingPrice = remainingLen <= 0 ? 0 : r[remainingLen - 1];
        int newMax = prices[j - 1] + remainingPrice;

        if (newMax > max) {
          max = newMax;
          s[i] = j;
        }
      }

      r[i] = max;
    }

    int i = n - 1;
    System.out.println("Cut the rod in the following len");
    while (i >= 0) {
      System.out.print(s[i] + ", ");
      i -= s[i];
    }
    System.out.println();
    return r[n - 1];
  }
}
