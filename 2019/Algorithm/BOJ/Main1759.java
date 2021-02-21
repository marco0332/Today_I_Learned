import java.io.*;
import java.util.*;

public class Main1759 {
	static int L, C;
	static char[] arr, answer;
	
	public static void go(int idx, int cnt, int vowel, int conso) {
		if(cnt == L) {
			if(vowel >=1 && conso >=2)
				System.out.println(answer);
		}
		else {
			for(int i=idx; i<C; i++) {
				answer[cnt] = arr[i];
				if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u')
					go(i+1, cnt+1, vowel+1, conso);
				else
					go(i+1, cnt+1, vowel, conso+1);
				answer[cnt] = '\0';
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();	C = sc.nextInt();
		arr = new char[C];	answer = new char[L];
		
		for(int c=0; c<C; c++) {
			arr[c] = sc.next().charAt(0);
		}
		Arrays.sort(arr);
		go(0, 0, 0, 0);
	}
}
