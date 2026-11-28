import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

    
        int[] multCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            for (int mul = f; mul <= maxFactor; mul += f) {
                multCount[f] += freq[mul];
            }
        }

        TreeMap<Long, Long> boostedByPrice = new TreeMap<>();
        long limitPrice = 2L * minPrice;

        for (int[] item : items) {
            int f = item[0];
            long price = item[1];
            long gain = multCount[f] - 1L; 
            if (gain > 0 && price <= limitPrice) {
                boostedByPrice.merge(price, gain, Long::sum);
            }
        }

        long remBudget = budget;
        long totalItems = 0;

        for (Map.Entry<Long, Long> entry : boostedByPrice.entrySet()) {
            long price = entry.getKey();
            long count = entry.getValue();
            if (remBudget < price) break;

            long take = Math.min(count, remBudget / price);
            totalItems += 2L * take;
            remBudget -= take * price;
        }

     
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}