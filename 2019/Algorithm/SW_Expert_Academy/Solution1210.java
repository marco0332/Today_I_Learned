import java.io.*;
import java.util.*;

public class Solution1210 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = 100;
		for(int t=1; t<=10; t++) {
			int[][] map = new int[N][N];
			int idx = 0;
			br.readLine();
			
			String[] li = new String[N];
			for(int i=0; i<100; i++) {
				li = br.readLine().split(" ");
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(li[j]);

					if(map[i][j] == 2)
						idx = j;
				}
			}
			
			for(int i=98; i>0; i--) {
				if(idx-1 >= 0 && map[i][idx-1] == 1) {
					while(idx-1 >= 0 && map[i][idx-1] == 1)
						idx--;
				}
				else if(idx+1 < N && map[i][idx+1] == 1) {
					while(idx+1 < N && map[i][idx+1] == 1)
						idx++;
				}
			}
			
			System.out.printf("#%d %d\n",t,idx);
		}
	}
}