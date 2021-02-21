import java.io.*;
import java.math.BigInteger;

public class Solution3459_승자_예측하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] names = {"Alice", "Bob"};
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringBuilder sb = new StringBuilder();
			BigInteger N = new BigInteger(br.readLine());
			int idx = 1;
			
			if(N.equals(BigInteger.valueOf(1)))
				sb.append("#"+t+" "+names[idx]);
			else {
				BigInteger r = BigInteger.valueOf(4);
				BigInteger befo = BigInteger.valueOf(1);
				BigInteger x = BigInteger.valueOf(1);
				for(int i=1; ; i++) {
					x = x.add(r);
					idx++;
					if(befo.compareTo(N) == -1 && (x.compareTo(N) == 1 || x.compareTo(N) == 0))
						break;
					
					befo = x;
					x = x.add(r);
					idx++;
					if(befo.compareTo(N) == -1 && (x.compareTo(N) == 1 || x.compareTo(N) == 0))
						break;
					
					r = r.multiply(BigInteger.valueOf(4));
				}
				sb.append("#"+t+" "+names[idx%2]);
			}
			
			System.out.println(sb);
		}
	}
}
