import java.io.*;
import java.util.*;

public class Solution6719_성수의프로그래밍강좌시청 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] NK = br.readLine().split(" ");
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(NK[0]);
			int K = Integer.parseInt(NK[1]);
			int[] list = new int[N];
			
			for(int i=0; i<N; i++) {
				list[i] = Integer.parseInt(input[i]);
			}
			Arrays.sort(list);
			
			double answer = 0;
			for(int i=N-K; i<N; i++) {
				answer = (answer+list[i])/2.0;
			}
			bw.write("#"+t+" "+answer);
		}
		bw.flush();
		bw.close();
	}
}
