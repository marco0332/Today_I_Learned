import java.io.*;
import java.util.*;

public class Main11404_플로이드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] adjArr = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(adjArr[i], Integer.MAX_VALUE);
		}
		
		for(int m=0; m<M; m++) {
			String[] input = br.readLine().split(" ");
			
			int u = Integer.parseInt(input[0]);
			int v = Integer.parseInt(input[1]);
			int w = Integer.parseInt(input[2]);
			
			adjArr[u][v] = Math.min(adjArr[u][v], w);
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k==i) continue;
				for(int j=1; j<=N; j++) {
					if(i==j || k==i) continue;
					
					if(adjArr[i][k] != Integer.MAX_VALUE && adjArr[k][j] != Integer.MAX_VALUE) {
						adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k]+adjArr[k][j]);
					}
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(adjArr[i][j] == Integer.MAX_VALUE) 
					adjArr[i][j] = 0;
				
				sb.append(adjArr[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
