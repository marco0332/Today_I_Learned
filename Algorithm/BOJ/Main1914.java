import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main1914 {
	public static void hanoi(int n, int target, int curBox) {
		if(n==1) System.out.println(curBox+" "+target);
		else {
			int center = 6 - target - curBox;
			hanoi(n-1, center, curBox);
			System.out.println(curBox+" "+target);
			hanoi(n-1, target, center);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		BigInteger[] cnt = new BigInteger[101];
		cnt[1] = new BigInteger("1");
		for(int i=2; i<=100; i++) {
			cnt[i] = cnt[i-1].multiply(new BigInteger("2")).add(new BigInteger("1"));
		}
		
		int N = sc.nextInt();
		System.out.println(cnt[N]);
		
		if(N<=20)
			hanoi(N, 3, 1);
	}
}
