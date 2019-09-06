import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int iter=0; iter<commands.length; iter++) {
            answer[iter] = find(array, commands[iter][0] - 1, commands[iter][1] - 1, commands[iter][2] - 1);
        }
        
        return answer;
    }
    
    public int find(int[] array, int start, int end, int k) {
        int[] ret = new int[end-start+1];
        
        int idx = 0;
        for(int iter=start; iter<=end; iter++) {
            ret[idx++] = array[iter];
        }
        Arrays.sort(ret);
        
        return ret[k];
    }
}