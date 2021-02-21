import java.io.*;
import java.util.*;

public class Main2309 {
	static int[] arr = new int[9];
	static boolean[] answer = new boolean[9];
	static int rule;
	
	public static void go(int idx, int cnt, int sum) {
		if(cnt == 2) {
			if(sum == rule) {
				for(int i=0; i<9; i++) {
					if(!answer[i])
						System.out.println(arr[i]);
				}
				System.exit(0);
			}
		}
		else {
			for(int i=idx; i<9; i++) {
				if(!answer[i]) {
					answer[i] = true;
					go(i+1, cnt+1, sum+arr[i]);
					answer[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		rule = 0;
		for(int i=0; i<9; i++) {
			arr[i] = sc.nextInt();
			rule += arr[i];
		}
		rule -= 100;
		Arrays.sort(arr);
		
		go(0, 0, 0);
	}

}
