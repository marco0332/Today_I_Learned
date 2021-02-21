import java.io.*;
import java.util.*;

public class Main6378_디지털_루트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int sum = 0, tmp = 0, val = 0;
			String valS = sc.nextLine();
			if(valS.length() == 1 && Integer.parseInt(valS) == 0) break;
			
			for (int i = 0, size = valS.length(); i < size; i++) {
				val += valS.charAt(i)-'0';
			}
			
			while(val >= 10) {
				tmp = val;
				sum = 0;
				
				while(tmp > 0) {
					sum += tmp % 10;
					tmp /= 10;
				}
				val = sum;
			}
			System.out.println(val);
		}
	}
}
