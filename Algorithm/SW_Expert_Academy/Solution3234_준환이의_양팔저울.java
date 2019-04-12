import java.io.*;
import java.util.*;

public class Solution3234_준환이의_양팔저울 {
	static int N, answer;
	static int[] arr;
	static int[] permu;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			answer = 0;

			permu = new int[N];
			arr = new int[N];
			String[] line = br.readLine().split(" ");
			for (int n = 0; n < N; n++) {
				arr[n] = Integer.parseInt(line[n]);
			}

			makePermu(0, 0);
			bw.write("#" + t + " " + answer + "\n");
		}
		bw.flush();
		bw.close();
	}

	public static void makePermu(int idx, int flag) {
		if (idx == N) {
			go(0, 0, 0);
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				permu[idx] = i;
				makePermu(idx + 1, flag | 1 << i);
			}
		}
	}

	public static void go(int idx, int leftSum, int rightSum) {
		if (idx == N) {
			answer++;
			return;
		}

		int leftSumAdd = leftSum + arr[permu[idx]];
		int rightSumAdd = rightSum + arr[permu[idx]];

		go(idx + 1, leftSumAdd, rightSum);
		if (rightSumAdd <= leftSum)
			go(idx + 1, leftSum, rightSumAdd);
	}
}