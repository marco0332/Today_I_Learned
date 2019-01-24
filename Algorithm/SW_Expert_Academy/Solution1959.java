import java.io.*;
import java.util.*;

public class Solution1959 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] nArr = new int[N];
			int[] mArr = new int[M];
			
			int answer = 0;
			int val = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) {
				nArr[n] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int m=0; m<M; m++) {
				mArr[m] = Integer.parseInt(st.nextToken());
			}
			
			if(N>=M) {
				for(int n=0; n<=N-M; n++) {
					val = 0;
					for(int m=0; m<M; m++) {
						val += nArr[n+m] * mArr[m];
					}
					if(answer < val) answer = val;
				}
			}
			else {
				for(int m=0; m<=M-N; m++) {
					val = 0;
					for(int n=0; n<N; n++) {
						val += mArr[n+m] * nArr[n];
					}
					if(answer < val) answer = val;
				}
			}
			
			System.out.printf("#%d %d\n",t,answer);
		}
	}
}
