import java.io.*;
import java.util.*;

public class Solution2007_패턴마디의길이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String input = br.readLine();
			
			for(int i=1; i<=10; i++) {
				if(input.substring(0, i).equals(input.substring(i, i+i))) {
					sb.append("#"+t+" "+i+"\n");
					break;
				}
			}
			
		}
		System.out.println(sb);
	}
}
