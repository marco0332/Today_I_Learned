import java.io.*;

/**
 * Main5052_전화번호목록
 */
public class Main5052_전화번호목록 {

    static final int NUMBER_LIMIT = 10;
    static final String YES = "YES", NO = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            boolean isOverWrite = false;
            Trie trie = new Trie();

            for(int iter=0; iter<N; iter++){
                String number = br.readLine();

                if(!isOverWrite && trie.push(number)){
                    isOverWrite = true;
                }
            }

            if(!isOverWrite){
                System.out.println(YES);
            } else{
                System.out.println(NO);
            }
        }
    }

    public static class Trie {
        Trie[] children;
        boolean terminal;

        public Trie() {
            children = new Trie[NUMBER_LIMIT];
            terminal = false;
        }

        public boolean push(String number){
            Trie curNode = this;
            boolean isOverWrite = true;

            for(int iter=0, size=number.length(); iter<size; iter++){
                int key = number.charAt(iter) - '0';

                if(curNode.children[key] == null){
                    curNode.children[key] = new Trie();
                    isOverWrite = false;
                }

                curNode = curNode.children[key];
                if(curNode.terminal){
                    break;
                }
            }
            curNode.terminal = true;

            return isOverWrite;
        }
    }
}