import java.io.*;
import java.util.*;

public class Solution4676 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int H, len;
		for(int t=1; t<=T; t++) {
			String str = new String();
			str = br.readLine();
			len = str.length();
			int[] cnt = new int[len+1];
			
			H = Integer.parseInt(br.readLine());
			String[] line = br.readLine().split(" ");
			for(int h=0; h<H; h++) {
				cnt[Integer.parseInt(line[h])]++;
			}
			
			System.out.print("#"+t+" ");
			for(int h=0; h<len; h++) {
				for(int i=0; i<cnt[h]; i++)
					System.out.print("-");
				System.out.print(str.charAt(h));
			}
			for(int i=0; i<cnt[len]; i++)
				System.out.print("-");
			System.out.println();
		}
	}
}
