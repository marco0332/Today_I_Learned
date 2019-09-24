import java.util.*;

public class Solution {
	public int[] solution(int []arr) {
        int befoValue = -1;
        ArrayList<Integer> find = new ArrayList<Integer>();
        for(int index=0; index<arr.length; index++) {
            if(arr[index] != befoValue) {
                find.add(arr[index]);
                befoValue = arr[index];
            }
        }
        int[] answer = find.stream().mapToInt(Integer::intValue).toArray();
        return answer;
	}
}