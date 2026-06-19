class Solution {
    public int largestAltitude(int[] gain) {
     int a = 0;
     int b = 0;

     for(int g : gain){
        b += g;
        a = Math.max(a , b);
     }   
     return a;
    }
}