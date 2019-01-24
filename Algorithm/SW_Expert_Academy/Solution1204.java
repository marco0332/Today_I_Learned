import java.io.*;
import java.util.*;

public class Solution1204 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			
			int Max = 0;
			int mIdx = 0;
			st = new StringTokenizer(br.readLine());
			int[] cnt = new int[101];
			for(int n=0; n<1000; n++) {
				int idx = Integer.parseInt(st.nextToken());
				cnt[idx]++;
				
				if(cnt[idx] >= Max) {
					Max = cnt[idx];
					mIdx = idx;
					System.out.println(idx);
				}
			}
			System.out.println("#"+t+" "+mIdx);
		}
	}
}
