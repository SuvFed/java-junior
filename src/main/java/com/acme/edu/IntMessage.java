package com.acme.edu;

/**
 * Created by Ivan on 01.10.2017.
 */
public class IntMessage implements Message<Integer> {
    private int summ = 0;
    private int overloadPositeveCounter = 0;
    private int overloadNegativeCounter = 0;
    private StringBuilder sb = new StringBuilder();

    @Override
    public void accamulate(Integer message) {
        boolean moreThenMax = (long) (summ + message) > (long) Integer.MAX_VALUE;
        boolean lessThenMin = (long) (summ + message) < (long) Integer.MIN_VALUE;
        if(moreThenMax) {
            overloadPositeveCounter++;
            summ = (message - (Integer.MAX_VALUE - summ));
        }else if(lessThenMin) {
            overloadNegativeCounter++;
            summ = (message - (Integer.MIN_VALUE - summ));
        }
    }


    /**
     * differenceParametres[0]:
     * 1 -> positive > negative
     * 0 -> negative > positive
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
        for (int i = 0; i < difference; i++) {
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
