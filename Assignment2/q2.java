import java.util.Scanner;
public class q2{
    public static void main(String[] args){

        Scanner obj = new Scanner(System.in);

        System.out.print("enter size ");

        long int n = obj.nextInt();

        long int[] arr = new int[n];

        System.out.println("Enter the array elements in range 0-20");

        long int[] freq = new int[21];

        for(int i=0;i<n;i++){
            arr[i] = obj.nextInt();

            if(arr[i]<0 || arr[i]>20){
                freq[0]++;
            }
            else{
                freq[arr[i]]++;
            }
        }
        long int j=0;
        for(int i=0;i<21;i++){
            for(int k=0;k<freq[i];k++){
                arr[j] = i;
                j++;
            }
        }
        System.out.print("Sorted array is as shown: ");
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }
}