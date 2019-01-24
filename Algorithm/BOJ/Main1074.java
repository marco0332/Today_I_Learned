import java.io.*;
import java.util.*;

public class Main1074 {
	public static int go(int N, int y, int x, int r, int c) {
		if(N==1) return 0;
		
		int ans = 0;
		int nN = N/2;
		int[] dr = {nN-1, nN-1, 2*nN-1, 2*nN-1};
		int[] dc = {nN-1, 2*nN-1, nN-1, 2*nN-1};
		
		int idx = 0;
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				int ny = y + nN*i;
				int nx = x + nN*j;
				
				if(ny<=r && r<=y+dr[idx] && nx<=c && c<=x+dc[idx]) {
					ans += go(nN, ny, nx, r, c) + nN*nN*idx;
					break;
				}
				idx++;
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		N = (int) Math.pow(2, N);
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		System.out.println(go(N, 0, 0, r, c));
	}
}
