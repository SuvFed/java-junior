package com.acme.edu;

public class Logger {


    //region const
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String REF_PREFIX = "reference: ";
    private static final String STRING_PREF = "string: ";
    private static int state = 0; //int - 1, byte - 2, String - 3, 0 - резерв;
    private static int sumOfInt = 0;
    private static int countOverLoad = 0;
    //private static String result;

    //endregion
//region yes



    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    public static void log(char message) {
        print(CHAR_PREFIX +"\n" + message);
    }

    public static void log(Object message) {
        print(REF_PREFIX + "@"+ message);
    }


    private static void print(String message) {
        System.out.print(message);
    }
//endregion

    public static void log(byte message) {
        print(PRIMITIVE_PREFIX + "\n" + message);
    }
    public static void log(int message) {
        if(state!=1){
            sumOfInt = 0;
            state = 1;
        }
        if((long)sumOfInt+message>(long)Integer.MAX_VALUE) {

        }else {
            sumOfInt += message;
        }
        print(message + "\n");

    }
    public static void log(String message) {
        print(STRING_PREF + message);
    }

    public static void close(){
        System.out.println();
    }
}

class Main{
    public static void main(String[] args) {
        byte mes = 13;
        byte sum = 123;
        byte a = (byte) ((mes) - (byte)(Byte.MAX_VALUE - sum));
        System.out.println(a);
    }
}