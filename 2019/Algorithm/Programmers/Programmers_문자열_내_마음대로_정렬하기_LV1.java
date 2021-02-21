import java.util.*;

class Solution {
  public String[] solution(String[] strings, int n) {
      ArrayList<String> arr = new ArrayList<String>();
      for(int index=0; index<strings.length; index++) {
          arr.add(strings[index]);
      }
      Collections.sort(arr, new Comparator<String>() {
          @Override
          public int compare(String string1, String string2) {
              int comp = string1.charAt(n) - string2.charAt(n);
              if(comp != 0) {
                  return comp;
              }
              return string1.compareTo(string2);
          }
      });
      
      return arr.toArray(new String[arr.size()]);
  }
}