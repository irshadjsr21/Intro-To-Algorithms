import java.io.*;

/**
 * Executes the CountSort algorithm for the given inputs in `input.txt` file.
 */
class CountSortExecutor {
  /**
   * Internal method to print the array.
   */
  private static void printArray(int size, int[] arr) {
    for (int i = 0; i < size; i++) {
      System.out.println(arr[i]);
    }
    System.out.println();
  }

  /**
   * Internal method to implement count sort.
   *
   * Complexity: O(n^2)
   */
  private static void countSort(int size, int[] arr) {
    int countArr[] = new int[size];
    int newArr[] = new int[size];

    for (int i = 0; i < size; i++)
      countArr[i] = 0;

    for (int i = 0; i < size; i++) {
      countArr[arr[i]]++;
    }

    for (int i = 1; i < size; i++) {
      countArr[i] += countArr[i - 1];
    }

    for (int i = size - 1; i >= 0; i--) {
      newArr[--countArr[arr[i]]] = arr[i];
    }

    printArray(size, newArr);
  }

  /**
   * The main method reads the input file, create the array and passes it to the
   * sort method.
   */
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String capStr = br.readLine();

    int cap = Integer.parseInt(capStr);
    int arr[] = new int[cap];
    String line = null;
    int index = 0;
    while ((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if (lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        if (c > cap || c < 0) {
          throw new Error("Array element should be in the range 0 - (n - 1).");
        }
        arr[index++] = c;
      }
    }

    br.close();
    countSort(cap, arr);
  }
}
