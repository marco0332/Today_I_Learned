import java.io.*;

public class Solution1263_사람네트워크2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			
			int N = Integer.parseInt(input[0]);
			int idx = 1;
			int[][] adjArr = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					adjArr[i][j] = Integer.parseInt(input[idx++]);
					
					if(i == j)
						adjArr[i][j] = 0;
					else if(adjArr[i][j] == 0)
						adjArr[i][j] = 987654321;
				}
			}
			
			for(int k=0; k<N; k++) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
					}
				}
			}
			
			int answer = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				int sum = 0;
				
				for(int j=0; j<N; j++) {
					sum += adjArr[i][j];
				}
				answer = Math.min(answer, sum);
			}
			sb.append("#"+t+" "+answer+"\n");
		}
		
		System.out.println(sb);
	}
}
