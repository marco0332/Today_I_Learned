import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution3074_입국심사 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int N, M;
        int[] arr;
        int minCost;
        long s, e;
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            minCost = Integer.MAX_VALUE;
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
                minCost = Math.min(minCost, arr[i]);
            }
            s = 0;
            e = (long)minCost * M;
            long mid = 0;
            long ans = Long.MAX_VALUE;
            long sum;
            while(s+1 < e) {
                mid = (s+e)/2;
                sum = 0;
                for(int i = 0; i < N; i++) {
                    sum += mid/arr[i];
                }
                if(sum >= M) {
                    e = mid;
                    if(ans > mid) {
                        ans = mid;
                    }
                }else {
                    s = mid;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb.toString());
    }
}