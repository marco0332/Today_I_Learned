import java.util.*;

public class Solution2047 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StringBuilder exam = new StringBuilder(sc.next());
		for(int i=0; i<exam.length(); i++) {
			if('a' <= exam.charAt(i) && exam.charAt(i) <= 'z') {
				exam.setCharAt(i, (char)(exam.charAt(i)-32));
			}
		}
		System.out.println(exam);
	}

}
