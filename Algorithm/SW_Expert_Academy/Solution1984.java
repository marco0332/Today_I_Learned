import java.io.*;
import java.util.*;

public class Solution1984 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int Max=0, Min=987654321;
			int sum=0;
			
			String[] str = br.readLine().split(" ");
			for(String s : str) {
				int val = Integer.parseInt(s);
				
				if(Max < val) Max = val;
				if(Min > val) Min = val;
				sum += val;
			}
			sum -= Max + Min;
			System.out.printf("#%d %d\n", t,Math.round(sum/8.0));
		}
	}
}
