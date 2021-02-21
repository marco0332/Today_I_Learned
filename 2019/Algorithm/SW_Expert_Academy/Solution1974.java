import java.util.*;

public class Solution1974 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int N = 9;
		int[][] map = new int[N][N];
		int answer = 1;
		int sum = 0;
		
		for(int t=1; t<=T; t++) {
			answer = 1;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i=0; i<N; i++) {
				sum = 0;
				for(int j=0; j<N; j++) {
					sum += map[i][j];
				}
				if(sum != 45) {
					answer = 0;
					break;
				}
			}
			
			for(int j=0; j<N; j++) {
				sum = 0;
				for(int i=0; i<N; i++) {
					sum += map[i][j];
				}
				if(sum != 45) {
					answer = 0;
					break;
				}
			}

			for (int jI = 0; jI < 3; jI++) {
				for (int jJ = 0; jJ < 3; jJ++) {
					sum = 0;
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							sum += map[i + 3 * jI][j + 3 * jJ];
						}
					}
					if (sum != 45) {
						answer = 0;
						break;
					}
				}
			}
			
			System.out.printf("#%d %d\n",t,answer);
		}
	}
}
