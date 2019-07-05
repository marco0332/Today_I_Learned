import java.io.*;
import java.util.*;

public class Main1620 {

    static String[] dict;
    static int N;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        dict = new String[N+1];
        map = new HashMap<String, Integer>();
        for(int index=1; index<=N; index++){
            dict[index] = br.readLine();
            map.put(dict[index], index);
        }

        for(int queryIndex=0; queryIndex<M; queryIndex++){
            bw.write(go(br.readLine()));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
    
    public static String go(String query){
        if(isInteger(query)){
            return dict[Integer.parseInt(query)];
        } else{
            return Integer.toString(find(query));
        }
    }

    public static Integer find(String query){
        return map.get(query);
    }

    public static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
        } catch(NumberFormatException e){
            return false;
        } catch(NullPointerException e){
            return false;
        }
        return true;
    }
}