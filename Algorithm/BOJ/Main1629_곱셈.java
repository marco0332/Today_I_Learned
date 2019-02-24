import java.io.*;
import java.util.*;

public class Main1629_곱셈 {
	static int C = 0;
	
	public static long power(long a, long b) {
		long ret = 1;
		while(b>0) {
			if(b%2 == 1) {
				ret *= a;
				ret %= C;
				b--;
			}
			a *= a;
			a %= C;
			b /= 2;
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		C = sc.nextInt();
		
		System.out.println(power(A,B));
	}
}
