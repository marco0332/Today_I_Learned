import java.util.*;
import java.io.*;

public class Solution1240 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] Digit = new int[128];
		for(int i=0; i<128; i++) {
			Digit[i] = -1;
		}
		Digit[13] = 0;		Digit[25] = 1;		Digit[19] = 2;		Digit[61] = 3;		Digit[35] = 4;
		Digit[49] = 5;		Digit[47] = 6;		Digit[59] = 7;		Digit[55] = 8;		Digit[11] = 9;
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			String str = new String();
			int[] arr = new int[8];
			int rule = 64;
			int answer = 0;
			
			boolean check = false;
			for(int n=0; n<N; n++) {
				str = br.readLine();
				int idx = -1;
				if(check) continue;
				
				for(int m=M-1; m>=0; m--) {
					if(str.charAt(m) == '1') {
						idx = m;
						break;
					}
				}
				
				if(idx != -1) {
					check = true;
					
					idx -= 55;
					for(int i=0; i<8; i++) {
						rule = 64;
						int val = 0;
						for(int j=0; j<7; j++) {
							int nIdx = idx + 7*i + j;
							
							if((str.charAt(nIdx)-'0'&1) == 1) {
								val += rule;
							}
							rule /= 2;
						}
						if(Digit[val] != -1) {
							arr[i] = Digit[val];
						}
					}
				}
			}
			
			rule = (arr[0]+arr[2]+arr[4]+arr[6])*3 + arr[1]+arr[3]+arr[5]+arr[7];
			if(rule%10 == 0) {
				for(int i=0; i<8; i++) {
					answer += arr[i];
				}
			}
			System.out.printf("#%d %d\n",t,answer);
		}
	}
}
