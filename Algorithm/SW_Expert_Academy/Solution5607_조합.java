import java.io.*;
import java.util.*;

public class Solution5607_조합 {
	static final int mod = 1234567891;
	
	public static long power(long a, long b) {
		long ret = 1;
		while(b>0) {
			if(b%2 == 1) {
				ret *= a;
				ret %= mod;
			}
			a *= a;
			b /= 2;
			a %= mod;
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		long[] fact = new long[1000001];
		long[] inv = new long[1000001];
		
		fact[0] = 1;
		fact[1] = 1;
		for(int i=2; i<=1000000; i++) {
			fact[i] = (fact[i-1]*i) % mod;
		}
		inv[1000000] = power(fact[1000000], mod-2);
		for(int i=999999; i>=0; i--) {
			inv[i] = (inv[i+1] * (i+1)) % mod;
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			
			long answer = (fact[x]*inv[x-y]) % mod;
			answer = (answer * inv[y]) % mod;
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
	}
}
