import java.util.Scanner;

public class Solution2056 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		String[] months = {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}; 
		String[] m_days = {"", "31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			String Date = sc.next();
			
			if(Date.length() != 8) System.out.println("#"+t+" "+"-1");
			else {
				String ans = new String();
				ans = ans + Date.substring(0, 4);
				
				String month = Date.substring(4, 6);
				String day = Date.substring(6, 8);
//				System.out.println(month+" "+day);
				
				boolean check = false;
				for(int i=1; i<=12; i++) {
					if(month.compareTo(months[i]) == 0) {
						if(day.compareTo(m_days[i]) <= 0) {
							check = true;
							ans += '/' + month + '/' + day;
							break;
						}
						else
							break;
					}
				}
				System.out.print("#"+t+" ");
				if(check == false) System.out.println("-1");
				else System.out.println(ans);
			}
		}
	}
}
