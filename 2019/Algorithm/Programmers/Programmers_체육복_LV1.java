import java.util.*;

class Solution {
    int answer = 0;
    boolean[] check_reserve;
    boolean[] check_lost;
    
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        check_reserve = new boolean[reserve.length];
        check_lost = new boolean[lost.length];
        for(int iter=0; iter<reserve.length; iter++) {
            check_reserve[iter] = true;
        }
        for(int iter=0; iter<reserve.length; iter++) {
            for(int l_iter=0; l_iter<lost.length; l_iter++) {
                if(lost[l_iter] == reserve[iter]) {
                    check_reserve[iter] = false;
                    check_lost[l_iter] = true;
                    break;
                }
            }
        }
        
        
        go(n, lost, reserve, 0);
        return answer;
    }
    
    public void go(int cnt, int[] lost, int[] reserve, int lost_idx) {
        if(lost_idx == lost.length){
            if(cnt > answer){
                answer = cnt;
            }
            return;
        }
        
        if(!check_lost[lost_idx]) {
            for(int iter=0; iter<reserve.length; iter++) {
                if(check_reserve[iter]) {
                    if(lost[lost_idx]+1 == reserve[iter] || lost[lost_idx]-1 == reserve[iter]) {
                        check_reserve[iter] = false;
                        check_lost[lost_idx] = true;
                        go(cnt, lost, reserve, lost_idx+1);
                        check_lost[lost_idx] = false;
                        check_reserve[iter] = true;
                    }
                }
            }

            go(cnt-1, lost, reserve, lost_idx+1);
        } else {
            go(cnt, lost, reserve, lost_idx+1);
        }
    }
}