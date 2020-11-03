import java.io.*;

class SolutionExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    int cap = Integer.parseInt(br.readLine());
    int n = Integer.parseInt(br.readLine());
    int v[] = new int[n];
    int w[] = new int[n];

    for (int i = 0; i < n; i++)
      v[i] = Integer.parseInt(br.readLine());

    for (int i = 0; i < n; i++)
      w[i] = Integer.parseInt(br.readLine());

    br.close();

    Solution sol = new Solution();
    System.out.println(sol.maxValue(v, w, cap));
  }
}
