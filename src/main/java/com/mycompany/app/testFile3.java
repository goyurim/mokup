package com.mycompany.app;
import java.util.*;
public class testFile3 {

    //Main 함수
    public static void main(String args[]) {
        testFile3 T = new testFile3();
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while(tc-- > 0){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            int Gcd = T.getGcd(Math.max(n1,n2), Math.min(n1,n2)); //test

            System.out.println((n1*n2)/Gcd);
        }
        sc.close();
    }
    //최소 공배수 구하는 함수
    private int getGcd(int a, int b){
        if(b == 0 ) return a;
        else return getGcd(b, a%b);
    } 
}
