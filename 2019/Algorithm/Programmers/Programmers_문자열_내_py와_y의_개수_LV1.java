class Solution {
    boolean solution(String s) {
        s = s.toLowerCase();
        int cntP = 0;
        int cntY = 0;
        
        for(int index=0, length=s.length(); index<length; index++) {
            if(s.charAt(index) == 'y') {
                cntY++;
            } else if(s.charAt(index) == 'p') {
                cntP++;
            }
        }

        return cntY == cntP ? true : false;
    }
}