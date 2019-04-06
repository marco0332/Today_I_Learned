import java.io.*;
import java.util.*;

public class Main17135_캐슬디펜스_시간단축 {
	static int N, M, D, answer;
	static boolean[][] map;
	static int[] archer = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = st.nextToken().charAt(0) == '1' ? true : false;
			}
		}

		select(0, 0);
		System.out.println(answer);
	}

	public static void select(int idx, int cnt) {
		if (cnt == 3) {
			start();
			return;
		}

		for (int i = idx; i < M; i++) {
			archer[cnt] = i;
			select(i + 1, cnt + 1);
		}
	}

	public static void start() {
		boolean[][] tmpMap = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				tmpMap[n][m] = map[n][m];
			}
		}

		int curAns = 0;
		for (int base = N; base > 0; base--) {
			curAns += shoot(tmpMap, base);
		}

		answer = Math.max(answer, curAns);
	}

	public static int shoot(boolean[][] tmpMap, int base) {
		int enemyClear = 0;
		ArrayList<Pos> check = new ArrayList<>();

		for (int a = 0; a < 3; a++) {
			int minN = -1;
			int minM = -1;
            
            boolean flag = false;
            for(int d=1; d<=D; d++){
                int subBase = d-1;
                
                for(int i=0; i<subBase && !flag; i++){
                    int nr = base-1-i;
                    int nc = archer[a]-(subBase-i);
                    
                    if(0<=nr && nr<N && 0<=nc && nc<M && tmpMap[nr][nc]){
                        minN = nr;
                        minM = nc;
                        flag = true;
                        break;
                    }
                }
                if(!flag && 0<=base-d && tmpMap[base-d][archer[a]]){
                    minN = base-d;
                    minM = archer[a];
                    flag = true;
                }
                for(int i=0; i<subBase && !flag; i++){
                    int nr = base-subBase+i;
                    int nc = archer[a]+1+i;
                    
                    if(0<=nr && nr<N && 0<=nc && nc<M && tmpMap[nr][nc]){
                        minN = nr;
                        minM = nc;
                        flag = true;
                        break;
                    }
                }
            }

			if (flag) {
				check.add(new Pos(minN,minM));
			}
		}
		
		for(int i=0,len=check.size(); i<len; i++) {
			if(tmpMap[check.get(i).n][check.get(i).m])
				enemyClear++;
			
			tmpMap[check.get(i).n][check.get(i).m] = false; 
		}

		return enemyClear;
	}
	
	static class Pos {
		int m, n;
		
		public Pos(int n, int m) {
			this.m = m;
			this.n = n;
		}
	}
}