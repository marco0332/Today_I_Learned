import java.io.*;
import java.util.*;
 
public class Main{
     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         int T = sc.nextInt();
         
         for(int t=0; t<T; t++){
             int val = 0;
             int sum = 0;
             val = sc.nextInt();
             
             if(val == 0) ;
             else if(val == 1) sum += 5000000;
             else if(val <= 3) sum += 3000000;
             else if(val <= 6) sum += 2000000;
             else if(val <= 10) sum += 500000;
             else if(val <= 15) sum += 300000;
             else if(val <= 21) sum += 100000;
             
             val = sc.nextInt();
             if(val == 0) ;
             else if(val <= 1) sum += 5120000;
             else if(val <= 3) sum += 2560000;
             else if(val <= 7) sum += 1280000;
             else if(val <= 15) sum += 640000;
             else if(val <= 31) sum += 320000;
             
             System.out.println(sum);
         }
     }
}