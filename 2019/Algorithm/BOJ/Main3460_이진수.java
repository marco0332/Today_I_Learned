import java.io.*;
import java.util.*;

public class Main3460_ÀÌÁø¼ö {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int val = 0, modV = 0, divV = 0;
		for(int t=0; t<T; t++) {
			val = sc.nextInt();
			
			int idx = 0;
			while(true) {
				modV = val%2;
				divV = val/2;
				
				if(modV==0 && divV==0) break;
				if(modV == 1) System.out.print(idx+" ");
				idx++;
				val = divV;
			}
		}
	}
}
