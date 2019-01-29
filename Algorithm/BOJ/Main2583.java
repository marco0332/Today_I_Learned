import java.io.*;
import java.util.*;

public class Main2583 {
	static int M, N, K, cnt;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> answer;
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void dfs(int y, int x) {
		visited[y][x] = true;
		cnt++;
		
		for(int i=0; i<4; i++) {
			int ny = y + dr[i];
			int nx = x + dc[i];
			
			if(0<=ny && ny<M && 0<=nx && nx<N && !visited[ny][nx] && map[ny][nx] == 0) {
				dfs(ny,nx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] MNK = br.readLine().split(" ");
		M = Integer.parseInt(MNK[0]);
		N = Integer.parseInt(MNK[1]);
		K = Integer.parseInt(MNK[2]);
		map = new int[M][N];
		visited = new boolean[M][N];
		answer = new ArrayList<Integer>();
		
		for(int k=0; k<K; k++) {
			String[] line = br.readLine().split(" ");
			int lx, ly, rx, ry;
			lx = Integer.parseInt(line[0]);
			ly = M-Integer.parseInt(line[1]);
			rx = Integer.parseInt(line[2]);
			ry = M-Integer.parseInt(line[3]);
			
			for(int y=ry; y<ly; y++) {
				for(int x=lx; x<rx; x++) {
					map[y][x] = 1;
				}
			}
		}
		
		for(int m=0; m<M; m++) {
			for(int n=0; n<N; n++) {
				if(!visited[m][n] && map[m][n] == 0) {
					cnt = 0;
					dfs(m,n);
					answer.add(cnt);
				}
			}
		}
		Collections.sort(answer);
		
		bw.write(answer.size()+"\n");
		for(int i=0,size=answer.size(); i<size; i++) {
			bw.write(answer.get(i)+" ");
		}
		bw.flush();
		bw.close();
	}
}
