import java.io.*;
import java.util.*;

public class Solution1213_3일차String {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=10; t++) {
			br.readLine();
			
			String pattern = br.readLine();
			String input = br.readLine();
			int len = pattern.length();
			int ans = 0;
			
			for(int i=0,size=input.length(); i<size; i++) {
				if(pattern.charAt(0) == input.charAt(i) && i+len<=size && input.substring(i, i+len).equals(pattern)) {
					ans++;
				}
			}
			
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.println(sb);
	}
}
