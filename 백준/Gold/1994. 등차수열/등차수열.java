import java.util.*;
class Main {
    public static int solution(int[]nums,int[][]dy, int n){
        if(n==1) return 1;
        int answer=0;
        for(int i=1;i<n; i++){
            for(int j=1+i;j<=n; j++){
                dy[i][j]=2;
                int pre = nums[i]- (nums[j]-nums[i]);
                //int k=0;
                //for(k=i-1;k>=1;k--){
                    //if(nums[k]==pre) break;
                //}
                //성능개선 O(n) -> O(log n)
                int lt=1, rt=i-1;
                int mid=0;
                while (lt<rt){
                    mid = (lt+rt) / 2;
                    if(nums[mid]<pre) lt=mid+1;
                    else if(nums[mid]==pre && nums[rt]==pre) lt=mid+1;
                    else rt = mid;

                }
                //찾았으면
                if(nums[rt] == pre) dy[i][j] = Math.max(dy[i][j], dy[rt][i]+1);
                answer = Math.max(answer,dy[i][j]);
            }
        }
        //for(int[] t : dy)System.out.println(Arrays.toString(t));
        return answer;

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n+1];
        int[][] dy = new int[n+1][n+1];
        for(int i=1; i<=n; i++)nums[i]= sc.nextInt();
        Arrays.sort(nums);
        System.out.println(solution(nums,dy,n));



    }
}