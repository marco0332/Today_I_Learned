import java.io.*;
import java.util.*;

public class Main1952_수영장 {
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
		int[] price, using;
		int answer;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			for(int t=1; t<=T; t++) {
				String[] line = br.readLine().split(" ");
				price = new int[4];
				using = new int[12];
				for(int i=0; i<4; i++) {
					price[i] = Integer.parseInt(line[i]);
				}
				line = br.readLine().split(" ");
				for(int i=0; i<12; i++) {
					using[i] = Integer.parseInt(line[i]);
				}
				
				answer = price[3];
				go(0, 0);
				bw.write("#"+t+" "+answer+"\n");
			}
		}
		
		public void go(int idx, int sum) {
			if(idx >= 12) {
				answer = Math.min(answer, sum);
				return;
			}
			
			if(using[idx] == 0)
				go(idx+1, sum);
			
			else {
				// case 1
				go(idx+1, sum+using[idx]*price[0]);
				
				// case 2
				go(idx+1, sum+price[1]);
				
				// case 3
				go(idx+3, sum+price[2]);
			}
		}
	}
}
