import java.io.*;
import java.util.*;

public class Solution1233 {
	static int N;
	static char[] adj;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line;
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			adj = new char[N + 1];
			Arrays.fill(adj, 'x');

			for (int n = 1; n <= N; n++) {
				line = br.readLine().split(" ");
				adj[Integer.parseInt(line[0])] = line[1].charAt(0);
			}
			
			answer = 1;
			for(int n=1; n<=N; n++) {
				if(n*2 <= N && !Character.isDigit(adj[n]));
				else if(n*2 > N && Character.isDigit(adj[n]));
				else {
					answer = 0;
					break;
				}
			}
			System.out.println("#" + t + " " + answer);
		}
	}
}
