public class UnboundedKnapsack {

    static int knapsack(int n, int capacity, int[] wt, int[] val){

         if (n < 0 || capacity == 0) {
            return 0;
        }

        // Skip current item
        int skip = knapsack(n - 1, capacity, wt, val);

        // Take current item
        int take = 0;

        if (wt[n] <= capacity) {
            take = val[n] +
                   knapsack(n, capacity - wt[n], wt, val);
        }

        return Math.max(take, skip);
    }

    public static void main(String[] args) {
          int[] wt = {2, 3, 4};
        int[] val = {4, 5, 7};
        int capacity = 6;

        int result = knapsack(wt.length - 1, capacity, wt, val);

        System.out.println(result);
    }
    
}
