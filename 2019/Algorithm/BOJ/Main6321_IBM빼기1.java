import java.io.*;
import java.util.*;

public class Main6321_IBM»©±â1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		sc.nextLine();
		for(int t=1; t<=T; t++) {
			String input = sc.nextLine();
			
			System.out.println("String #"+t);
			for(int i=0,len=input.length(); i<len; i++) {
				System.out.print((char)((input.charAt(i)-'A'+1)%26+'A'));
			}
			System.out.println();
			System.out.println();
		}
	}
}
