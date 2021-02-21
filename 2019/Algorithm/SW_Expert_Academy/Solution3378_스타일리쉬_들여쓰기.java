import java.io.*;
import java.util.*;

public class Solution3378_스타일리쉬_들여쓰기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int p, q;
		int[] blockCnt, rcsCur, ansRCS;
		int[][] rcsCnt;
		String[] pList, qList;
		ArrayList<RCSD> arr;

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				preprocess();
				countFunc();
				findRCS(0);
				System.out.println(rcsCnt[0][0] + " " + rcsCnt[1][0] + " " +  rcsCnt[2][0]+" " +Arrays.toString(ansRCS));

				bw.write("#" + t);
				findAnswer();
				bw.write("\n");
			}
		}

		public void preprocess() throws IOException {
			ansRCS = new int[3];
			blockCnt = new int[3];
			arr = new ArrayList<>();
			rcsCnt = new int[3][21];
			rcsCur = new int[3];

			String[] line = br.readLine().split(" ");
			p = Integer.parseInt(line[0]);
			q = Integer.parseInt(line[1]);

			pList = new String[p];
			qList = new String[q];
			for (int i = 0; i < p; i++) {
				pList[i] = br.readLine();
			}
			for (int i = 0; i < q; i++) {
				qList[i] = br.readLine();
			}
		}

		public void countFunc() {
			int dotCnt = 0;
			boolean check = true, checkBefo = true;
			for (int i = 0; i < p; i++) {
				dotCnt = 0;
				check = true;
				checkBefo = true;
				int[] blockCntTmp = new int[3];

				for (int j = 0, len = pList[i].length(); j < len; j++) {
					for (int k = 0; k < 3; k++) {
						blockCntTmp[k] = blockCnt[k];
					}

					if (check && pList[i].charAt(j) == '.') {
						dotCnt++;
					} else if (pList[i].charAt(j) == '(') {
						check = false;
						blockCnt[0]++;
					} else if (pList[i].charAt(j) == ')') {
						check = false;
						blockCnt[0]--;
					} else if (pList[i].charAt(j) == '{') {
						check = false;
						blockCnt[1]++;
					} else if (pList[i].charAt(j) == '}') {
						check = false;
						blockCnt[1]--;
					} else if (pList[i].charAt(j) == '[') {
						check = false;
						blockCnt[2]++;
					} else if (pList[i].charAt(j) == ']') {
						check = false;
						blockCnt[2]--;
					} else {
						check = false;
					}

					if (!check && checkBefo) {
						arr.add(new RCSD(blockCntTmp[0], blockCntTmp[1], blockCntTmp[2], dotCnt));
					}

					checkBefo = check;
				}
			}
		}

		public void findRCS(int idx) {
			if (idx == 3) {
				boolean flag = true;
				for (int i = 0, size = arr.size(); i < size; i++) {
					int sum = arr.get(i).r * rcsCur[0] + arr.get(i).c * rcsCur[1] + arr.get(i).s * rcsCur[2];

					if (sum != arr.get(i).d) {
						flag = false;
						break;
					}
				}

				if (flag) {
					for (int i = 0; i < 3; i++) {
						if (rcsCnt[i][rcsCur[i]] == 0) {
							rcsCnt[i][rcsCur[i]]++;
							rcsCnt[i][0]++;
							ansRCS[i] = rcsCur[i];
						}
					}
				}
				return;
			}

			for (int i = 1; i < 21; i++) {
				rcsCur[idx] = i;
				findRCS(idx + 1);
			}
		}

		public void findAnswer() throws IOException {
			int dotCnt = 0;
			boolean check = true;
			for (int i = 0; i < q; i++) {
				dotCnt = 0;
				check = true;

				int ret = 0;
				for (int k = 0; k < 3; k++) {
					if (ret != -1 && blockCnt[k] > 0) {
						if (rcsCnt[k][0] == 1) {
							ret += blockCnt[k] * ansRCS[k];
						} else {
							ret = -1;
						}
					}
				}
				
				for(int k=0, size=arr.size(); k<size; k++) {
					if(ret == -1 && arr.get(k).r == blockCnt[0] && arr.get(k).c == blockCnt[1] && arr.get(k).s == blockCnt[2]) {
						ret = arr.get(k).d;
						break;
					}
				}

				bw.write(" " + ret);
//					rcsCnt[0][0] + rcsCnt[1][0] + rcsCnt[2][0] +Arrays.toString(ansRCS));
//					bw.write(" "+);

				for (int j = 0, len = qList[i].length(); j < len; j++) {
					if (check && qList[i].charAt(j) == '.') {
						dotCnt++;
					} else if (qList[i].charAt(j) == '(') {
						check = false;
						blockCnt[0]++;
					} else if (qList[i].charAt(j) == ')') {
						check = false;
						blockCnt[0]--;
					} else if (qList[i].charAt(j) == '{') {
						check = false;
						blockCnt[1]++;
					} else if (qList[i].charAt(j) == '}') {
						check = false;
						blockCnt[1]--;
					} else if (qList[i].charAt(j) == '[') {
						check = false;
						blockCnt[2]++;
					} else if (qList[i].charAt(j) == ']') {
						check = false;
						blockCnt[2]--;
					} else {
						check = false;
					}
				}
			}
		}
	}

	static class RCSD {
		int r, c, s, d;

		public RCSD(int r, int c, int s, int d) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
		}
	}
}
