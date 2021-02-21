class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int befo = 0;
        for(int index=0; index<prices.length; index++) {
            if(befo > prices[index]) {
                fill(answer, prices, prices[index], index);
            }
            befo = prices[index];
        }
        int index = prices.length - 1;
        int cur = index;
        while(cur > -1) {
            if(answer[cur] == 0) {
                answer[cur] = index-cur;
            }
            cur--;
        }
        
        return answer;
    }
    
    public void fill(int[] answer, int[] prices, int rule, int index) {
        int cur = index-1;
        while(cur > -1 && prices[cur] > rule) {
            if(answer[cur] == 0) {
                answer[cur] = index-cur;
            }
            cur--;
        }
    }
}