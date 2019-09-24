import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Print> queue = new LinkedList<Print>();
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
        for(int index=0; index<priorities.length; index++) {
            queue.offer(new Print(priorities[index], index));
            pQueue.add(-priorities[index]);
        }
        
        int answer = 1;
        int maxVal = -pQueue.poll();
        while(true) {
            Print cur = queue.poll();
            
            if(cur.priority == maxVal) {
                if(cur.index == location) {
                    break;
                } else {
                    maxVal = -pQueue.poll();
                    answer++;
                }
            } else {
                queue.offer(cur);
            }
        }
        
        return answer;
    }
}

class Print {
    int priority;
    int index;
    
    public Print(int priority, int index) {
        this.priority = priority;
        this.index = index;
    }
}