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
    private static byte sumOfByte = 0;


    //endregion
    //region don't using
    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    public static void log(char message) {
        print(CHAR_PREFIX +"\n" + message);
    }

    public static void log(Object message) {
        print(REF_PREFIX + "@"+ message);
    }
//



    public static void log(byte message) {
        if (state != 2){
            sumOfByte = message;
            state = 2;
        } else if (state == 2) {
            boolean moreThenMax = (int) sumOfByte + message > (int) Byte.MAX_VALUE;
            boolean lessThenMin = (int) sumOfByte + message < (int) Byte.MIN_VALUE;
            if (moreThenMax || lessThenMin){
                if (moreThenMax){
                    sumOfByte = (byte)(message - (Byte.MAX_VALUE - sumOfByte));
                    System.out.println(Byte.MAX_VALUE);
                } else if(lessThenMin) {
                    sumOfByte = (byte)(message - (Byte.MIN_VALUE - sumOfByte));
                    System.out.println(Byte.MIN_VALUE);
                }
            } else {
                sumOfByte += message;
            }
        }
    }

    public static void log(int message) {
        if (state != 1) {
            sumOfInt = message;
            state = 1;
        } else if (state == 1) {
            boolean moreThenMax = (long) sumOfInt + message > (long) Integer.MAX_VALUE;
            boolean lessThenMin = (long) sumOfInt + message < (long) Integer.MIN_VALUE;
            if (moreThenMax || lessThenMin) {
                if (moreThenMax) {
                    sumOfInt = message - (Integer.MAX_VALUE - sumOfInt);
                    System.out.println(Integer.MAX_VALUE);
                } else if (lessThenMin) {
                    System.out.println(Integer.MIN_VALUE);
                    sumOfInt = message - (Integer.MIN_VALUE - sumOfInt);
                }
            } else {
                sumOfInt += message;
            }
        }
    }


    public static void log(String message) {

        if (state == 0) {
            System.out.println(message);
            rememberString = message;
            countOfDuplicateStrings = 1;
            state = 3;
        } else if (state == 1) {
            System.out.println(sumOfInt);
            System.out.println(message);
            sumOfInt = 0;
            state = 3;
            rememberString = message;
            countOfDuplicateStrings = 1;
        } else if (state == 2){
            System.out.println(sumOfByte);
            System.out.println(message);
            sumOfByte = 0;
            state = 3;
            rememberString = message;
            countOfDuplicateStrings = 1;
        } else if (state == 3) {
                if (rememberString.equals(message)) {
                    countOfDuplicateStrings++;
                } else {
                    System.out.println(rememberString + " (x" + countOfDuplicateStrings + ")");
                    rememberString = message;
                    countOfDuplicateStrings = 1;
                }
            }

//        print(STRING_PREF + message);
        }



    private static void print(String message) {
        System.out.print(message);
    }


    public static void close(){
        if(state == 1) {
            System.out.println(sumOfInt);
        }
        else if(state == 2){
            System.out.println(sumOfByte);
        }
        else if(state == 3){
            if(countOfDuplicateStrings>1) {
                System.out.println(rememberString + " (x"+ countOfDuplicateStrings+")");
            }
            else {
                System.out.println(rememberString);
            }

        }
        sumOfInt = 0;
        state = 0;
        sumOfByte = 0;
        rememberString = null;
    }

}