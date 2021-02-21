import java.io.*;
import java.util.*;

class Pos{
	int y, x;
	
	public Pos(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main2667 {
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int ansCnt;
	static ArrayList<Integer> ansHouse;
	
	public static void bfs(int y, int x) {
		ansCnt++;
		
		int cnt = 0;
		
		visited[y][x] = true;
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(y,x));
		
		while(!q.isEmpty()){
			Pos cur = q.poll();
			cnt++;
			
			for(int i=0; i<4; i++) {
				int ny = cur.y + dr[i];
				int nx = cur.x + dc[i];
				
				if(0<=ny && ny<N && 0<=nx && nx<N && !visited[ny][nx] && map[ny][nx] == 1) {
					visited[ny][nx] = true;
					q.add(new Pos(ny,nx));
				}
			}
		}
		
		ansHouse.add(cnt);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		ansHouse = new ArrayList<Integer>();
		map = new int[N][N];
		visited = new boolean[N][N];
		ArrayList<Pos> arr = new ArrayList<Pos>();
		
		for(int r=0; r<N; r++) {
			String[] input = br.readLine().split("");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(input[c]);
				
				if(map[r][c] == 1) arr.add(new Pos(r,c));
			}
		}
		
		for(int i=0, size=arr.size(); i<size; i++) {
			int y = arr.get(i).y;
			int x = arr.get(i).x;
			
			if(!visited[y][x]) bfs(y,x);
		}
		Collections.sort(ansHouse);
		
		System.out.println(ansCnt);
		for(int i=0, size=ansHouse.size(); i<size; i++) {
			System.out.println(ansHouse.get(i));
		}
	}
}
