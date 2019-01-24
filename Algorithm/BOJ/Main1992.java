import java.io.*;
import java.util.*;

public class Main1992 {
	static String[] map;
	
	public static void go(int N, int y, int x) {
		if(N==1) {
			System.out.print(map[y].charAt(x));
			return;
		}
		
		int sum = 0;
		for(int i=y; i<y+N; i++) {
			for(int j=x; j<x+N; j++) {
				if(map[i].charAt(j) == '1') sum++;
			}
		}
		
		if(sum == N*N) System.out.print("1");
		else if(sum == 0) System.out.print("0");
		else {
			System.out.print("(");
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					go(N/2, y+i*(N/2), x+j*(N/2));
				}
			}
			System.out.print(")");
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		map = new String[N];
		for(int n=0; n<N; n++) {
			map[n] = br.readLine();
		}
		
		go(N, 0, 0);
	}
}
