import java.util.*;

class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length; // number of users
        
        // Convert each user's languages to a set for quick lookup
        List<Set<Integer>> userLanguages = new ArrayList<>();
        for (int[] langArr : languages) {
            Set<Integer> set = new HashSet<>();
            for (int lang : langArr) set.add(lang);
            userLanguages.add(set);
        }
        
        // Step 1: Identify users who cannot communicate
        Set<Integer> usersToTeach = new HashSet<>();
        
        for (int[] friendship : friendships) {
            int u = friendship[0] - 1; // 0-based index
            int v = friendship[1] - 1;
            
            // Check if they share a common language
            boolean canCommunicate = false;
            for (int lang : userLanguages.get(u)) {
                if (userLanguages.get(v).contains(lang)) {
                    canCommunicate = true;
                    break;
                }
            }
            
            if (!canCommunicate) {
                usersToTeach.add(u);
                usersToTeach.add(v);
            }
        }
        
        if (usersToTeach.isEmpty()) return 0; // No teaching needed
        
        // Step 2: For each language, count how many of these users already know it
        int[] languageCount = new int[n + 1]; // 1-indexed
        
        for (int user : usersToTeach) {
            for (int lang : userLanguages.get(user)) {
                languageCount[lang]++;
            }
        }
        
        // Step 3: Find language with maximum users already knowing it
        int maxKnow = 0;
        for (int i = 1; i <= n; i++) {
            maxKnow = Math.max(maxKnow, languageCount[i]);
        }
        
        // Step 4: Minimum users to teach = total users - maxKnow
        return usersToTeach.size() - maxKnow;
    }
}
