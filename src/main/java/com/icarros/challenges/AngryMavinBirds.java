package com.icarros.challenges;

public class AngryMavinBirds {

    public static int countPoints(String[] results){
        int total = 0;

        for (String result :
                results) {
            total += result.chars().filter(ch -> ch == '*').count();
        }

        return total;
    }

    public static void main(String[] args) {
        String[] input = new String[]{"*--", "***", "---"};

        System.out.println(countPoints(input));
    }
}
