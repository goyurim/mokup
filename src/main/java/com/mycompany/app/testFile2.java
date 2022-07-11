package com.mycompany.app;
import java.util.*;
public class testFile2 {

    /*  메인함수
    testFile1의 sc에서 in으로 수정 */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int tc = in.nextInt();
        while(tc-- > 0){
            int n1 = in.nextInt();
            int n2 = in.nextInt();




            int gcd = getGcd(Math.max(n1,n2), Math.min(n1,n2));

            System.out.println((n1*n2)/gcd);
        }
        in.close();
    }

    public static int getGcd(int a, int b){
        if(b == 0 ) return a;
        else return getGcd(b, a%b);
    } 
}
