package com.koitoer.java.algorithm;

/**
 * Created by mauricio.mena on 04/08/2016.
 */
public class MaximumSumArray {

    /**
     * Brute force three nested loops complexity O(n^3)
     * @param arr
     * @param n
     * @return
     */
    public static int maximumArrayNumber(int arr[], int n){

        int ans = Integer.MIN_VALUE;

        for(int array_size = 1 ; array_size <=n ; ++array_size){
            for(int startIndex = 0 ; startIndex < n ; ++startIndex){
                if(startIndex+array_size > n){
                    break;
                }
                int sum = 0;
                for(int i = startIndex ; i < (startIndex+array_size); i++){
                    sum += arr[i];
                }
                ans = Math.max(ans,sum);
            }
        }
        return ans;
    }


    /**
     * Used pre calculate result from previous iteration and added the k+1 element
     * Complexity has been reduced to O(n^2)
     * @param arr
     * @param n
     * @return
     */
    public static int maximumArrayNumberSaving(int arr[], int n){
        int ans = Integer.MIN_VALUE;
        for(int startIndex = 0 ; startIndex < n ; ++startIndex){
            int sum = 0;
            for(int array_size = 1 ; array_size <=n ; ++array_size){
                if(startIndex+array_size > n){
                    break;
                }
                sum+= arr[startIndex + array_size - 1];
                ans = Math.max(ans,sum);
            }
        }
        return ans;
    }


    /**
     * Using divide and conquer and the sum of nested arrays.
     *          -- c                    if n=1
     * T(n)     -- 2T(n/2) + c'n        if n>1
     *
     * T(n) =  cn + c'nlogn
     *
     * O(n log n)
     * @param arr
     * @param n
     * @return
     */
    public static int maximumArrayNumberDivideAndConquer(int arr[], int n){
        if(n==1) {
            return arr[0];
        }
        int m = n/2;
        int leftMax = maximumArrayNumberDivideAndConquer(arr, n);
        int rightMax = maximumArrayNumberDivideAndConquer(arr,n);
        int leftSum  = Integer.MIN_VALUE, rightSum = Integer.MIN_VALUE;
        int sum = 0;

        for(int i = m; i<n ; i++){
            sum += arr[i];
            rightSum = Math.max(rightSum, sum);
        }

        sum = 0;

        for(int i = (m-1); i>=0 ; i--){
            sum += arr[i];
            leftSum = Math.max(leftSum, sum);
        }

        int ans = Math.max(leftMax, rightMax);
        return Math.max(ans, leftSum+rightSum);
    }

    /**
     * Only works if there is a positive number in the array.
     * If array only contains negative check the maximum of them.
     * @param arr
     * @param n
     * @return
     */
        public static int maximumArrayAlgorithm(int arr[], int n){
            int ans = 0, sum = 0;
            for(int i = 0; i < n ; ++i){
                if(sum + arr[i] > 0){
                    sum+=arr[i];
                }else{
                    sum = 0;
                }
                ans = Math.max(ans, sum);
            }
            return ans;
        }


    }
