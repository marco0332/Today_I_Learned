import java.io.*;
import java.util.*;

public class Main5532_방학숙제 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int L = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		
		int divA = (int) Math.ceil((double)A/C);
		int divB = (int) Math.ceil((double)B/D);
		System.out.println(L-Math.max(divA, divB));
	}
}
