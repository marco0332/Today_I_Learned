import java.io.*;
import java.util.*;

public class Main2458_키_순서 {
	static int N, M;
	static boolean[][] down, up;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		down = new boolean[N][N];
		up = new boolean[N][N];
		for (int m = 0; m < M; m++) {
			line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]) - 1;
			int b = Integer.parseInt(line[1]) - 1;
			
			down[a][b] = true;
			up[b][a] = true;
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(down[i][k] && down[k][j])
						down[i][j] = true;
					if(up[i][k] && up[k][j])
						up[i][j] = true;
				}
			}
		}
		
		int answer = 0;
		for(int i=0; i<N; i++) {
			int cnt = 0;
			for(int j=0; j<N; j++) {
				if(up[i][j] || down[i][j])
					cnt++;
			}
			if(cnt == N-1)
				answer++;
		}
		
		System.out.println(answer);
	}
}
