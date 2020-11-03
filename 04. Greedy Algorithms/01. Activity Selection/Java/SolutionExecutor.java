import java.io.*;

class SolutionExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    int cap = Integer.parseInt(br.readLine());
    int s[] = new int[cap];
    int f[] = new int[cap];

    for (int i = 0; i < cap; i++)
      s[i] = Integer.parseInt(br.readLine());

    for (int i = 0; i < cap; i++)
      f[i] = Integer.parseInt(br.readLine());

    br.close();

    Solution sol = new Solution();
    System.out.println(sol.maxActivities(s, f));
  }
}
