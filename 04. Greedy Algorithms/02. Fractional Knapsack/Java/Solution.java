import java.util.*;

class Solution {
  /**
   * Greedy Algorithm.
   *
   * The main idea is to make a greedy choice by selecting the item which has the
   * maximum `value` to `weight` ratio.
   *
   * To solve this problem we first calculate the ratio of `value` and `weight` of
   * all the items, and then sort it in descending order.
   *
   * Now we just have select all the items from the beginning till we have no
   * space left.
   *
   * Time complexity will be `O(n log(n) + n)`, which comprises of sorting the
   * items `O(n log(n))`, and filling up the knapsack `O(n)`
   *
   * Complexity: O(n log(n))
   */
  public double maxValue(int[] v, int[] w, int W) {
    int n = v.length;
    KnapsackItem[] items = new KnapsackItem[n];

    for (int i = 0; i < n; i++) {
      items[i] = new KnapsackItem(i, v[i], w[i]);
    }

    Arrays.sort(items, new KnapsackItemComparator());

    double totalValue = 0;
    int currentIndex = 0;

    while (W > 0) {
      if (items[currentIndex].w >= W) {
        totalValue += W * items[currentIndex].ratio;
        System.out.println("Selecting " + W + " pounds of item no. " + items[currentIndex].index + " of value "
            + (W * items[currentIndex].ratio));
        W = 0;
      } else {
        totalValue += items[currentIndex].v;
        W -= items[currentIndex].w;
        System.out.println("Selecting " + items[currentIndex].w + " pounds of item no. " + items[currentIndex].index
            + " of value " + items[currentIndex].v);
        currentIndex++;
      }
    }

    return totalValue;
  }

  private class KnapsackItem {
    public int index;
    public int v;
    public int w;
    public double ratio;

    public KnapsackItem(int index, int v, int w) {
      super();
      this.index = index;
      this.v = v;
      this.w = w;
      this.ratio = v / (double) w;
    }

  }

  private class KnapsackItemComparator implements Comparator<KnapsackItem> {

    @Override
    public int compare(KnapsackItem a, KnapsackItem b) {
      return Double.compare(b.ratio, a.ratio);
    }
  }

}
