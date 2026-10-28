import java.util.Arrays;

public class EqualSubSetSumItrative {

    static boolean equalSubSetSumItrative(int[] arr){

        int sum = Arrays.stream(arr).sum();

        if((sum & 1) == 1) return false;

        int target = sum >> 1;

        boolean[][] dp = new boolean[arr.length + 1][target + 1];

        for(int i = 0; i <= arr.length; i++){
            dp[i][0] = true;
        }

        for(int i = 1; i <= arr.length; i++){
            for(int s = 1; s <= target; s++){

                boolean skip = dp[i-1][s];

                boolean take = false;
                if (arr[i-1] <= s) {
                    take = dp[i-1][s - arr[i-1]];
                }

                dp[i][s] = take || skip;
            }
        }

        return dp[arr.length][target];
    }
    public static void main(String[] args) {
           int[] arr = {1,5,11,5};

           boolean res = equalSubSetSumItrative(arr);

           System.out.println(res);
    }
}
