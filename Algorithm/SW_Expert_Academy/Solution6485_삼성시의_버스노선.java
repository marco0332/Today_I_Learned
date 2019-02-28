import java.io.*;
import java.util.*;

public class Solution6485_삼성시의_버스노선 {
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			answer = new int[5001];

			int S, E;
			for (int n = 0; n < N; n++) {
				String[] SE = br.readLine().split(" ");
				S = Integer.parseInt(SE[0]);
				E = Integer.parseInt(SE[1]);

				for (int i = S; i <= E; i++) {
					answer[i]++;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#"+t);
			int P = Integer.parseInt(br.readLine());
			for(int p=0; p<P; p++) {
				int idx = Integer.parseInt(br.readLine());
				sb.append(" " + answer[idx]);
			}
			System.out.println(sb);
		}
	}
}
