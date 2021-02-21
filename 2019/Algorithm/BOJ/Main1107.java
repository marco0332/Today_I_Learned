import java.io.*;
import java.util.*;

public class Main1107 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] check = new int[10];
		
		for(int m=0; m<M; m++) {
			check[sc.nextInt()]++;
		}
		
		int idx = 100;
		int answer = N-idx>0 ? N-idx : idx-N;
		
		for(int i=0; i<=1000000; i++) {
			boolean isPoss = false;
			int val = i;
			int cnt = 0;
			
			while(true) {
				if(check[val%10] > 0) break;
				val /= 10;
				cnt++;
				if(val == 0) {
					isPoss = true;
					break;
				}
			}
			
			if(isPoss) {
				int min = N - i > 0 ? N - i : i - N;
				min += cnt;
				answer = answer < min ? answer : min;
			}
		}
		
		System.out.println(answer);
	}
}
