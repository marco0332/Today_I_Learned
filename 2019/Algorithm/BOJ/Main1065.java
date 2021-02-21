import java.io.*;
import java.util.*;

public class Main1065 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int cnt = 0;
		
		if(N<100) cnt = N;
		else {
			cnt += 99;
			
			int[] val = new int[3];
			for(int i=100; i<=N; i++) {
				val[0] = i%10;
				val[1] = (i/10)%10;
				val[2] = i/100;
				
				if(val[0]-val[1] == val[1]-val[2])
					cnt++;
			}
		}
		System.out.println(cnt);
	}
}
