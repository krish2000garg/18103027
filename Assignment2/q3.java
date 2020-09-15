import java.util.*;
public class q3{
    public static void main(String[] args){

        Scanner obj = new Scanner(System.in);

        System.out.println("enter string");
        String s = obj.nextLine();
        long int d, x, y;
        char[] chars = s.toCharArray();
        long int n=chars.length;
        char temp;
        char minm;

        for(int i=0;i<n-1;i++){
            x = chars[i];
            d = i;
            for(int j=i+1;j<chars.length;j++){
                y = chars[j];
//                 
                if(x>y){
                    d = j;
                    x = y;
                }
            }
            temp = chars[i];
            chars[i] = chars[d];
            chars[d] = temp;
        }
        System.out.println(chars);
    }
}