import java.io.*;
import java.util.*;

public class Solution1986 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int div2 = N/2;
			int mod2 = N%2;
			
			if(mod2 == 0) System.out.printf("#%d %d\n", t, -div2);
			else System.out.printf("#%d %d\n",  t, N - div2);
		}
	}
}
