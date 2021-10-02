package com.icarros.challenges;

public class CreepyHours {

    public static boolean isCreepy(String hour){
        //AB:BA e AA:AA
        if (hour.equals(new StringBuffer(hour).reverse().toString())){
            return true;        //Chegagem de palíndromo checa ambos os casos
        }

        boolean hhiguais = hour.substring(0, 1).equals(hour.substring(1, 2));
        boolean hhigualmm = hour.substring(0, 2).equals(hour.substring(3, 5));
        boolean mmiguais = hour.substring(3, 4).equals(hour.substring(4, 5));
        boolean higualm = hour.substring(0, 1).equals(hour.substring(3, 4));

        //AB:AB
        if (hhigualmm //Dígitos de hora iguais os dígitos de minuto
                && !hhiguais){ //Dígitos de hora diferentes
            return true;
        }

        //AA:BB
        return hhiguais //Digitos de hora iguais
                && mmiguais //Dígitos de minutos iguais
                && !(higualm); // Dígito de hora diferente do dígito de minuto
    }

    public static int countCreepyHours(String[] hours){
        int count = 0;

        for (String hour :
                hours) {
            if (isCreepy(hour)) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        String[] input = new String[]{"11:00", "10:01", "12:12", "01:23"};

        System.out.println(countCreepyHours(input));
    }
}
