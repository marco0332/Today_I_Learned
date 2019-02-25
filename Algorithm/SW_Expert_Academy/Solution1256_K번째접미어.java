import java.io.*;
import java.util.*;

public class Solution1256_K번째접미어 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int K = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			String[] arr = new String[input.length()+1];
			for(int i=0, len=input.length(); i<=len; i++) {
				arr[i] = input.substring(len-i, len);
			}
			Arrays.sort(arr);
			sb.append("#"+t+" "+arr[K]+"\n");
		}
		System.out.println(sb);
	}
}
