import java.io.*;

/**
 * Executes the RadixSort algorithm for the given inputs in `input.txt` file.
 */
class RadixSortExecutor {
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
   * Internal method to return the digit of the given number at the given place.
   */
  private static int getDigit(int num, int place) {
    String numStr = Integer.toString(num);
    int index = numStr.length() - place - 1;

    if (index < 0)
      return 0;

    return Integer.parseInt("" + numStr.charAt(index));
  }

  /**
   * Internal method to find the max in the given array.
   *
   * Complexity: O(n)
   */
  private static int findMax(int size, int arr[]) {
    int max = arr[0];
    for (int i = 1; i < size; i++) {
      if (arr[i] > max)
        max = arr[i];
    }

    return max;
  }

  /**
   * Internal method to implement count sort.
   *
   * Complexity: O(n)
   */
  private static void countSort(int index, int size, int[] arr) {
    int countArr[] = new int[size];
    int newArr[] = new int[size];

    for (int i = 0; i < size; i++)
      countArr[i] = 0;

    for (int i = 0; i < size; i++) {
      countArr[getDigit(arr[i], index)]++;
    }

    for (int i = 1; i < size; i++) {
      countArr[i] += countArr[i - 1];
    }

    for (int i = size - 1; i >= 0; i--) {
      newArr[--countArr[getDigit(arr[i], index)]] = arr[i];
    }

    for (int i = 0; i < size; i++) {
      arr[i] = newArr[i];
    }
  }

  /**
   * Internal method to implement radix sort.
   *
   * Complexity: O(n*m),
   *
   * Where `n` is length of array, `m` is no of digits in the max elem.
   * 
   * Complexity can be O(n) if the no of digits is known.
   */
  private static void radixSort(int size, int arr[]) {
    int max = findMax(size, arr);

    int maxLen = Integer.toString(max).length();

    for (int i = 0; i < maxLen; i++) {
      countSort(i, size, arr);
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
    radixSort(cap, arr);
    printArray(cap, arr);
  }
}
