import java.util.*;
import java.io.*;

public class Main4690_완전세제곱 {
	public static void main(String[] args) {
		int a3 = 0, b3 = 0, c3 = 0, d3 = 0;
		for(int a=2; a<=100; a++) {
			a3 = a*a*a;
			
			for(int b=2; b<=100; b++) {
				b3 = b*b*b;
				if(b3 > a3) break;
				
				for(int c=b+1; c<=100; c++){
					c3 = c*c*c;
					if(b3 + c3 > a3) break;
					
					for(int d=c+1; d<=100; d++) {
						d3 = d*d*d;
						if(b3 + c3 + d3 > a3) break;
						if(b3 + c3 + d3 == a3)
							System.out.println("Cube = "+a+", Triple = ("+b+","+c+","+d+")");
					}
				}
			}
		}
	}
}
