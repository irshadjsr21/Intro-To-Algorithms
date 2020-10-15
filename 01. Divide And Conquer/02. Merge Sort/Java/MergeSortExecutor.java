import java.io.*;

/**
 * Executes the MergeSort algorithm for the given inputs in `input.txt` file.
 */
class MergeSortExecutor {
  /**
   * Internal method to print the array.
   */
  private static void printArray(int size, int[] arr) {
    for (int i = 0; i < size; i++) {
      System.out.println(arr[i]);
    }
  }

  /**
   * Internal method to implement merge sort.
   *
   * Time Complexity: O(n log(n))
   *
   * Space Complexity: O(n)
   */
  private static void mergeSort(int startIndex, int size, int[] arr) {
    if (size <= 1)
      return;

    int startIndex1 = startIndex;
    int size1 = (size / 2);
    int startIndex2 = startIndex + size1;
    int size2 = size - size1;

    mergeSort(startIndex1, size1, arr);
    mergeSort(startIndex2, size2, arr);

    int i = startIndex1, j = startIndex2;
    int[] newArr = new int[size];
    int index = 0;

    while (i < startIndex1 + size1 && j < startIndex2 + size2) {
      if (arr[i] < arr[j]) {
        newArr[index++] = arr[i++];
      } else {
        newArr[index++] = arr[j++];
      }
    }

    while (i < startIndex1 + size1) {
      newArr[index++] = arr[i++];
    }

    while (j < startIndex2 + size2) {
      newArr[index++] = arr[j++];
    }

    index = startIndex;
    for (i = 0; i < size; i++) {
      arr[startIndex + i] = newArr[i];
    }
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
    mergeSort(0, cap, arr);
    printArray(cap, arr);
  }
}
