import java.io.*;
import java.util.*;

public class Main2577_숫자의개수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] mem = new int[10];
		
		int val = 1;
		for(int i=0; i<3; i++) {
			val *= sc.nextInt();
		}
		
		int cur = 0;
		while(val > 0) {
			cur = val%10;
			val /= 10;
			
			mem[cur]++;
		}
		
		for(int i=0; i<10; i++) {
			System.out.println(mem[i]);
		}
	}
}
