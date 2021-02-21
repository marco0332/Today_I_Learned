import java.io.*;
import java.util.*;

public class Main6679_싱기한_네자리 {
	public static int Ten(int num) {
		int sum = 0;
		while(num > 0) {
			sum += num%10;
			num /= 10;
		}
		return sum;
	}
	
	public static int Twv(int num) {
		int sum = 0;
		while(num > 0) {
			sum += num%12;
			num /= 12;
		}
		return sum;
	}
	
	public static int Sixt(int num) {
		int sum = 0;
		while(num > 0) {
			sum += num%16;
			num /= 16;
		}
		return sum;
	}
	
	public static void check(int num) {
		int ten = Ten(num);
		int twv = Twv(num);
		int sixt = Sixt(num);
		
		if(ten == twv && twv == sixt)
			System.out.println(num);
	}
	
	public static void main(String[] args) {
		for(int i=1000; i<10000; i++) {
			check(i);
		}
	}
}
