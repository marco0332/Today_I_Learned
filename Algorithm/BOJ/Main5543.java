import java.io.*;
import java.util.*;

public class Main5543 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[5];
		for(int i=0; i<5; i++) {
			arr[i] = sc.nextInt();
		}
		
		int Min = 987654321;
		for(int i=0; i<3; i++) {
			for(int j=3; j<5; j++) {
				int val = arr[i] + arr[j];
				if(val-50 < Min)
					Min = val-50;
			}
		}
		System.out.println(Min);
	}
}
