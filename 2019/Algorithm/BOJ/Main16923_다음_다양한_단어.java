import java.io.*;
import java.util.*;

public class Main16923_다음_다양한_단어 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}
	
	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int[] boxes;
		boolean[] alphabet;
		int bIdx;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
			boxes = new int[26];
			alphabet = new boolean[27];
			bIdx = 0;
		}
		
		public void solve() throws IOException {
			String input = br.readLine();
			if(input.equals("zyxwvutsrqponmlkjihgfedcba")) {
				bw.write("-1"+"\n");
				return;
			}
			
			for(int i=0,len=input.length(); i<len; i++) {
				boxes[bIdx++] = input.charAt(i)-'a'+1;
				alphabet[boxes[bIdx-1]] = true;
			}
			
			if(bIdx < 26) {
				for(int i=1; i<=26; i++) {
					if(!alphabet[i]) {
						boxes[bIdx++] = i;
						break;
					}
				}
			}
			else {
				for(int j=bIdx-1; j>=0; j--) {
					int curIdx = boxes[j];
					boolean flag = false;
					
					for(int i=curIdx+1; i<=26; i++) {
						if(!alphabet[i]) {
							boxes[j] = i;
							alphabet[i] = true;
							flag = true;
							break;
						}
					}
					alphabet[curIdx] = false;
					
					if(flag) {
						break;
					} else {
						boxes[j] = 0;
					}
					
				}
			}
			for(int i=0; i<bIdx; i++) {
				if(boxes[i] > 0)
					bw.write(boxes[i]+'a'-1);
			}
			bw.write("\n");
		}
	}
}