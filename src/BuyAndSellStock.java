

public class BuyAndSellStock {
    /**
     * Brute Way.
     * TC: O(n^2)
     * SC: O(1)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = prices.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (prices[i] - prices[j] > profit) {
                    profit = prices[i] - prices[j];
                }
            }
        }

        return profit;
    }

    /**
     * Find min and max points.
     * TC: O(n)
     * SC: O(1)
     * @param prices
     * @return
     */

    public int maxProfitSmart(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }
}
