import java.io.*;
import java.util.*;

public class Solution6782_현주가_좋아하는_제곱근놀이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			long val = Long.parseLong(br.readLine());
			int answer = 0;
			long rootV = 0;
			
			while (val != 2) {
				rootV = (long) Math.sqrt(val);
				if (rootV * rootV == val) {
					val = (long) rootV;
					answer++;
				}
				else {
					answer += (rootV+1)*(rootV+1)-val;
					val = (rootV+1)*(rootV+1);
				}
			}
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
	}
}
