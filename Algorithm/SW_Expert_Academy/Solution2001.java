import java.io.*;
import java.util.*;

public class Solution2001 {
	static int N, M;
	static int[][] map;
	
	public static int calc(int i, int j) {
		int sum = 0;
		for(int r=0; r<M; r++) {
			for(int c=0; c<M; c++) {
				sum += map[i+r][j+c];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int Answer = 0;
			String[] NM = br.readLine().split(" ");
			N = Integer.parseInt(NM[0]);
			M = Integer.parseInt(NM[1]);
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				String[] tmp = br.readLine().split(" ");
				
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tmp[j]);
				}
			}
			
			for(int i=0; i<=N-M; i++) {
				for(int j=0; j<=N-M; j++) {
					int val = calc(i,j);
					if(Answer < val) Answer = val;
				}
			}
			
			System.out.printf("#%d %d\n", t, Answer);
		}
	}
}
