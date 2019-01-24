import java.io.*;
import java.util.*;

public class Main5597 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[31];
		for(int i=0; i<28; i++) {
			arr[sc.nextInt()]++;
		}
		
		for(int i=1; i<31; i++) {
			if(arr[i]==0)
				System.out.println(i);
		}
	}
}
