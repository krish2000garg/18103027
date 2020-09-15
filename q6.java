import java.util.Scanner;
public class p6{
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);

        System.out.print("send number: ");
        int n = obj.nextInt();

        System.out.println("seq " + n);
        long int c = 0;
        while(n>0 && n<Integer.MAX_VALUE && n!=1 && c<Integer.MAX_VALUE ){
            if(n%2==0)
                n=n/2;
            else{
                n*=3;
                n++;
            }
            c++;
            System.out.println(n);
        }
        if(n==1){
            System.out.println("No of steps: "+c);
        }
        else if(n!=1 && n==Integer.MAX_VALUE || n<0){
            System.out.println("Integer overflow reached");
        }
        else{
            System.out.println("No of steps maxed out.");
        }
    }
}