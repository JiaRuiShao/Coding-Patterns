/*  */


package array;

class Stock {
    public int globalSell;
    public int globalProfit;

    public Stock(int globalSell, int globalProfit) {
        this.globalSell = globalSell;
        this.globalProfit = globalProfit;
    }
}

class StockPrices {

    public Stock findBuySellStockPrices(int[] arr) {
        if (arr == null || arr.length < 2) return null;
        int minBuy = arr[0], currentProfit, globalProfit = Integer.MIN_VALUE, globalSell = arr[1];
        for (int i = 1; i < arr.length; i++) {
            currentProfit = arr[i] - minBuy;
            if (currentProfit > globalProfit) {
                globalProfit = currentProfit;
                globalSell = arr[i];
            }
            if (arr[i] < minBuy) {
                minBuy = arr[i];
            }
        }
        return new Stock(globalSell, globalProfit);
    }

    public static void main(String[] args) {
        StockPrices sp = new StockPrices();
        int[] array = {1, 2, 3, 4, 3, 2, 1, 2, 5};
        Stock result = sp.findBuySellStockPrices(array);
        System.out.println(String.format("Buy Price: %d, Sell Price: %d", result.globalSell - result.globalProfit, result.globalSell));

        int[] array2 = {8, 6, 5, 4, 3, 2, 1};
        result = sp.findBuySellStockPrices(array2);
        System.out.println(String.format("Buy Price: %d, Sell Price: %d", result.globalSell - result.globalProfit, result.globalSell));
    }
}
