import java.io.*;
import java.util.*;

public class Main2606_바이러스 {
	static int N, K;
	static boolean[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new boolean[N+1][N+1];
		
		for(int k=0; k<K; k++) {
			String[] line = br.readLine().split(" ");
			
			int s = Integer.parseInt(line[0]);
			int e = Integer.parseInt(line[1]);
			arr[s][e] = true;
			arr[e][s] = true;
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i!=j && arr[i][k] && arr[k][j])
						arr[i][j] = true;
				}
			}
		}
		
		int answer = 0;
		for(int n=1; n<=N; n++) {
			if(arr[1][n])
				answer++;
		}
		
		System.out.println(answer);
	}
}
