import java.io.*;
import java.util.*;

public class Main6603 {
	static int[] arr;
	static int[] answer;
	static int N;
	
	public static void go(int idx, int cnt) {
		if(cnt == 6) {
			for(int i=0; i<cnt; i++) {
				System.out.print(answer[i]+" ");
			}
			System.out.println();
		}
		else {
			for(int i=idx; i<N; i++) {
				answer[cnt] = arr[i];
				go(i+1,cnt+1);
				answer[cnt] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			
			arr = new int[N];
			answer = new int[N];
			for(int n=0; n<N; n++) {
				arr[n] = Integer.parseInt(st.nextToken());
			}
			go(0, 0);
			System.out.println();
		}
	}
}
