package com.mycompany.app;
import java.util.*;
public class testFile1 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while(tc-- > 0){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            int gcd = getGcd(Math.max(n1,n2), Math.min(n1,n2));

            System.out.println((n1*n2)/gcd);
        }
        sc.close();
    }

    public static int getGcd(int a, int b){
        if(b == 0 ) return a;
        else return getGcd(b, a%b);
    } 
}
