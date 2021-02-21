import java.io.*;
import java.util.*;

public class Main2448_별찍기11 {
	static char[][] answer;
	
	public static void go(int y, int x, int N) {
		if(N == 3) {
			answer[y][x] = '*';
			
			answer[y+1][x-1] = '*';
			answer[y+1][x+1] = '*';
			
			for(int i=0; i<5; i++) {
				answer[y+2][x-2+i] = '*';
			}
		}
		else {
			int jump = N/2;
			
			go(y, x, jump);
			go(y+jump, x-jump, jump);
			go(y+jump, x+jump, jump);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		answer = new char[N][2*N-1];
		for(int n=0; n<N; n++) {
			Arrays.fill(answer[n], ' ');
		}
		
		go(0, N-1, N);
		for(int n=0; n<N; n++) {
			bw.write(answer[n]);
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}
