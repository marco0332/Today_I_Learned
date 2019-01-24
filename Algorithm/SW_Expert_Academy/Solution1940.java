import java.io.*;
import java.util.*;

public class Solution1940 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int N = sc.nextInt();
			
			int speed = 0;
			int dist = 0;
			int sel = 0;
			int acc = 0;
			for(int n=0; n<N; n++) {
				sel = sc.nextInt();
				if(sel == 0) {
					dist += speed;
				}
				else if(sel == 1) {
					acc = sc.nextInt();
					speed += acc;
					dist += speed;
				}
				else if(sel == 2) {
					acc = sc.nextInt();
					speed = speed-acc > 0 ? speed-acc : 0;
					dist += speed;
				}
			}
			
			System.out.printf("#%d %d\n",t,dist);
		}
	}
}
