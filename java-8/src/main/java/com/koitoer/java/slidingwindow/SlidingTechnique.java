package com.koitoer.java.slidingwindow;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SlidingTechnique {

    @Test
    public void maxInSubArrayLength3() {
        int k = 3;
        int array[] = { 2, 3, 4, 5, -1, 8, 2 };
        Assertions.assertThat(new SlidingTechnique().findMaxInSubArrayWithLength(array, k)).isEqualTo(12);
        Assertions.assertThat(new SlidingTechnique().findMaxAverage(array, k)).isEqualTo(12);
    }

    @Test
    public void maxInSubArrayLength2() {
        int k = 2;
        int array[] = { 2, 3, 4, 5, -1, 8, 2 };
        Assertions.assertThat(new SlidingTechnique().findMaxInSubArrayWithLength(array, k)).isEqualTo(10);
        Assertions.assertThat(new SlidingTechnique().findMaxAverage(array, k)).isEqualTo(10);
    }

    @Test
    public void minArrayLengthSumA() {
        int a = 10;
        int array[] = { 2, 4, 6, 2, 8, 1, 9, 10 };
        Assertions.assertThat(new SlidingTechnique().minSubArraySize(array, a)).isEqualTo(1);
    }

    @Test
    public void maxSubArraySum() {
        int array[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        Assertions.assertThat(new SlidingTechnique().maxSubArray(array)).isEqualTo(6);
        Assertions.assertThat(new SlidingTechnique().maxSubArraySimple(array)).isEqualTo(6);
    }

    @Test
    public void maxSubArraySum2() {
        int array[] = { 2, 1, 3, 4, -1, -2, -100, 1005, 4 };
        Assertions.assertThat(new SlidingTechnique().maxSubArraySimple(array)).isEqualTo(1009);
        Assertions.assertThat(new SlidingTechnique().maxCCode(array)).isEqualTo(1009);

    }

    @Test
    public void maxSubArraySum3() {
        int array[] = { -1, -2, -3, -4, -1, -2, -100, -1005, 4 };
        Assertions.assertThat(new SlidingTechnique().maxSubArraySimple(array)).isEqualTo(4);
        Assertions.assertThat(new SlidingTechnique().maxCCode(array)).isEqualTo(4);

    }

    private int getTotalCount(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            count += array[i];
        }
        return count;
    }

    /**
     * local_maximum at index i is the maximum of A[i] and the sum of A[i] and local_maximum at index i-1.
     */
    public int maxCCode(int[] array) {
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            localMax = Math.max(array[i], array[i] + localMax);
            if (localMax > globalMax) {
                globalMax = localMax;
            }
        }
        return globalMax;
    }

    public String toLowerCase(String str) {
        //[t, e, s, t, S, t, r, i, n, g]
        Character[] charObjectArray =
            str.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        return Arrays.stream(charObjectArray).map(x -> x.toString().toUpperCase()).collect(Collectors.joining());
    }

    public int maxSubArraySimple(int[] array) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            // Increment the counter with the current value
            sum = sum + array[i];

            if (sum > maxSum) {
                maxSum = sum;
            }

            // If sum is negative, we simply get rid of the past
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    public int maxSubArray(int[] array) {
        int sum = getTotalCount(array);
        return maxSubArrayRecursive(array, 0, array.length - 1, sum);
    }

    private int maxSubArrayRecursive(int[] array, int left, int right, int count) {
        if (left >= right)
            return count;

        int withoutFirst = maxSubArrayRecursive(array, left + 1, right, count - array[left]);
        int withoutLast = maxSubArrayRecursive(array, left, right - 1, count - array[right]);

        return Math.max(count, Math.max(withoutFirst, withoutLast));

    }

    private int minSubArraySize(int[] array, int a) {
        int n = array.length;
        int minLength = Integer.MAX_VALUE, i = 0, sum = 0;
        for (int j = 0; j < n; j++) {
            sum += array[j];
            while (sum >= a) {
                minLength = Math.min(minLength, j - i + 1);
                sum -= array[i++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    @Test
    public void maxInSubArrayLength2_sliding() {
        int k = 2;
        int array[] = { 2, 3, 4, 5, -1, 8, 2 };
        Assertions.assertThat(new SlidingTechnique().findMaxAverage(array, k)).isEqualTo(10);
    }

    private int findMaxInSubArrayWithLength(int[] array, int k) {
        int currentSum = 0, maxSum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < i + k; j++) {
                if (j == array.length) {
                    break;
                }
                currentSum += array[j];
            }
            maxSum = Math.max(currentSum, maxSum);
            currentSum = 0;
        }

        return maxSum;
    }

    public double findMaxAverage(int[] array, int k) {
        int n = array.length, sum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < k; ++i) {
            sum += array[i];
        }
        for (int i = k; i <= n; ++i) {
            maxSum = Math.max(maxSum, sum);
            if (i == n) {
                break;
            }
            sum -= array[i - k];
            sum += array[i];
        }
        return maxSum;
    }

}
