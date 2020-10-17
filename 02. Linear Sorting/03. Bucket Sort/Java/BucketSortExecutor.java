import java.io.*;
import java.util.ArrayList;

/**
 * Executes the BucketSort algorithm for the given inputs in `input.txt` file.
 */
class BucketSortExecutor {
  /**
   * Internal method to print the array.
   */
  private static void printArray(int size, double[] arr) {
    for (int i = 0; i < size; i++) {
      System.out.println(arr[i]);
    }
    System.out.println();
  }

  /**
   * Internal method to return the bucket index of the given number.
   */
  private static int getBucketIndex(double num) {
    return Integer.parseInt("" + Double.toString(Math.floor(num * 10)).charAt(0));
  }

  /**
   * Internal method to implement bucket sort.
   *
   * Complexity: O(n),
   *
   * Assumption: The array elements are evenly distributed so each bucket will
   * contain approximately 1 element.
   *
   * If the assumption is not true, then the Complexity will be: O(n log(n)),
   * since the BinaryHeap's add and poll method has a complexity of O(log(n)).
   */
  private static void bucketSort(int size, double arr[]) {
    ArrayList<BinaryHeap<Double>> heap = new ArrayList<BinaryHeap<Double>>(size);

    for (int i = 0; i < size; i++) {
      heap.add(new BinaryHeap());
    }

    for (int i = 0; i < size; i++) {
      int index = getBucketIndex(arr[i]);
      heap.get(index).add((-1) * arr[i]);
    }

    int index = 0;
    for (int i = 0; i < size; i++) {
      BinaryHeap<Double> currentHeap = heap.get(i);
      while (!currentHeap.isEmpty()) {
        arr[index++] = (-1) * currentHeap.poll();
      }
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
    double arr[] = new double[cap];
    String line = null;
    int index = 0;
    while ((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if (lineArr.length > 0) {
        double c = Double.parseDouble(lineArr[0]);
        arr[index++] = c;
      }
    }

    br.close();
    bucketSort(cap, arr);
    printArray(cap, arr);
  }
}
