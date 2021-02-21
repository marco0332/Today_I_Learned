import java.io.*;
import java.util.*;

public class Main7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, String> map = new HashMap<String, String>();

        int N;
        N = Integer.parseInt(br.readLine());
        for(int n=0; n<N; n++){
            String[] line = br.readLine().split(" ");
            map.put(line[0], line[1]);
        }

        for(String key : map.keySet()){
            if(map.get(key).equals("enter")){
                bw.write(key+"\n");
            }
        }

        bw.flush();
        bw.close();
    }
}