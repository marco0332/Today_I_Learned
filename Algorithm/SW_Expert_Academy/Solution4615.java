import java.io.*;
import java.util.*;

public class Solution4615 {
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		int N, M, r, c, doll, mid;
		
		int[] dr = {0,0,1,-1};
		int[] dc = {1,-1,0,0};
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			mid = N/2;
			for(int i=0; i<2; i++) {
				map[mid-i][mid-i] = 2;
				map[mid-(i+1)%2][mid-i] = 1;
			}
			
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine());
				
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				doll = Integer.parseInt(st.nextToken());
			}
		}
	}
}
