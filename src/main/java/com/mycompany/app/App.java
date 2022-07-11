/*----------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *---------------------------------------------------------------------------------------*/
package com.mycompany.app;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class App {
    public static void main(String[] args) throws IOException{
        App test = new App();
        diff_match_patch dmp = new diff_match_patch();
        //전처리
        //주석 제거 | 줄바꿈 제거 | 공백 제거
        String s1 = Files.readString(Paths.get("src/main/java/com/mycompany/app/testFile1.java")).replaceAll("(//).*|/\\*([^*]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/|\\R| ","");
        String s2 = Files.readString(Paths.get("src/main/java/com/mycompany/app/testFile2.java")).replaceAll("(//).*|/\\*([^*]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/|\\R| ","");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println();
        //다른 개인 오픈소스 이용한 방법
        System.out.println("개인 오픈소스 결과: "+Math.round(test.similarity(s1,s2)*100) + ", " + test.similarity(s1,s2)*100);

        //google/diff-match-patch 라이브러리를 이용한 방법
        List<diff_match_patch.Diff> lDiffs = dmp.diff_main(s1,s2);
        int Iindex = dmp.diff_levenshtein(lDiffs);
        System.out.println("google/diff-match-patch 결과: "+Math.round(100 - ((double)Iindex/ Math.max(s1.length(),s2.length()))*100) + ", " + (100 - ((double)Iindex/ Math.max(s1.length(),s2.length()))*100));
    }

    private double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        
        if (s1.length() < s2.length()) {
            longer = s2; 
            shorter = s1;
        }
        
        int longerLength = longer.length();
        if (longerLength == 0) return 1.0;
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }
    private int editDistance(String s1, String s2) {
        // s1 = s1.toLowerCase();
        // s2 = s2.toLowerCase();
        int[] costs = new int[s2.length() + 1];
        
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }
                        
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            
            if (i > 0) costs[s2.length()] = lastValue;
        }
        
        return costs[s2.length()];
    }
}
