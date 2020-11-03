import java.util.*;

class Solution {
  /**
   * Greedy Algorithm.
   *
   * Here we have to find a set of non overlapping (or compatable) activities in
   * the given set `S` which has maximum length.
   *
   * We can divide the problem into sub problems and solve it through Dynamic
   * Programming. The division of the problem will be as follows.
   *
   * We will find an activity (say `am`) in the given set `S[0][n]` which we
   * assume will be present in the maximum compatable set (say `k[0][n]`). Once we
   * find this activity, then we have to find the maximum compatable set for the
   * activity before and after `am`, and that will be `k[0][m-1]` and `k[m+1][n]`.
   *
   * Now that we have successfully divided the problem into sub problems, we can
   * solve it through Dynamic Programming which will have a Space Complexity of
   * O(n^2) and Time Complexity of O(n^2).
   *
   * But here a better approach will be making a greedy choice when selecting the
   * `am` activity. The idea here is to make the maximum set of compatable
   * activity, we can select the activity which finishes first and by doing so we
   * can save time for selecting more activities.
   *
   * Since the given activies are already sorted, we can automatically select the
   * first activity, and then look for the next compatable activity. We do this
   * until we gone through all the activities.
   *
   * This gives ius the Maximum Set of Compatable Activities.
   *
   * Complexity: O(n)
   */
  public List<Integer> maxActivities(int[] s, int[] f) {
    List<Integer> list = new ArrayList<Integer>();

    int n = s.length;

    if (n < 1)
      return list;

    int currentAct = 0;
    list.add(1);

    for (int i = 1; i < n; i++) {
      if (s[i] >= f[currentAct]) {
        list.add(i + 1);
        currentAct = i;
      }
    }

    return list;
  }
}
