import java.io.*;
import java.util.*;

public class Solution1928 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String str = br.readLine();
			int len = str.length();
			len /= 4;
			String ans = new String();
			
			for(int i=0; i<len; i++) {
				int code = 0;
				for(int j=0; j<4; j++) {
					code <<= 6;
					
					char ch = str.charAt(i*4 + j);
					if('A' <= ch && ch <= 'Z') {
						code += ch - 'A';
					}
					else if('a' <= ch && ch <= 'z') {
						code += ch - 'a' + 26;
					}
					else if('0' <= ch && ch <= '9') {
						code += ch - '0' + 52;
					}
					else if(ch == '+') {
						code += 62;
					}
					else if(ch == '/') {
						code += 63;
					}
				}
				
				for(int k=3; k>0; k--) {
					int chV = 0;
					for(int j=0; j<8; j++) {
						int idx = k*8-1-j;
						
						if((code & (1<<idx)) != 0) {
							chV += 1<<(7-j);
						}
					}
					ans += (char)chV;
				}
			}
			System.out.printf("#%d %s\n",t,ans);
		}
	}
}
