class Solution {
    public int maximumDifference(int[] nums) {
        int n=nums.length;
        int max = -1;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                int a = nums[i];
                int b = nums[j];
                if(a<b){
                    int temp = b-a;
                    max = Math.max(max,temp);
                }
            }
        }
        return max;
    }
}