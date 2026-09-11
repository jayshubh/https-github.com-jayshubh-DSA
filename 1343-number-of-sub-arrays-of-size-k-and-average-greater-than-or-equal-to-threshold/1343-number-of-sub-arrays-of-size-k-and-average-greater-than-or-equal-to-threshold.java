class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int i = 0;
        int j = k;
        int c = 0;
        int n = arr.length;
        while (j <= n) {
            int sum = 0;
            for (int a = i; a < j; a++) {
                sum += arr[a];
            }
            int avg = sum / k;
            if (avg >= threshold) {
                c++;
            }
            i++;
            j++;
        }
        return c;
    }
}