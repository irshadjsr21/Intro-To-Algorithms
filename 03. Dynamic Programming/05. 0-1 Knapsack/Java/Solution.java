import java.util.*;

class Solution {
  /**
   * Backtracking. (Brute Force)
   *
   * Since we have two options for each item (taking it, and not taking it), we
   * look at all the possible cases which can be generated and find the optimal
   * list for getting the maximum value in under the given weight `W`.
   *
   * Time Complexity: O(2^n)
   */
  public int maxValueBT(int v[], int w[], int W) {
    KnapsackItems finalItems = new KnapsackItems();
    KnapsackItems currentItems = new KnapsackItems();

    BT(0, v, w, W, currentItems, finalItems);

    System.out.println(finalItems.items);

    return finalItems.totalValue;
  }

  private class KnapsackItems {
    public List<Integer> items;
    public int totalWeight;
    public int totalValue;

    public KnapsackItems() {
      items = new ArrayList();
      totalWeight = 0;
    }

    public void add(int index, int value, int weight) {
      totalWeight += weight;
      totalValue += value;
      items.add(index);
    }

    public void poll(int value, int weight) {
      totalWeight -= weight;
      totalValue -= value;
      items.remove(items.size() - 1);
    }

    public void copy(KnapsackItems that) {
      this.items = new ArrayList(that.items);
      this.totalWeight = that.totalWeight;
      this.totalValue = that.totalValue;
    }
  }

  public void BT(int i, int v[], int w[], int W, KnapsackItems currentItems, KnapsackItems finalItems) {
    if (currentItems.totalWeight > W) {
      return;
    }

    if (currentItems.totalValue > finalItems.totalValue) {
      finalItems.copy(currentItems);
    }

    if (i >= v.length)
      return;

    currentItems.add(i, v[i], w[i]);

    BT(i + 1, v, w, W, currentItems, finalItems);

    currentItems.poll(v[i], w[i]);

    BT(i + 1, v, w, W, currentItems, finalItems);
  }

  /**
   * Dynamic Programming.
   *
   * Here we divide the problem into sub optimal problems. For each item, we have
   * a choice to select it or not.
   *
   * For this, we create a DP table (say `a`) of size `n * W`. Where the value of
   * `a[i][j]` stores the maximum value of the items we can take from `0 to i`
   * when we have a space of `j`. For example, if `a[2][3] = 10` then we conclude
   * that if we consider the items from `0` to `2` (i.e. `{ 1, 2 }`), and we have
   * `3` space in our knapsack, an optimal choice of the items will lead to the
   * maximum value of `10`.
   *
   * Note: Here index `i = 0` indicates no item and `j = 0` indicates no space in
   * the knapsack.
   *
   * From the above note, we can conclude that `a[0][j]` and `a[i][0]` will be
   * `0`. Because we cannot store any items if there aren't any and we cannot
   * store any item if there is no space.
   *
   * Then to calculate `a[i][j]`, we can make 2 choices. One is to inlcude the
   * item at `i` or not to include the item at `i`.
   *
   * If `w[i]` greater than `j`, meaning the weight of the current item is more
   * than the space we have in knapsack, we have no option but to skip the current
   * item.
   *
   * The two cases are given below:
   *
   * - If `w[i]` is less than or equal to `j`, then we choose the current item and
   * try to fill the remaining space in the knapsack. We can find the remaining
   * space by `r = j - w[i]` (say remaining space is `r`). Then we can fill this
   * remaining space by the items before `i`. The optimal value for this sub
   * problem will be already stored in `a[i - 1][r]`.
   *
   * - Till now we covered the case where the item is chosen to be selected, but
   * the case remains of if we do not select the item. For this we simply select
   * the previous items to fill the knapsack. The optimal value for this sub
   * problem will be already stored in `a[i - 1][j]`.
   *
   * We take the maximum of the above two values and store it in `a[i][j]`.
   * 
   * The value of `a[n][W]` will give us the optimal value to our main problem.
   *
   * To find the actual items which lead to the optimal solution, we check the if
   * the value of `a[n][W]` exists in the previous item row (i.e., `n - 1`) in the
   * same column (i.e. `W`). Essentially we are checking if the value of `a[n][W]`
   * was copied from the previous item row, which means the choice of not
   * selecting the item `n` lead to this optimal value.
   *
   * So it the two values are equal, we skip the item `n` and if they are
   * different we select the item `n`. After selecting we decrement `W` by the
   * weight of item `n` and look in the item `n - 1`. Essentially, it means that
   * we have now selected the item `n`, so we now have to find the choices we made
   * which gave us the optimal solution `a[n-1][W - w[n]]`.
   *
   * Time Complexity: O(n * W)
   *
   * Space Complexity: O(n * W)
   */
  public int maxValue(int v[], int w[], int W) {
    int n = v.length;
    int a[][] = new int[n + 1][W + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= W; j++) {
        int includedMax = 0;

        if (j - w[i - 1] >= 0) {
          includedMax = a[i - 1][j - w[i - 1]] + v[i - 1];
        }

        a[i][j] = Math.max(a[i - 1][j], includedMax);
      }
    }

    int weight = W;
    int currentItem = n;
    List<Integer> list = new ArrayList<>();

    // O(n)
    while (weight > 0 && currentItem > 0) {
      if (a[currentItem][weight] != a[currentItem - 1][weight]) {
        list.add(currentItem);
        weight -= w[currentItem - 1];
      }
      currentItem--;
    }

    System.out.println(list);

    return a[n][W];
  }
}
