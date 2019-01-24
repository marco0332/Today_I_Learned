import java.io.*;
import java.util.*;

public class Solution1288 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			long N = sc.nextLong();
			
			int[] digit = new int[10];
			int cnt = 0;
			int ans = 0;
			long val;
			
			while(cnt<10) {
				ans++;
				val = N*ans;
				
				while(val>0) {
					if(digit[(int)(val%10)]==0) {
						digit[(int)(val%10)]++;
						cnt++;
					}
					val /= 10;
				}
			}
			System.out.printf("#%d %d\n",t,N*ans);
		}
	}
}
