import java.io.*;
import java.util.*;

public class Main1053_피보나치 {
	final static int MOD = 10000;

	static class Matrix {
		int a, b, c, d;

		public Matrix(int a, int b, int c, int d) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}
	}

	static public Matrix multi(Matrix A, Matrix B) {
		int tmpA, tmpB, tmpC, tmpD;

		tmpA = (A.a * B.a) + (A.b * B.c);
		tmpB = (A.a * B.b) + (A.b * B.d);
		tmpC = (A.c * B.a) + (A.d * B.c);
		tmpD = (A.c * B.b) + (A.d * B.d);

		return new Matrix(tmpA%MOD, tmpB%MOD, tmpC%MOD, tmpD%MOD);
	}

	static public int power(int N) {
		Matrix ret = new Matrix(1, 0, 0, 1);
		Matrix X = new Matrix(1, 1, 1, 0);
		while (N > 0) {
			if ((N & 1) == 1) {
				ret = multi(ret, X);
			}
			N /= 2;
			X = multi(X, X);
		}
		return ret.b;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			int N = Integer.parseInt(br.readLine());
			int ret = 0;
			if (N == -1)
				break;
			else if (N == 0)
				ret = 0;
			else {
				ret = power(N);
			}
			bw.write(ret + "\n");
		}
		bw.close();
	}
}
