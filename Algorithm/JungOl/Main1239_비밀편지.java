import java.io.*;
import java.util.*;

public class Main1239_비밀편지 {
	static char[] alpha = {'A','B','C','D','E','F','G','H'};
	static int[] code = {0,15,19,28,38,41,53,58};
	static int[] data;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		data = new int[N];
		ArrayList<Character> ans = new ArrayList<>();
		for(int n=0; n<N; n++) {
			String subInput = input.substring(6*n, 6*n+6);
			
			for(int i=0; i<6; i++) {
				data[n] += subInput.charAt(5-i) == '1' ? 1<<i : 0;
			}
			char cur = find(data[n]);
			
			if(cur != 'X')
				ans.add(cur);
			else {
				bw.write((n+1)+"\n");
				bw.close();
				return;
			}
		}
		for(int i=0; i<N; i++) {
			bw.write(ans.get(i)+"");
		}
		bw.close();
	}
	
	public static char find(int val) {
		char ret = 'X';
		
		for(int i=0; i<8; i++) {
			int cnt = 0;
			
			for(int j=0; j<6; j++) {
				if((val & 1<<j) != (code[i] & 1<<j)) {
					cnt++;
				}
			}
			
			if(cnt <= 1) {
				ret = alpha[i];
				break;
			}
		}
		
		return ret;
	}
}
