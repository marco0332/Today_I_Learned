import java.util.*;
import java.io.*;

public class Main1924_2007³â {
	final static String[] week = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
	final static int[] month = {31,31,28,31,30,31,30,31,31,30,31,30};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		
		if(x==1) System.out.println(week[y%7]);
		else {
			int day = 0;
			for(int i=1; i<x; i++) {
				day += month[i];
			}
			day += y;
			
			System.out.println(week[day%7]);
		}
	}
}
