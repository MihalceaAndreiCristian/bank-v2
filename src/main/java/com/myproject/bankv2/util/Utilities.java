package com.myproject.bankv2.util;

public class Utilities {

    public static String generateBankAccount(String currency){
        String finalNumber ="";

        finalNumber += currency.substring(0,2);
        int a =randomNumber(0,100);
        if (a<10){
            finalNumber += "0" + a ;
        }else{
            finalNumber += a;
        }
        finalNumber += "BANKACRT";
        int b = randomNumber(10000000,99999999);
        finalNumber += b;

        return finalNumber.toUpperCase();
    }
    public static int randomNumber(int min, int max){
        return (int) (Math.random()*(max-min+1)+min);
    }

    public static String generateCardNumber(){
        String finalNumber= "" ;
        Integer a = randomNumber(1000,9999);
        Integer b = randomNumber(1000,9999);
        Integer c = randomNumber(1000,9999);
        Integer d = randomNumber(1000,9999);
        finalNumber += a.toString();
        finalNumber += b.toString();
        finalNumber += c.toString();
        finalNumber += d.toString();
        return finalNumber;
    }

    public static String randomNumberCCV(int min, int max){
        String result ="";
        int a= (int) (Math.random()*(max-min+1)+min);
        if (a <0){
            a *= -1;
        }
        if (a <10){
            return result ="00"+ a;
        }else if (a>9 && a<99){
            return result = "0" + a;
        }else {
            return result.valueOf(a);

        }
    }

    public static long randomNumberLong(long min, long max){
        return (long) (Math.random()*(max-min+1)+min);
    }


}
