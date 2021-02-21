import java.io.*;
import java.util.*;

public class Main1697 {
	
	static int N, K;
	static int[] dp = new int[100001];
	static Queue<Integer> q = new LinkedList<>();
	
	static void go(int u, int v) {
		if(0<= v && v < 100001 && dp[v] == 0) {
			dp[v] = dp[u] + 1;
			q.add(v);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		q.add(N);
		dp[N]++;
		
		while(q.size() > 0) {
			int x = q.poll();
			go(x, x-1);
			go(x, x+1);
			go(x, x*2);
		}
		
		System.out.println(dp[K] - 1);
	}
	
}
