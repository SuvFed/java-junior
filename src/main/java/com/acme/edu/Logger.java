package com.acme.edu;

public class Logger {


    //region const
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String REF_PREFIX = "reference: ";
    private static final String STRING_PREF = "string: ";
    //endregion

    //region fields of statement
    /**
     * Fields of statement
     */
    private static int state = 0;//1-int 2-byte 3-string
    private static int countOfDuplicateStrings = 0;
    private static String rememberString;
    private static byte sumOfByte = 0;
    private static int sumOfInt = 0;
    //endregion

    public static void log(byte message) {
        setState(message);
    }

    public static void log(int message) {
        if ((state == 3) && (countOfDuplicateStrings > 1)) {
            System.out.println(" (x" + countOfDuplicateStrings + ")");
        }
        setState(message);
    }

    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    public static void log(char message) {
        print(CHAR_PREFIX + "\n" + message);
    }

    private static void setState(String message) {
        state = 3;
        if ((rememberString != null)&&(rememberString.equals(message))) {
            countOfDuplicateStrings++;
        } else {
            countOfDuplicateStrings = 1;
        }
        rememberString = message;
        sumOfByte = 0;
        sumOfInt = 0;
    }

    private static void setState(int message) {
        boolean moreThenMax = (long) sumOfInt + message > (long) Integer.MAX_VALUE;
        boolean lessThenMin = (long) sumOfInt + message < (long) Integer.MIN_VALUE;
        if (state != 1){
            sumOfInt = message;
        } else {
            if (moreThenMax) {
                sumOfInt = message - (Integer.MAX_VALUE - sumOfInt);
                System.out.println(Integer.MAX_VALUE);
            } else if (lessThenMin) {
                System.out.println(Integer.MIN_VALUE);
                sumOfInt = message - (Integer.MIN_VALUE - sumOfInt);
            } else {
                sumOfInt += message;
            }
        }
        countOfDuplicateStrings = 0;
        rememberString = null;
        sumOfByte = 0;
        state = 1;
    }

    private static void setState(byte message) {
        boolean moreThenMax = (int) sumOfByte + message > (int) Byte.MAX_VALUE;
        boolean lessThenMin = (int) sumOfByte + message < (int) Byte.MIN_VALUE;
        if (state != 2){
            sumOfByte = message;
        } else {
            if (moreThenMax) {
                sumOfByte = (byte) (message - (Byte.MAX_VALUE - sumOfByte));
                System.out.println(Byte.MAX_VALUE);
            } else if (lessThenMin) {
                sumOfByte = (byte) (message - (Byte.MIN_VALUE - sumOfByte));
                System.out.println(Byte.MIN_VALUE);
            } else {
                sumOfByte += message;
            }
        }
        countOfDuplicateStrings = 0;
        rememberString = null;
        sumOfInt = 0;
        state = 2;
    }

    private static void setState(){
        state = 0;
        rememberString = null;
        sumOfByte = 0;
        sumOfInt = 0;
        countOfDuplicateStrings = 0;
    }

    public static void log(String message) {
        switch (state){
            case 0:
                System.out.println(message);
                setState(message);
                break;
            case 1:
                System.out.println(sumOfInt);
                System.out.println(message);
                setState(message);
                break;
            case 2:
                System.out.println(sumOfByte);
                System.out.println(message);
                setState(message);
                break;
            case 3:
                setState(message);
                if (countOfDuplicateStrings <= 1){
                    System.out.print(message);
                }
                break;
        }
    }

    public static void log(Object message) {
        print(REF_PREFIX + "@"+ message);
    }


    private static void print(String message) {
        System.out.print(message);
    }


    public static void close(){
        if ((state == 3) && (countOfDuplicateStrings > 1)) {
            System.out.println(" (x" + countOfDuplicateStrings + ")");
        }
        System.out.println(sumOfInt);
        setState();
    }

}