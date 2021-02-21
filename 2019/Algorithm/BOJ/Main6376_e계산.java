import java.io.*;
import java.util.*;

public class Main6376_e°è»ê {
	public static void main(String[] args) {
		double[] factorial = new double[10];
		double[] sum = new double[10];
		
		factorial[0] = 1;
		sum[0] = 1;
		for(int i=1; i<10; i++) {
			factorial[i] = factorial[i-1]*i;
			sum[i] = sum[i-1] + (1.0/factorial[i]);
		}
		
		System.out.println("n e");
		System.out.println("- -----------");
		for(int i=0; i<2; i++) {
			System.out.println(i+" "+(int)sum[i]);
		}
		System.out.println(2+" "+sum[2]);
		for(int i=3; i<10; i++) {
			System.out.printf("%d %.9f\n",i,sum[i]);
		}
	}
}
