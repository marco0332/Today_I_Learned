import java.io.*;
import java.util.*;

public class Main1182 {
	static int N, S;
	static int[] arr;
	static int answer = 0;

	public static void go(int idx, int cnt, int sum) {
		if (idx == N) {
			if(sum == S && cnt > 0)
				answer++;
			return;
		}
		go(idx+1, cnt+1, sum+arr[idx]);
		go(idx+1, cnt, sum);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		S = sc.nextInt();
		arr = new int[N];

		for (int n = 0; n < N; n++) {
			arr[n] = sc.nextInt();
		}
		go(0,0,0);
		System.out.println(answer);
	}
}
