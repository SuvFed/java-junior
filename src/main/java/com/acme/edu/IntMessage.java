package com.acme.edu;

/**
 * Created by Ivan on 01.10.2017.
 */
public class IntMessage implements Message<Integer> {

    private static int summ = 0;
    private static int overloadPositeveCounter = 0;
    private static int overloadNegativeCounter = 0;
    private static final State state = State.INT;
    private static StringBuilder sb = new StringBuilder("");
    private static Integer message = 0;
    
    
    public IntMessage(Integer message) {
        IntMessage.message = message;
    }

    public static StringBuilder getSb() {
        return sb;
    }

    public static void setMessage(int message) {
        IntMessage.message = message;
    }

    public static void setOverloadNegativeCounter(int overloadNegativeCounter) {
        IntMessage.overloadNegativeCounter = overloadNegativeCounter;
    }

    public static void setOverloadPositeveCounter(int overloadPositeveCounter) {
        IntMessage.overloadPositeveCounter = overloadPositeveCounter;
    }

    public static void setSb(StringBuilder sb) {
        IntMessage.sb = sb;
    }

    public static void setSumm(int summ) {
        IntMessage.summ = summ;
    }

    public Integer getMessage() {
        return message;
    }


    public State getState() {
        return state;
    }

    @Override
    public void accamulate(Integer message) {
        if((long)message+summ>Integer.MAX_VALUE){
            overloadPositeveCounter++;
            summ = message - (Integer.MAX_VALUE - summ);
        }
        else if ((long)message+summ<Integer.MIN_VALUE){
            overloadNegativeCounter++;
            summ = (message - (Integer.MIN_VALUE - summ));
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
            return writeMessage(difference, Integer.MAX_VALUE);
        }
        else {
            return writeMessage(difference, Integer.MIN_VALUE);
        }


    }


}
