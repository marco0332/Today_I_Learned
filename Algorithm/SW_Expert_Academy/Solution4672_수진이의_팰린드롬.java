import java.io.*;
import java.util.*;

public class Solution4672_수진이의_팰린드롬 {
	static String text;
	static int len;
	static int answer;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String teke = br.readLine();
			len = teke.length();
			dp = new int[len][len];
			answer = 0;

			String palin = makePalin(teke);
			text = palin;

			int cur = 0;
			answer = DP(0,len-1);
			bw.write("#" + t + " " + answer + "\n");
		}
		bw.close();
	}

	public static String makePalin(String teke) {
		PriorityQueue<Alpha> pq = new PriorityQueue<>();
		int[] cnt = new int['z' - 'a' + 1];

		for (int i = 0; i < len; i++) {
			cnt[teke.charAt(i) - 'a']++;
		}

		for (int i = 0; i < cnt.length; i++) {
			if (cnt[i] > 0) {
				pq.add(new Alpha((char)(i+'a'),cnt[i]));
			}
		}
		
		String ret = "";
		while(!pq.isEmpty()) {
			Alpha cur = pq.poll();
			
			if(cur.cnt > 1) {
				ret = cur.val+ret;
				ret = ret+cur.val;
				
				if(cur.cnt > 2)
					pq.add(new Alpha(cur.val,cur.cnt-2));
			} else {
				ret += cur.val;
			}
		}
		
		return ret;
	}
	
	public static int DP(int left,int right) {
        if(left > right) return 0;
        if(left==right) return 1;
        if(left+1==right) return text.charAt(left)==text.charAt(right) ? 3 : 2;
        if(dp[left][right]!=0)
            return dp[left][right];
         
        dp[left][right] = DP(left+1,right)+DP(left,right-1) - DP(left+1,right-1);
             
            if(text.charAt(left) == text.charAt(right)) 
                            dp[left][right] += 1;
 
        return dp[left][right];
    }

	static class Alpha implements Comparable<Alpha> {
		char val;
		int cnt;

		public Alpha(char val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Alpha o) {
			if (this.cnt != o.cnt)
				return o.cnt - this.cnt;
			else
				return 0;
		}
	}
}