import java.io.*;
import java.util.*;

public class Main1681_해밀턴_순환회로 {
	static int N;
	static int[][] map;
	static int answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		for(int i=0; i<N; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		// 최소값 세팅
		for(int i=0; i<N-1; i++) {
			answer += map[i][i+1];
		}
		answer += map[N-1][0];
		
		go(0, 0, 0);
		System.out.println(answer);
	}
	
	static ArrayList<Integer> arr = new ArrayList<>();
	
	public static void go(int idx, int sum, int cnt) {
		if(cnt == N-1) {
			if(map[idx][0] > 0 && answer > sum+map[idx][0]) {
				answer = sum+map[idx][0];
			}
			return;
		}
		
		visited[idx] = true;
		
		for(int i=1; i<N; i++) {
			if(i!=idx && !visited[i] && map[idx][i]>0 && sum+map[idx][i]<answer) {
				arr.add(i);
				go(i, sum+map[idx][i], cnt+1);
				arr.remove(cnt);
			}
		}
		
		visited[idx] = false;
	}
}
