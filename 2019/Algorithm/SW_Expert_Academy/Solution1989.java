import java.io.*;
import java.util.*;

public class Solution1989 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringBuffer str = new StringBuffer(br.readLine());
			
			int l = str.length();
			boolean check = true;
			if(l%2 == 1) {
				for(int i=0; i<l/2; i++) {
					if(str.charAt(i) != str.charAt(l-1-i)) {
						check = false;
						break;
					}
				}
			}
			else {
				for(int i=0; i<=l/2; i++) {
					if(str.charAt(i) != str.charAt(l-1-i)) {
						check = false;
						break;
					}
				}
			}
			
			if(check) System.out.printf("#%d %d\n", t, 1);
			else System.out.printf("#%d %d\n",t, 0);
		}
	}
}
