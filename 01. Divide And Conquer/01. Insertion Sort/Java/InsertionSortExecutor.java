import java.io.*;

/**
 * Executes the InsertionSort algorithm for the given inputs in `input.txt`
 * file.
 */
class InsertionSortExecutor {
  /**
   * Internal method to print the array.
   */
  private static void printArray(int size, int[] arr) {
    for (int i = 0; i < size; i++) {
      System.out.println(arr[i]);
    }
  }

  /**
   * Internal method to implement insertion sort.
   *
   * Complexity: O(n^2)
   */
  private static void sort(int size, int[] arr) {
    for (int i = 1; i < size; i++) {
      int j = i - 1;
      int index = i;
      while (j >= 0 && arr[j] > arr[index]) {
        int temp = arr[j];
        arr[j] = arr[index];
        arr[index] = temp;
        index--;
        j--;
      }
    }

    printArray(size, arr);
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
        arr[index++] = c;
      }
    }

    br.close();
    sort(cap, arr);
  }
}
