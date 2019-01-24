import java.io.*;
import java.util.*;

public class Main2503 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] arr = new int[N][3];
		for(int i=0; i<N; i++) {
			for(int j=0; j<3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int ans = 0;
		int[] val = new int[3];
		int[] cur = new int[3];
		for(int i=123; i<1000; i++) {
			cur[0] = i/100;
			cur[1] = (i/10)%10;
			cur[2] = i%10;
			
			if(cur[0]==cur[1] || cur[1]==cur[2] || cur[0]==cur[2]) continue;
			if(cur[0] == 0 || cur[1] == 0 || cur[2] == 0) continue;
			
			boolean check = true;
			for(int n=0; n<N; n++) {
				val[0] = arr[n][0]/100;
				val[1] = (arr[n][0]/10)%10;
				val[2] = arr[n][0]%10;
				
				int strike=0;
				int ball=0;
				for(int j=0; j<3; j++) {
					for(int k=0; k<3; k++) {
						if(cur[j] == val[k]) {
							if(j==k) strike++;
							else ball++;
							break;
						}
					}
				}
				
				if(arr[n][1] == strike && arr[n][2] == ball)
					;
				else {
					check = false;
					break;
				}
			}
			if(check) ans++;
		}
		System.out.println(ans);
	}
}
