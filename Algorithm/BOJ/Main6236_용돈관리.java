import java.io.*;
import java.util.*;

public class Main6236_용돈관리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		int[] arr = new int[N];
		int start = Integer.MIN_VALUE;
		int end = 0;
		for(int n=0; n<N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
			
			start = Math.max(start, arr[n]);
			end += arr[n];
		}
		
		int K = 0;
		int answer = Integer.MAX_VALUE;
		while(start <= end) {
			K = (start+end)/2;
			if(answer <= K) break;
			
			int budget = 0;
			int cnt = 0;
			for(int n=0; n<N; n++) {
				if(arr[n] > budget) {
					budget = K;
					budget -= arr[n];
					cnt++;
				}
				else
					budget -= arr[n];
			}
			
			if(cnt > M)
				start = K+1;
			else if(cnt <= M) {
				answer = Math.min(answer, K);
				end = K;
			}
		}
		System.out.println(answer);
	}
}
