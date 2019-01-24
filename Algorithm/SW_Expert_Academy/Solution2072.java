import java.io.*;
import java.util.*;

public class Solution2072 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int ans;
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			ans = 0;
			
			for (int i = 0; i < 10; i++) {
				int val = Integer.parseInt(st.nextToken());
				if (val % 2 == 1)
					ans += val;
			}
			
			System.out.printf("#%d %d\n",t,ans);
		}
	}
}
