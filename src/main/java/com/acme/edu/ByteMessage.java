package com.acme.edu;

/**
 * Created by Ivan on 02.10.2017.
 */
public class ByteMessage implements Message<Byte> {
    private static byte summ = 0;
    private static int overloadPositeveCounter = 0;
    private static int overloadNegativeCounter = 0;
    private static final State state = State.BYTE;
    private static StringBuilder sb = new StringBuilder("");
    private static Byte message = 0;


    public ByteMessage(Byte message) {
        ByteMessage.message = (byte)message;
    }

    public static StringBuilder getSb() {
        return sb;
    }

    public static void setMessage(byte message) {
        ByteMessage.message = message;
    }

    public static void setOverloadNegativeCounter(int overloadNegativeCounter) {
        ByteMessage.overloadNegativeCounter = overloadNegativeCounter;
    }

    public static void setOverloadPositeveCounter(int overloadPositeveCounter) {
        ByteMessage.overloadPositeveCounter = overloadPositeveCounter;
    }

    public static void setSb(StringBuilder sb) {
        ByteMessage.sb = sb;
    }

    public static void setSumm(byte summ) {
        ByteMessage.summ = summ;
    }

    public Byte getMessage() {
        return message;
    }


    public State getState() {
        return state;
    }

    @Override
    public void accamulate(Byte message) {
        if((int)message+summ>Byte.MAX_VALUE){
            overloadPositeveCounter++;
            summ = (byte)(message - (Byte.MAX_VALUE - summ));
        }
        else if ((int)message+summ<Byte.MIN_VALUE){
            overloadNegativeCounter++;
            summ = (byte)(message - (Byte.MIN_VALUE - summ));
        }
        else {
            summ += message;
        }
    }


    /**
     * differenceParametres[0]:
     * true -> positive > negative
     * false -> negative > positive
     * @return
     */
    private boolean isPositive(){
        boolean maxOrMin;
        if (overloadNegativeCounter>overloadPositeveCounter) {
            maxOrMin = false;
        }
        else {
            maxOrMin = true;
        }
        return maxOrMin;
    }

    private String writeMessage(int difference, int maxOrMin){
        sb.append(summ + "\n");
        for (int i = 0; i < difference-1; i++) {
            sb.append(maxOrMin + "\n");
        }
        if(difference==1) {
            sb.append(maxOrMin + "\n");
        }
        return sb.toString();
    }

    @Override
    public String createMessage() {
        int difference = Math.abs(overloadNegativeCounter-overloadPositeveCounter);
        if(isPositive()) {
            return writeMessage(difference, Byte.MAX_VALUE);
        }
        else {
            return writeMessage(difference, Byte.MIN_VALUE);
        }


    }



}
