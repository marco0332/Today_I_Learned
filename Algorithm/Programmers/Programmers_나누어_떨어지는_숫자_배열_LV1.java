import java.util.*;

class Solution {
  public int[] solution(int[] arr, int divisor) {
      ArrayList<Integer> divided = new ArrayList<Integer>();
      for(int index=0; index<arr.length; index++) {
          if(arr[index] % divisor == 0){
              divided.add(arr[index]);
          }
      }
      if(divided.size() == 0){
          divided.add(-1);
      }
      Collections.sort(divided);
      
      int[] answer = divided.stream().mapToInt(Integer::intValue).toArray();
      return answer;
  }
}