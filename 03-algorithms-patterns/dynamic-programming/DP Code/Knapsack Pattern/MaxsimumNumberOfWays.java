public class MaxsimumNumberOfWays {

    static int maxWay(int[] coins, int target, int n){

        if(target == 0){
            return  1;
        }

        if(n < 0){
            return  0;
        }

        int skip = maxWay(coins, target, n-1);

        int take = 0;
        if(coins[n] <= target){
            take = maxWay(coins, target- coins[n], n);
        }

        return take + skip;
    }

    public static void main(String[] args) {
        int[] coins = {1,2,5};

        int target = 5;

        int res = maxWay(coins,target,coins.length - 1);

        System.out.println(res);
    }
}
