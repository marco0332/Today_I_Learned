import java.io.*;
import java.util.*;

public class Solution1285 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int minV = 987654321;
			int cnt = 0;
			
			String[] arr = br.readLine().split(" ");
			for(int n=0; n<N; n++) {
				int val = Integer.parseInt(arr[n]);
				val = Math.abs(val);
				
				if(val < minV) {
					minV = val;
					cnt = 1;
				}
				else if(val == minV){
					cnt++;
				}
			}
			System.out.printf("#%d %d %d\n",t,minV,cnt);
		}
	}
}
