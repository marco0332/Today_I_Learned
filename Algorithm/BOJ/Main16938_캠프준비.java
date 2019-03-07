import java.io.*;
import java.util.*;

public class Main16938_캠프준비 {
	static int N,L,R,X;
	static int[] arr;
	static int answer;
	static int[] list;
	
	public static void go(int idx, int sum, int cnt) {
		if(cnt>1 && list[cnt-1] - list[0] >= X && L <= sum && sum <= R)
			answer++;
		
		for(int i=idx; i<N; i++) {
			list[cnt] = arr[i];
			go(i+1, sum+arr[i], cnt + 1);
			list[cnt] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		X = sc.nextInt();
		arr = new int[N];
		list = new int[N];
		for(int n=0; n<N; n++) {
			arr[n] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		go(0, 0, 0);
		System.out.println(answer);
	}
}
