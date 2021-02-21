import java.io.*;
import java.util.*;

public class Solution4698 {
	static int D;
	
	public static boolean checkDigit(int val) {
		while(val > 0) {
			if(val%10 == D)
				return true;
			val /= 10;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] chae = new boolean[1000001];
		Arrays.fill(chae, true);
		ArrayList<Integer> sosu = new ArrayList<Integer>();
		
		chae[0] = chae[1] = false;
		for(int i=2; i<=1000000; i++) {
			if(chae[i])
				sosu.add(i);
			
			int idx = 2;
			while(i*idx <= 1000000) {
				chae[i*idx] = false;
				idx++;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int size = sosu.size();
			int answer = 0;
			for(int i=0; i<size; i++) {
				int val = sosu.get(i);
				if(A<=val && val<=B) {
					if(checkDigit(val))
						answer++;
				}
			}
			
			System.out.printf("#%d %d\n",t,answer);
		}
	}
}
