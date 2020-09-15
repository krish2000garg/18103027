import java.util.Scanner;
public class q1{
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);

        System.out.print("Enter the 2strings ");
        String s1;
        String s2;
        s1 = obj.nextLine();
        s2 = obj.nextLine();
        boolean checkeq = false;
        long int n1 = s1.length();
        long int n2 = s2.length();
        long int n;

        if(n1>=n2)
            n=n2;
        else
            n=n1;

        long int i = 0;
        while(i<n){

            long int x = s1.charAt(i), 
            long int y = s2.charAt(i);
//             
            if(x<y){
                checkeq = true;
                System.out.println("Smaller string: "+s1);
                System.out.println("Larger string: "+s2);
                break;
            }

            else if(y<x){
                checkeq = true;
                System.out.println("Smaller string: "+s2);
                System.out.println("Larger string: "+s1);
                break;
            }
            i++;
        }

        if(checkeq){
            if(n2>n){
                System.out.println("Smaller string: "+s1);
                System.out.println("Larger string: "+s2);
            }
            else if(n1>n){
                System.out.println("Smaller string: "+s2);
                System.out.println("Larger string: "+s1);
            }
            else{
                System.out.println(s1 +" and "+ s2 +" are equal");
            }
        }

    }
}
