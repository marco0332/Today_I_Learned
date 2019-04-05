import java.io.*;
import java.util.*;

public class Main2577_회전_초밥 {
	static int N, D, K, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[] count = new int[D + 1];
		int[] startMember = new int[K + 1];
		int[] arr = new int[N+K];
		int curVal = 0;
		int answer = 0;
		int MAX_RESULT = K+1;
		
		for(int n=0; n<N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		for(int n=N; n<N+K; n++) {
			arr[n] = arr[n-N];
		}
		
		
		for(int n=0; n<K; n++) {
			if (count[arr[n]] == 0) {
				curVal++;
			}
			count[arr[n]]++;
			startMember[n] = arr[n];
		}
		int alpha = count[C] == 0 ? 1 : 0;
		answer = Math.max(answer, curVal + alpha);
		
		
		for(int n=K; n<N+K && answer != MAX_RESULT; n++) {
			int befo = arr[n-K];
			count[befo]--;
			if (count[befo] == 0) {
				curVal--;
			}

			if (count[arr[n]] == 0) {
				curVal++;
			}
			count[arr[n]]++;

			alpha = count[C] == 0 ? 1 : 0;
			answer = Math.max(answer, curVal + alpha);
		}
		System.out.println(answer);
	}
}
