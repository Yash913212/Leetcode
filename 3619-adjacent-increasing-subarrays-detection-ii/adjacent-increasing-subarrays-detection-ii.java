import java.util.*;

class Solution {
    public int maxIncreasingSubarrays(List<Integer> numsList) {
        int n = numsList.size();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = numsList.get(i);  // convert List → array

        int maxK = 0, prev = 0, curr = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                curr++;
            } else {
                maxK = Math.max(maxK, Math.max(curr / 2, Math.min(prev, curr)));
                prev = curr;
                curr = 1;
            }
        }
        maxK = Math.max(maxK, Math.max(curr / 2, Math.min(prev, curr)));
        return maxK;
    }
}
