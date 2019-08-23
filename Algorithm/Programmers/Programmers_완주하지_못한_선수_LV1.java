import java.util.HashMap;

public class Programmers_완주하지_못한_선수_LV1 {
	public static void main(String[] args) {
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for(int i=0; i<completion.length; i++) {
			if(hm.containsKey(completion[i])) {
				hm.put(completion[i], hm.get(completion[i])+1);
			} else {
				hm.put(completion[i], 1);
			}
		}
		
		String answer = "";
		for(int i=0; i<participant.length; i++) {
			if(!hm.containsKey(participant[i]) || hm.get(participant[i]) == 0) {
				answer = participant[i];
				break;
			} else {
				hm.put(participant[i], hm.get(participant[i]) - 1);
			}
		}
		
		System.out.println(answer);
	}
}
