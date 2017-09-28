package com.acme.edu;

public class Logger {


    //region const
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String REF_PREFIX = "reference: ";
    private static final String STRING_PREF = "string: ";
    private static int sumOfInt = 0;
    private static int state = 0;//1-int 2-byte 3-string
    private static int countOfDuplicateStrings = 0;
    private static String rememberString;


    //endregion

    public static void log(byte message) {
        print(PRIMITIVE_PREFIX + "\n" + message);
    }

    public static void log(int message) {
        if (state != 1){
            sumOfInt = message;
            state = 1;
        } else if (state == 1) {
            boolean moreThenMax = (long) sumOfInt + message > (long) Integer.MAX_VALUE;
            boolean lessThenMin = (long) sumOfInt + message < (long) Integer.MIN_VALUE;
            if (moreThenMax || lessThenMin){
            if (moreThenMax){
                sumOfInt = message - (Integer.MAX_VALUE - sumOfInt);
                System.out.println(Integer.MAX_VALUE);
            } else if(lessThenMin) {
                System.out.println(Integer.MIN_VALUE);
                sumOfInt = message - (Integer.MAX_VALUE - sumOfInt);
            }
        } else {
            sumOfInt += message;
        }
        }

//        if (state != 1){
//            sumOfInt = 0;
//            state = 1;
//        }
//
//        //print(PRIMITIVE_PREFIX + message + "\n");
    }

    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    public static void log(char message) {
        print(CHAR_PREFIX +"\n" + message);
    }

    public static void log(String message) {

        if (state == 0){
            System.out.println(message);
            rememberString = message;
            countOfDuplicateStrings = 1;
            state = 3;
        } else if (state != 3) {
            System.out.println(sumOfInt);
            System.out.println(message);
            sumOfInt = 0;
            state = 3;
            rememberString = message;
            countOfDuplicateStrings = 1;
        } else if (state == 3){
            if (rememberString.equals(message)){
                countOfDuplicateStrings++;
            } else {
                System.out.println(rememberString + " (x" + countOfDuplicateStrings + ")");
                rememberString = message;
                countOfDuplicateStrings = 1;
            }
        }

//        print(STRING_PREF + message);
    }

    public static void log(Object message) {
        print(REF_PREFIX + "@"+ message);
    }


    private static void print(String message) {
        System.out.print(message);
    }


    public static void close(){
        System.out.println(sumOfInt);
    }

}