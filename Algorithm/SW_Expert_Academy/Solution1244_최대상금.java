import java.io.*;
import java.util.*;

public class Solution1244_최대상금 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int answer, cnt, len;
	static String val;

	public static void swap(int i, int j) {
		StringBuilder sb = new StringBuilder(val);
		char l = sb.charAt(i), r = sb.charAt(j);
		sb.setCharAt(i, r);
		sb.setCharAt(j, l);
		val = sb.toString();
	}

	public static void go(int N) throws IOException {
		if (N == 0)
			answer = Math.max(answer, Integer.parseInt(val));
		else {
			for (int i = 0; i < len - 1; i++) {
				for (int j = i + 1; j < len; j++) {
					if (i != j) {
						swap(i, j);
						go(N - 1);
						swap(i, j);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] input = br.readLine().split(" ");
			val = input[0];
			cnt = Integer.parseInt(input[1]);
			len = val.length();
			answer = 0;

			bw.write("#" + t + " ");
			if(cnt > 4) {
				cnt = 4 + cnt%2;
			}
			go(cnt);
			bw.write(answer + "\n");
			bw.flush();
		}
		bw.close();
	}
}