import java.io.*;
import java.util.*;

public class Solution {
	static char[][] board = new char[4][4];
	static Map<String, Integer> m;
	static int answer;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	static void go(int y, int x, int cnt, StringBuilder sb) {
		if(cnt == 7) {
			if(!m.containsKey(sb.toString())) {
				m.put(sb.toString(), 1);
				answer++;
			}
		} else {
			for(int i=0; i<4; i++) {
				int ny = y+dr[i];
				int nx = x+dc[i];
				
				if(0<=ny && ny<4 && 0<=nx && nx<4) {
					sb.append(board[ny][nx]);
					go(ny, nx, cnt+1, sb);
					sb.deleteCharAt(cnt);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			m = new HashMap<>();
			answer = 0;
			
			for(int i=0; i<4; i++) {
				String[] line = br.readLine().split(" ");
				for(int j=0; j<4; j++) {
					board[i][j] = line[j].charAt(0);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					sb.append(board[i][j]);
					go(i, j, 1, sb);
					sb.deleteCharAt(0);
				}
			}
			
			bw.write("#"+t+" "+answer);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
}