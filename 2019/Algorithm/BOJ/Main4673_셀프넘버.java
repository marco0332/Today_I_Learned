import java.io.*;
import java.util.*;

public class Main4673_¼¿ÇÁ³Ñ¹ö {
	static boolean[] check = new boolean[10001];

	public static void go(int cur) {
		if (!check[cur])
			System.out.println(cur);

		int val = cur;
		while (cur > 0) {
			val += cur % 10;
			cur /= 10;
		}
		if (val <= 10000)
			check[val] = true;
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 10000; i++) {
			go(i);
		}
	}
}
