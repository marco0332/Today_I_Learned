import java.io.*;
import java.util.*;

public class Main17136_색종이붙이기 {
	static int N = 10;
	static boolean[][] map = new boolean[N][N];
	static int[] type = {0,5,5,5,5,5};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0) == '1' ? true : false;
			}
		}
		
		int answer = 0;
		for(int size=5; size>0; size--) {
			answer += go(size);
		}
		
		boolean flag = true;
		isNot:
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]) {
					flag = false;
					break isNot;
				}
			}
		}
		
		if(flag) System.out.println(answer);
		else System.out.println(-1);
	}
	
	public static int go(int size) {
		int curAns = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(type[size] == 0 || i+size>N || j+size>N)
					break;
				
				if(map[i][j]) {
					boolean flag = true;
					isNot:
					for(int n=0; n<size; n++) {
						for(int m=0; m<size; m++) {
							if(!map[i+n][j+m]) {
								flag = false;
								break isNot;
							}
						}
					}
					
					if(flag) {
						for(int n=0; n<size; n++) {
							for(int m=0; m<size; m++) {
								map[i+n][j+m] = false;
							}
						}
						curAns++;
						type[size]--;
						j += size-1;
					}
				}
				
			}
		}
		
		return curAns;
	}
}
