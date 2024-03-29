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
        similarity_detection sd = new similarity_detection();
        diff_match_patch dmp = new diff_match_patch();
        String regex = "(//).*|/\\*([^*]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/|\\R| ";
        //전처리
        //주석 제거 | 줄바꿈 제거 | 공백 제거
        String s1 = Files.readString(Paths.get("src/main/java/com/mycompany/app/testFile1.java")).replaceAll(regex,"");
        String s2 = Files.readString(Paths.get("src/main/java/com/mycompany/app/testFile2.java")).replaceAll(regex,"");
        String s3 = Files.readString(Paths.get("src/main/java/com/mycompany/app/testFile3.java")).replaceAll(regex,"");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println();

        System.out.println("변수명 변경");

        //다른 개인 오픈소스 이용한 방법
        System.out.println("개인 오픈소스 (file1, file2 비교 결과): "+Math.round(sd.similarity(s1,s2)*100));

        //google/diff-match-patch 라이브러리를 이용한 방법
        List<diff_match_patch.Diff> lDiffs = dmp.diff_main(s1,s2);
        int Iindex = dmp.diff_levenshtein(lDiffs);
        System.out.println("google/diff-match-patch (file1, file2 비교 결과): "+Math.round(100 - ((double)Iindex/ Math.max(s1.length(),s2.length()))*100));


        System.out.println("\n호출 방법 변경");

        System.out.println("개인 오픈소스 (file1, file3 비교 결과): "+Math.round(sd.similarity(s1,s3)*100));

        lDiffs = dmp.diff_main(s1,s3);
        Iindex = dmp.diff_levenshtein(lDiffs);
        System.out.println("google/diff-match-patch (file1, file3 비교 결과): "+Math.round(100 - ((double)Iindex/ Math.max(s1.length(),s3.length()))*100));
    }

    
}
