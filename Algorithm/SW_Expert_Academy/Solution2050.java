import java.util.Scanner;

public class Solution2050 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		for(int i=0; i<input.length(); i++) {
			System.out.print((input.charAt(i)-'A'+1)+" ");
		}
	}
}
