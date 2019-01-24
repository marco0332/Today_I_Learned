import java.io.*;
import java.util.*;

public class Main1535 {
	static int N, answer = 0;
	static int[] happy, spent;
	
	public static void go(int idx, int hSum, int sSum) {
		if(idx == N) {
			if(sSum < 100 && answer < hSum)
				answer = hSum;
			return;
		}
		go(idx+1, hSum+happy[idx], sSum+spent[idx]);
		go(idx+1, hSum, sSum);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		happy = new int[N];
		spent = new int[N];
		for(int n=0; n<N; n++) {
			spent[n] = sc.nextInt();
		}
		for(int n=0; n<N; n++) {
			happy[n] = sc.nextInt();
		}
		
		go(0, 0, 0);
		System.out.println(answer);
	}
}
