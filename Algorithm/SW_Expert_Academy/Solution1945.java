import java.io.*;
import java.util.*;

public class Solution1945 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int val = sc.nextInt();
			
			int[] div = {2,3,5,7,11};
			int[] cDiv = new int[5];
			while(val > 1) {
				for(int i=0; i<5; i++) {
					while(val%div[i] == 0) {
						cDiv[i]++;
						val /= div[i];
					}
				}
			}
			
			System.out.printf("#%d ",t);
			for(int i=0; i<5; i++) {
				System.out.printf(cDiv[i]+" ");
			}
			System.out.println();
		}
	}
}
