class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int[] order = new int[26];
        for(int index=0, length=skill.length(); index<length; index++) {
            order[skill.charAt(index) - 'A'] = index + 1;
        }
        
        for(int query=0; query<skill_trees.length; query++) {
            int number = 1;
            
            boolean flag = true;
            for(int index=0, length=skill_trees[query].length(); index<length; index++) {
                int check = order[skill_trees[query].charAt(index) - 'A'];
                if(check == 0) {
                    continue;
                }
                
                if(check == number) {
                    number++;
                } else {
                    flag = false;
                    break;
                }
            }
            
            answer += flag == true ? 1 : 0;
        }
        
        return answer;
    }
}