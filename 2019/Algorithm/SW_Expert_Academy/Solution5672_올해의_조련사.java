import java.io.*;
import java.util.*;

public class Solution5672_올해의_조련사 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br,bw);
		solver.solve();
		bw.close();
	}
	
	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			
			for(int t=1; t<=T; t++) {
				int N = Integer.parseInt(br.readLine());
				PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
				
				int[] befo = new int[N];
				for(int n=0; n<N; n++) {
					befo[n] = br.readLine().charAt(0)-'A';
				}
				
				StringBuilder after = new StringBuilder();
				int L = 0, R = N-1;
				
				while(L<R) {
					if(befo[L] < befo[R]) {
						after.append((char)(befo[L]+'A'));
						L++;
					} else if(befo[L] > befo[R]) {
						after.append((char)(befo[R]+'A'));
						R--;
					} else {
						int tmpL = L;
						int tmpR = R;
						while(tmpL < tmpR && befo[tmpL] == befo[tmpR]) {
							tmpL++; tmpR--;
						}
						if(tmpL >= tmpR) {
							while(L<R) {
								after.append((char)(befo[L++]+'A'));
							}
						} else if(befo[tmpL] < befo[tmpR]) {
							after.append((char)(befo[L]+'A'));
							L++;
						} else {
							after.append((char)(befo[R]+'A'));
							R--;
						}
					}
				}
				after.append((char)(befo[L]+'A'));
				
				bw.write("#"+t+" "+after+"\n");
			}
		}
	}
}
