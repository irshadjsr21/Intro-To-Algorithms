import java.io.*;

class SolutionExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    int n = Integer.parseInt(br.readLine());
    double p[] = new double[n + 1];
    double q[] = new double[n + 1];
    p[0] = 0;
    for (int i = 1; i <= n; i++)
      p[i] = Double.parseDouble(br.readLine());

    for (int i = 0; i <= n; i++)
      q[i] = Double.parseDouble(br.readLine());

    br.close();

    Solution sol = new Solution();
    System.out.println(sol.optimalBST(p, q));
  }
}
