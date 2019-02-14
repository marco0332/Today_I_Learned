import java.io.*;
import java.util.*;

public class Solution1266_소수완제품확률 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> prime = new ArrayList<Integer>();
		prime.add(2);
		prime.add(3);
		prime.add(5);
		prime.add(7);
		prime.add(11);
		prime.add(13);
		prime.add(17);
		long[] fact = new long[20];
		
		fact[0] = fact[1] = 1;
		for(int i=2; i<20; i++) {
			fact[i] = fact[i-1]*i;
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			double p1 = Integer.parseInt(input[0])/100.0;
			double p2 = Integer.parseInt(input[1])/100.0;
			
			double total1 = 0, total2 = 0;
			double val1 = 0, val2 = 0;
			for(int i=0; i<=18; i++) {
				double nCr = (fact[18]/fact[i]/fact[18-i]);
				double tmp1 = nCr*Math.pow(p1, i)*Math.pow((1-p1), 18-i);
				total1 += tmp1;
				
				double tmp2 = nCr*Math.pow(p2, i)*Math.pow((1-p2), 18-i);
				total2 += tmp2;
				
				if(prime.contains(i)) continue;
				val1 += tmp1;
				val2 += tmp2;
			}
			
			double answer = 1 - (val1/total1) * (val2/total2);
			System.out.printf("#%d %.6f\n",t,answer);
		}
	}
}
