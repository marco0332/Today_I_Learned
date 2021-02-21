import java.util.Arrays;
import java.util.HashSet;

public class Programmers_전화번호_목록_LV2 {
	public static void main(String[] args) {
		String[] phone_book = {"123", "456", "789"};
		Arrays.sort(phone_book);
		
//		HashSet<String> set = new HashSet<String>();
		boolean flag = true;
		
//		goOut:
//		for(int i=0; i<phone_book.length; i++) {
//			for(int idx=1, len=phone_book[i].length(); idx<=len; idx++) {
//				if(set.contains(phone_book[i].subSequence(0, idx))) {
//					flag = false;
//					break goOut;
//				}
//			}
//			set.add(phone_book[i]);
//		}
		
		for(int i=1; i<phone_book.length; i++) {
			if(phone_book[i].startsWith(phone_book[i-1])) {
				flag = false;
				break;
			}
		}
		
		
		System.out.println(flag);
	}
}
