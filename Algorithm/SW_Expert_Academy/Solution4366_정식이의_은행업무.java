import java.io.*;
import java.util.*;

public class Solution4366_정식이의_은행업무 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[][] check = { { 1, 2 }, { -1, 1 }, { -2, -1 } };

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String binary = br.readLine();
			String triplets = br.readLine();
			Set<Long> s = new HashSet<Long>();

			// 2 진수
			long first = 0, second = 0;
			long base = 1;
			for (int i = 0, len = binary.length(); i < len; i++) {
				first += binary.charAt(len - 1 - i) == '1' ? base : 0;
				base *= 2;
			}

			base = 1;
			for (int i = 0, len = binary.length(); i < len; i++) {
				if (binary.charAt(len - 1 - i) == '1') {
					s.add(first - base);
				} else {
					s.add(first + base);
				}
				base *= 2;
			}

			// 3 진수
			base = 1;
			for (int i = 0, len = triplets.length(); i < len; i++) {
				second += (triplets.charAt(len - 1 - i) - '0') * base;
				base *= 3;
			}

			base = 1;
			long answer = 0;
			fin:
			for (int i = 0, len = triplets.length(); i < len; i++) {
				for(int multi : check[triplets.charAt(len-1-i)-'0']) {
					if(s.contains(second + base*multi)) {
						answer = second + base*multi;
						break fin;
					}
				}
				base *= 3;
			}
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
	}
}
