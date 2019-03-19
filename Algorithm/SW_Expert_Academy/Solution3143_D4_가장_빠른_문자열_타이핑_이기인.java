import java.io.*;
import java.util.*;

public class Solution3143_가장_빠른_문자열_타이핑 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			String A = input[0];
			String B = input[1];
			int alen = A.length();
			int blen = B.length();
			
//			int answer = 0;
//			for(int i=0; i<alen; i++) {
//				if(i+blen <= alen && A.substring(i, i+blen).equals(B)) {
//					answer++;
//					i += blen - 1;
//				} else {
//					answer++;
//				}
//			}
			A = A.replaceAll(B, "1");
//			bw.write("#"+t+" "+answer+"\n");
			bw.write("#"+t+" "+A.length()+"\n");
		}
		bw.close();
	}
}
