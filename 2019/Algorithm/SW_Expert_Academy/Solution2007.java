import java.io.*;
import java.util.*;

public class Solution2007 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder input = new StringBuilder();
			input.append(br.readLine());

			int ans = 0;

			for (int i = 0; i < input.length(); i++) {
				for(int j=1; j<input.length()/2; j++) {
					if(2*j + i < input.length() && input.substring(i,i+j).compareTo(input.substring(i+j, i+j+j)) == 0) {
						if(ans < j) {
							ans = j;
							break;
						}
					}
				}
				if(ans>0) break;
			}
			
			System.out.printf("#%d %d\n", t, ans);
		}
	}
}
