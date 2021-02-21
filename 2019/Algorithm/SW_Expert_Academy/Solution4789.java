import java.io.*;
import java.util.*;

public class Solution4789 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] input = br.readLine().split("");
			
			int ans = 0;
			int standing = 0;
			for(int i=0, size=input.length; i<size; i++) {
				standing += Integer.parseInt(input[i]);
				if(standing < i+1) {
					ans += i+1-standing;
					standing = i+1;
				}
			}
			bw.write("#"+t+" "+ans+"\n");
			bw.flush();
		}
		bw.close();
	}
}
