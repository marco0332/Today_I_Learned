import java.io.*;

public class Main16951_블록놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);

        line = br.readLine().split(" ");
        int[] query = new int[N];
        int[] dist = new int[N];
        for(int index=0; index<N; index++){
            query[index] = Integer.parseInt(line[index]);
            dist[index] = index * K;
        }

        int answer = Integer.MAX_VALUE;
        for(int a0=1; a0<=1000; a0++){
            int cnt = 0;

            for(int index=0; index<N; index++){

                if(a0 + dist[index] != query[index]){
                    cnt++;
                }
            }

            answer = answer > cnt ? cnt : answer;
        }

        if(answer == Integer.MAX_VALUE){
            answer = 0;
        }

        System.out.println(answer);
    }
}