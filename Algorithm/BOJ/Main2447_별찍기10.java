import java.io.*;
import java.util.*;

public class Main2447_별찍기10 {
	static int N;
	static char[][] answer;
	
	public static void go(int y, int x, int size, int val) {
		if(val == 0) {
			for(int i=y; i<y+size; i++) {
				for(int j=x; j<x+size; j++) {
					answer[i][j] = ' ';
				}
			}
		}
		else if(val == 1) {
			answer[y][x] = '*';
		}
		else {
			int jump = size/3;
			int num = 0;
			for(int i=y; i<y+size; i+=jump) {
				for(int j=x; j<x+size; j+=jump) {
					num++;
					
					if(num == 5) 
						go(i, j, jump, 0);
					else 
						go(i, j, jump, jump);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		answer = new char[N][N];
		
		go(0, 0, N, N);
		for(int i=0; i<N; i++) {
			bw.write(answer[i]);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
}
