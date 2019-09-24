import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> days = new ArrayList<Integer>();
        int cnt = 0;
        int befo = 0;
        for(int index=0; index<speeds.length; index++) {
            int toRelease = (int)Math.ceil((100.0 - progresses[index]) / speeds[index]);
            
            if(toRelease > befo) {
                if(cnt > 0) {
                    days.add(cnt);
                }
                cnt = 1;
                befo = toRelease;
            } else {
                cnt++;
            }
        }
        if(cnt > 0) {
            days.add(cnt);
        }
        
        return days.stream().mapToInt(Integer::intValue).toArray();
    }
}