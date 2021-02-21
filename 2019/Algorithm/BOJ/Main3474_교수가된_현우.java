import java.io.*;
import java.util.*;

public class Main3474_교수가된_현우 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int N = 0;
		int d2 = 0, d5 = 0, tmp = 0;
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			d2 = 0; d5 = 0;

			for (int i = 2; i <= N; i *= 2) {
	            d2 += N / i;
	        }
	 
	        for (int i = 5; i <= N; i *= 5) {
	            d5 += N / i;
	        }
			System.out.println(Integer.min(d2, d5));
		}
	}
}
