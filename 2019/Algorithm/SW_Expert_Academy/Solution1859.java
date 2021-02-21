import java.util.*;
import java.io.*;

public class Solution1859 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");

			int cur = 0;
			long ans = 0;
			for(int i=N-1; i>=0; i--) {
				int val = Integer.parseInt(str[i]);
				
				if(cur > val) ans += cur - val;
				else cur = val;
			}
			System.out.printf("#%d %d\n",t,ans);
		}
	}
}
