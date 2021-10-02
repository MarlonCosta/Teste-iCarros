package com.icarros.challenges;

import java.util.Arrays;

public class WeAreTheChampions {

    public static int countPoints(int wins, int ties){
        return (wins * 3) + ties;
    }


    public static int getWinnerPoints(int[] wins, int[] ties){
        int [] results = new int[wins.length];

        for (int i = 0; i < wins.length; i++) {
            results[i] = countPoints(wins[i], ties[i]);
        }

        return Arrays.stream(results).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] wins = {1, 3, 2, 0}, ties = {2, 0, 1, 3};

        System.out.println(getWinnerPoints(wins, ties));

    }
}
