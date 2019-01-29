import java.io.*;
import java.util.*;

public class Solution4751 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		String[] last = { ".", ".", "#", ".", "." };
		String[] pattern = { "..#.", ".#.#", "", ".#.#", "..#." };
		for (int t = 1; t <= T; t++) {
			String[] input = br.readLine().split("");
			int len = input.length;

			for (int r = 0; r < 5; r++) {
				if (r != 2) {
					for (int l = 0; l < len; l++) {
						bw.write(pattern[r]);
					}
				} else {
					for (int l = 0; l < len; l++) {
						bw.write("#." + input[l] + ".");
					}
				}
				bw.write(last[r] + "\n");
			}
			bw.flush();
		}
		bw.close();
	}
}
