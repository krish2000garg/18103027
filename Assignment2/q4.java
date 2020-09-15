public class q4{

    public static void main(String[] args){

        long int total=1;
        long int n = 1;
        while(n<Integer.MAX_VALUE){
            long int x=n*n;
            if(x==total)
                System.out.println("ans is "+n);
            total++;
            n++;
        }
        System.out.println("sum: "+sum+" n: "+n);
    }
}