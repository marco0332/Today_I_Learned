import java.io.*;
import java.util.*;

public class Solution1284 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int[] arr = new int[5];
			for(int i=0; i<5; i++) {
				arr[i] = sc.nextInt();
			}
			
			int w1 = arr[0]*arr[4];
			int w2 = arr[1];
			arr[4] -= arr[2];
			if(arr[4]>0)
				w2 += arr[4]*arr[3];
			
			int answer = Math.min(w1, w2);
			System.out.printf("#%d %d\n",t,answer);
		}
	}
}
