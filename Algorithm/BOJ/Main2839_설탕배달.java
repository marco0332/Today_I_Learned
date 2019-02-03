import java.io.*;
import java.util.*;

public class Main2839_º≥≈¡πË¥ﬁ {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int MIN_VAL = 987654321;
		int N = sc.nextInt();
		for(int i=0; i<=N/5; i++) {
			for(int j=0; j<=N/3; j++) {
				if(i*5 + j*3 == N && MIN_VAL > i+j) MIN_VAL = i+j;
			}
		}
		
		if(MIN_VAL == 987654321) System.out.println(-1);
		else System.out.println(MIN_VAL);
	}
}
