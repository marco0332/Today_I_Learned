import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        int[] ret = {};
        
        int[][] iter_info = {{1,2,3,4,5}, {2,1,2,3,2,4,2,5}, {3,3,1,1,2,2,4,4,5,5}};
        int[] iter_len = {5, 8, 10};
        int[] cnts = {0, 0, 0};
        int person_len = 3;
        
        for(int iter=0; iter<answers.length; iter++) {
            for(int person=0; person<person_len; person++) {
                if(answers[iter] == iter_info[person][iter%iter_len[person]]){
                    cnts[person]++;
                }
            }
        }
        
        int max_value = 0;
        for(int person=0; person<person_len; person++) {
            if(max_value < cnts[person]){
                max_value = cnts[person];
            }
        }
        
        for(int person=0; person<person_len; person++) {
            if(max_value == cnts[person]){
                answer.add(person+1);
            }
        }
        
        ret = new int[answer.size()];
        for(int iter=0; iter<ret.length; iter++){
            ret[iter] = answer.get(iter);
        }
        
        return ret;
    }
}