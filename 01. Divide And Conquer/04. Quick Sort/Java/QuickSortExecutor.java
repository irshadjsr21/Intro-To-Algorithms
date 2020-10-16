import java.io.*;

/**
 * Executes the QuickSort algorithm for the given inputs in `input.txt` file.
 */
class QuickSortExecutor {
  /**
   * Internal method to print the array.
   */
  private static void printArray(int size, int[] arr) {
    for (int i = 0; i < size; i++) {
      System.out.println(arr[i]);
    }
  }

  /**
   * Internal method to compute and return a `pivot` index in the array which have
   * the following property.
   *
   * - All the elements below index `pivot` is less than the array element at
   * `pivot`.
   *
   * - All the elements above index `pivot` is greater than the array element at
   * `pivot`.
   *
   * Complexity: O(n)
   */
  private static int partition(int startIndex, int endIndex, int[] arr) {
    int pivot = endIndex;
    int i = startIndex - 1;

    for (int j = startIndex; j < pivot; j++) {
      if (arr[j] <= arr[pivot]) {
        i++;
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
      }
    }

    i++;
    int temp = arr[pivot];
    arr[pivot] = arr[i];
    arr[i] = temp;
    return i;
  }

  /**
   * Internal method to implement quick sort.
   *
   * Complexity:
   *
   * Worst Case: O(n^2)
   *
   * Average Case: O(n log(n))
   */
  private static void quickSort(int startIndex, int endIndex, int[] arr) {
    if (startIndex > endIndex)
      return;

    int q = partition(startIndex, endIndex, arr);
    quickSort(startIndex, q - 1, arr);
    quickSort(q + 1, endIndex, arr);
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
    quickSort(0, cap - 1, arr);
    printArray(cap, arr);
  }
}
