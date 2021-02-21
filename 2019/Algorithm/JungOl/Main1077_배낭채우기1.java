import java.io.*;
import java.util.*;

public class Main1077_배낭채우기1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int W = Integer.parseInt(line[1]);
		int[] dp = new int[W+1];
		for(int n=0; n<N; n++) {
			line = br.readLine().split(" ");
			int wi = Integer.parseInt(line[0]);
			int pi = Integer.parseInt(line[1]);
			
			for(int w=wi; w<=W; w++) {
				dp[w] = Math.max(dp[w-wi]+pi,dp[w]);
			}
		}
		
		bw.write(dp[W]+"\n");
		bw.flush();
		bw.close();
	}
}
