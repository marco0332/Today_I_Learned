import java.io.*;
import java.util.*;

public class Solution2071 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			double ans = 0;
			
			for(int i=0; i<10; i++) {
				int val = Integer.parseInt(st.nextToken());
				
				ans += val;
			}
			ans /= 10;
			System.out.println("#"+t+" "+Math.round(ans));
		}
	}
}
