class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i : gifts){
            pq.add(i);
        }
        for(int i=0;i<k;i++){
            int maxEle = pq.poll();
            int val = (int) Math.sqrt(maxEle);
            pq.add(val);
        }
        long ans = 0;
        for(int i : pq){
            ans+=i;
        }
        return ans;
    }
}