import java.io.*;
import java.util.*;

public class Main1780 {
	static int[] answer = {0, 0, 0};
	static int[][] map;
	
	public static void go(int N, int y, int x) {
		if(N==1) {
			answer[map[y][x]+1]++;
			return;
		}
			
		int[] cnt = {0, 0, 0};
		for(int i=y; i<y+N; i++) {
			for(int j=x; j<x+N; j++){
				if(map[i][j] == -1) cnt[0]++;
				else if(map[i][j] == 0) cnt[1]++;
				else if(map[i][j] == 1) cnt[2]++;
			}
		}
		
		for(int i=0; i<3; i++) {
			if(cnt[i] == N*N) {
				answer[i]++;
				return;
			}
		}
		
		int nN = N/3;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				go(nN, y+i*nN, x+j*nN);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] li = br.readLine().split(" ");
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(li[j]);
			}
		}
		
		go(N, 0, 0);
		for(int i=0; i<3; i++) {
			System.out.println(answer[i]);
		}
	}
}
