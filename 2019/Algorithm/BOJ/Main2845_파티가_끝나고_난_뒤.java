import java.io.*;
import java.util.*;

public class Main2845_��Ƽ��_������_��_�� {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int L = sc.nextInt();
		int P = sc.nextInt();
		int answer = L*P;
		for(int i=0; i<5; i++) {
			System.out.print(sc.nextInt()-answer+" ");
		}
	}
}
