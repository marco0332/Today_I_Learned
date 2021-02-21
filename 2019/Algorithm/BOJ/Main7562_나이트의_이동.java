import java.io.*;
import java.util.*;

/**
 * Main7562
 */
public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dr = {-2,-2,-1,-1,1,1,2,2};
        int[] dc = {-1,1,-2,2,-2,2,-1,1};

        int T = Integer.parseInt(br.readLine());
        while(T-- != 0){
            N = Integer.parseInt(br.readLine());
            boolean[][] visited = new boolean[N][N];

            String[] input = br.readLine().split(" ");
            Pos startPoint = new Pos(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

            input = br.readLine().split(" ");
            Pos endPoint = new Pos(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

            Queue<Pos> q = new LinkedList<Pos>();
            q.add(startPoint);
            visited[startPoint.y][startPoint.x] = true;

            int answer = -1;
            boolean flag = false;
            while(!flag){
                answer++;

                int qSize = q.size();
                while(qSize-- != 0 && !flag){
                    Pos cur = q.poll();

                    if(endPoint.isEqual(cur)){
                        flag = true;
                        break;
                    }

                    for(int i=0; i<8; i++){
                        Pos next = new Pos(cur.y + dr[i], cur.x + dc[i]);
                        if(next.isCorrect() && !visited[next.y][next.x]){
                            q.add(next);
                            visited[next.y][next.x] = true;
                        }
                    }
                }
            }
            System.out.println(answer);
        }
    }

    static class Pos {
        int y, x;

        public Pos(int y, int x){
            this.y = y;
            this.x = x;
        }

        public Pos(){
            this.y = -1;
            this.x = -1;
        }

        public boolean isEqual(Pos input){
            if(this.y == input.y && this.x == input.x){
                return true;
            }
            return false;
        }

        public boolean isCorrect(){
            if(this.y < 0 || this.x < 0 || this.y >= N || this.x >= N){
                return false;
            }
            return true;
        }
    }
}