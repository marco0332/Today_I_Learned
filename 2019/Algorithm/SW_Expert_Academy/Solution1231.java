import java.io.*;
import java.util.*;

public class Solution1231 {
	static char[] adj;
	static int N;
	static StringBuilder sb;

	public static void inorder(int node) {
		if (node <= N) {
			inorder(node * 2);
			sb.append(adj[node]);
			inorder(node * 2 + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input;
		
		for (int t = 1; t <= 10; t++) {
			sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			adj = new char[N + 1];
			
			for (int n = 0; n < N; n++) {
				input = br.readLine().split(" ");
				adj[Integer.parseInt(input[0])] = input[1].charAt(0);
			}
			sb.append("#" + t + " ");
			inorder(1);
			bw.write(sb+"\n");
		}
		bw.flush();
	}
}