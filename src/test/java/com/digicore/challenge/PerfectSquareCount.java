package com.digicore.challenge;/*
 * @author Okala III
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class PerfectSquareCount {

    public static void main(String[] args) {
        question1();
    }
    public static void question1(){
        System.out.println("\nEnter absolute path of file:");
        Scanner input = new Scanner(System.in);
        String fileLocation = input.nextLine();
        try {
            Files.lines(Paths.get(fileLocation))
                    .forEach(x->{
                        String[] s = x.split(" ");
                        Arrays.stream(s)
                                .filter(y-> PerfectSquareCount.isPerfectSquare(Integer.parseInt(y)))
                                .forEach(System.out::println);
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean isPerfectSquare(int x){
        int sqrt = (int) Math.sqrt(x);
        return Math.pow(sqrt, 2) == x;
    }

}
