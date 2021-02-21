import java.io.*;
import java.util.*;

public class Solution4261_빠른_휴대전화_키패드 {
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
		char[] keywordTmp;
		int[] keyword;
		int[] keypad = {2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9};
		int N;
		
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void preprocess() throws IOException {
			String[] line = br.readLine().split(" ");
			keywordTmp = line[0].toCharArray();
			keyword = new int[keywordTmp.length];
			for(int i=0; i<keywordTmp.length; i++) {
				keyword[i] = keywordTmp[i]-'0';
			}
			N = Integer.parseInt(line[1]);
		}
		
		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			for(int t=1; t<=T; t++) {
				preprocess();
				int answer = 0;
				
				String[] line = br.readLine().split(" ");
				for(String cur : line) {
					if(cur.length() == keyword.length) {
						
						boolean flag = true;
						for(int i=0; i<keyword.length; i++) {
							if(keypad[cur.charAt(i)-'a'] != keyword[i]) {
								flag = false;
								break;
							}
						}
						
						if(flag)
							answer++;
					}
					
				}
				
				bw.write("#"+t+" "+answer+"\n");
			}
		}
	}
}
