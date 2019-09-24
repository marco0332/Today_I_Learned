class Solution {
  public String solution(int a, int b) {
      final int[] DAYS = {0, 31,29,31,30,31,30,31,31,30,31,30,31};
      final String[] DAY_OF_WEEK = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
      
      int sumDay = b;
      for(int month=1; month<a; month++) {
          sumDay += DAYS[month];
      }
      
      String answer = DAY_OF_WEEK[(sumDay-1)%7];
      
      return answer;
  }
}