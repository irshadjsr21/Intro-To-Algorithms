import java.io.*;

/**
 * Executes the MaximumSubarray algorithm for the given inputs in `input.txt`
 * file.
 */
class MaximumSubarrayExecutor {

  /**
   * Private class to contain details of the subarray. (like low, high and sum)
   */
  private static class SubArrayDetails {
    public int low;
    public int high;
    public int sum;

    public SubArrayDetails(int low, int high, int sum) {
      this.low = low;
      this.high = high;
      this.sum = sum;
    }
  }

  /**
   * Private method to return the maximum subarray details which crosses the mid
   * point.
   *
   * Complexity: O(n)
   */
  private static SubArrayDetails findMidMaxSubarray(int low, int mid, int high, int[] arr) {
    int leftMax = arr[mid];
    int leftIndex = mid;
    int leftSum = arr[mid];

    int rightMax = arr[mid + 1];
    int rightIndex = mid + 1;
    int rightSum = arr[mid + 1];

    for (int i = mid - 1; i >= low; i--) {
      leftSum = leftSum + arr[i];

      if (leftSum > leftMax) {
        leftMax = leftSum;
        leftIndex = i;
      }
    }

    for (int i = mid + 2; i <= high; i++) {
      rightSum = rightSum + arr[i];

      if (rightSum > rightMax) {
        rightMax = rightSum;
        rightIndex = i;
      }
    }

    return new SubArrayDetails(leftIndex, rightIndex, leftMax + rightMax);
  }

  /**
   * Private method to find the max subarray.
   *
   * Time Complexity: O(n log(n))
   */
  private static SubArrayDetails findMaxSubarray(int low, int high, int[] arr) {
    if (low == high)
      return new SubArrayDetails(low, high, arr[low]);

    int mid = (low + high) / 2;

    SubArrayDetails leftDetails = findMaxSubarray(low, mid, arr);
    SubArrayDetails rightDetails = findMaxSubarray(mid + 1, high, arr);
    SubArrayDetails crossDetails = findMidMaxSubarray(low, mid, high, arr);

    if (leftDetails.sum > rightDetails.sum && leftDetails.sum > crossDetails.sum)
      return leftDetails;
    else if (rightDetails.sum > leftDetails.sum && rightDetails.sum > crossDetails.sum)
      return rightDetails;
    else
      return crossDetails;
  }

  /**
   * The main method reads the input file, create the array and passes it to the
   * findMaxSubarray method.
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
    SubArrayDetails det = findMaxSubarray(0, cap - 1, arr);
    System.out.println("Max Subarray Index: " + det.low + " - " + det.high + ", Sum: " + det.sum);
  }
}
