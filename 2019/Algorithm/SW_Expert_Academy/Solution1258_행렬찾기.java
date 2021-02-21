import java.io.*;
import java.util.*;

public class Solution1258_행렬찾기 {
	static class Box implements Comparable<Box> {
		int r, c, size;
		
		public Box(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}

		@Override
		public int compareTo(Box o) {
			if(this.size != o.size) return this.size - o.size;
			else if(this.r != o.r) return this.r - o.r;
			else return 0;
		}
	}
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Box> arr;
	
	public static void go(int r, int c) {
		int cSize = 0;
		int rSize = 0;
		for(int i=c; i<N; i++) {
			if(map[r][i] > 0)
				cSize++;
			else
				break;
		}
		for(int j=r; j<N; j++) {
			if(map[j][c] > 0)
				rSize++;
			else
				break;
		}
		
		for(int i=r; i<r+rSize; i++) {
			for(int j=c; j<c+cSize; j++) {
				visited[i][j] = true;
			}
		}
		
		arr.add(new Box(rSize, cSize, rSize*cSize));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringBuilder sb = new StringBuilder();
			arr = new ArrayList<Box>();
			int bIdx = 0;
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] > 0) {
						go(i, j);
					}
				}
			}
			Collections.sort(arr);
			
			sb.append("#"+t+" "+arr.size());
			for(int i=0,size=arr.size(); i<size; i++) {
				sb.append(" "+arr.get(i).r+" "+arr.get(i).c);
			}
			System.out.println(sb);
		}
	}
}
