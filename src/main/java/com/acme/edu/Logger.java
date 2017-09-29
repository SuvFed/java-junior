package com.acme.edu;

public class Logger {


    //region const
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String REF_PREFIX = "reference: ";
    //endregion

    //region fields of statement
    /**
     * 1-int
     * 2-byte
     * 3-string
     */
    private static State state = State.DEFAULT;//
    private static int countOfDuplicateStrings = 0;
    private static String stringDeposite;
    private static byte sumOfByte = 0;
    private static int sumOfInt = 0;
    enum State{STRING, BYTE, INT, DEFAULT}
    //endregion

    //region don't using in itaretion2
    public static void log(Object message) {
        print(REF_PREFIX + "@"+ message);
    }

    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    public static void log(char message) {
        print(CHAR_PREFIX + "\n" + message);
    }

    public static void log(int ... message){
        StringBuilder sb = new StringBuilder("primitives array: {");
        for (int i = 0; i < message.length-1; i++) {
            sb.append(message[i] + ", ");
        }
        sb.append(message[message.length-1] + "}");
        System.out.print(sb.toString() + "\n");
    }
    //endregion

    //region iteration2
    public static void log(byte message) {
        setState(message);
    }

    public static void log(int message) {
        if ((state == State.STRING) && (countOfDuplicateStrings > 1)) {
            System.out.println(" (x" + countOfDuplicateStrings + ")");
        }
        setState(message);
    }

    public static void log(String message) {
        switch (state){
            case DEFAULT:

                System.out.println(message);
                setState(message);
                break;
            case INT:
                System.out.println(sumOfInt);
                System.out.println(message);
                setState(message);
                break;
            case BYTE:
                System.out.println(sumOfByte);
                System.out.println(message);
                setState(message);
                break;
            case STRING:
                setState(message);
                if (countOfDuplicateStrings <= 1){
                    System.out.print(message);
                }
                break;
        }
    }
    //endregion

    //region statesChange
    private static void setState(String message) {
        state = State.STRING;
        if ((stringDeposite != null)&&(stringDeposite.equals(message))) {
            countOfDuplicateStrings++;
        } else {
            countOfDuplicateStrings = 1;
        }
        stringDeposite = message;
        sumOfByte = 0;
        sumOfInt = 0;
    }

    private static void setState(int message) {
        boolean moreThenMax = (long) sumOfInt + message > (long) Integer.MAX_VALUE;
        boolean lessThenMin = (long) sumOfInt + message < (long) Integer.MIN_VALUE;
        if (state != State.INT){
            sumOfInt = message;
        } else {
            if (moreThenMax) {
                setSumOfMessage(Integer.MAX_VALUE, message);
            } else if (lessThenMin) {
                setSumOfMessage(Integer.MAX_VALUE, message);
            } else {
                sumOfInt += message;
            }
        }
        countOfDuplicateStrings = 0;
        stringDeposite = null;
        sumOfByte = 0;
        state = State.INT;
    }

    private static void setSumOfMessage (byte maxOrMin, byte message) {
        sumOfByte = (byte) (message - (maxOrMin - sumOfByte));
        System.out.println(maxOrMin);
    }

    private static void setSumOfMessage (int maxOrMin, int message) {
        sumOfInt = (int) (message - (maxOrMin - sumOfInt));
        System.out.println(maxOrMin);
    }

    private static void setState(byte message) {
        boolean moreThenMax = (int) sumOfByte + message > (int) Byte.MAX_VALUE;
        boolean lessThenMin = (int) sumOfByte + message < (int) Byte.MIN_VALUE;
        if (state != State.BYTE){
            sumOfByte = message;
        } else {
            if (moreThenMax) {
                setSumOfMessage(Byte.MAX_VALUE, message);
            } else if (lessThenMin) {
                setSumOfMessage(Byte.MIN_VALUE, message);
            } else {
                sumOfByte += message;
            }
        }
        countOfDuplicateStrings = 0;
        stringDeposite = null;
        sumOfInt = 0;
        state = State.BYTE;
    }

    private static void resetState(){
        state = State.DEFAULT;
        stringDeposite = null;
        sumOfByte = 0;
        sumOfInt = 0;
        countOfDuplicateStrings = 0;
    }
    //endregion



    private static void print(String message) {
        System.out.print(message);
    }

    public static void close(){
        if ((state == State.STRING) && (countOfDuplicateStrings > 1)) {
            System.out.println(" (x" + countOfDuplicateStrings + ")");
        }
        System.out.println(sumOfInt);
        resetState();
    }

}