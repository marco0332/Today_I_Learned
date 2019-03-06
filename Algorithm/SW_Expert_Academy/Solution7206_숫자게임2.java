import java.io.*;
import java.util.*;

public class Solution7206_숫자게임2 {
	static int answer;
	static int[] dp;
	static String[][] div = { {}, {}, { "1" }, { "01", "10", "11" },
			{ "001", "010", "011", "100", "101", "110", "111" }, { "0001", "0010", "0011", "0100", "0101", "0110",
					"0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111" } };

	public static void go(String N, int cnt) {
		int len = N.length();
		int Sum = 1;
		int val = Integer.parseInt(N);

		if (len > 1) {
			for (String state : div[len]) {
				int l = 0, r = 0;
				Sum = 1;

				for (int i = 0; i < len - 1; i++) {
					r++;
					if (state.charAt(i) == '1') {
						Sum *= Integer.parseInt(N.substring(l, r));
						l = r;
					}
				}
				Sum *= Integer.parseInt(N.substring(l, len));

				go(Integer.toString(Sum), cnt + 1);
			}
		}

		if (answer < cnt)
			answer = cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dp = new int[100000];
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			String N = br.readLine();
			answer = 0;

			if (N.length() < 2)
				sb.append("#" + t + " " + 0);
			else {
				go(N, 0);
				sb.append("#" + t + " " + answer);
			}
			System.out.println(sb);
		}
	}
}