package com.acme.edu;

/**
 * Created by Ivan on 02.10.2017.
 */
public class StringMessage implements Message<String>{

    private static final State state = State.STRING;
    private static StringBuilder sb = new StringBuilder("");
    private static String message = "";
    private static String oldMessage = "";
    private static int countReiteration = 0;

    public static StringBuilder getSb() {
        return sb;
    }

    public static void setSb(StringBuilder sb) {
        StringMessage.sb = sb;
    }

    public static void setMessage(String message) {
        StringMessage.message = message;
    }

    public static void setOldMessage(String oldMessage) {
        StringMessage.oldMessage = oldMessage;
    }

    public static void setCountReiteration(int countReiteration) {
        StringMessage.countReiteration = countReiteration;
    }

    public StringMessage(String message) {
        StringMessage.message = message;
        if(oldMessage == null){
            oldMessage = message;
        }
    }

    @Override
    public void accamulate(String message) {
        if(oldMessage.equals(message)) {
            countReiteration++;
        }
        else {
            createMessage();
            oldMessage = message;
            countReiteration = 1;
        }
    }

    @Override
    public String createMessage() {
        if(countReiteration == 1) {
            sb.append(oldMessage+"\n");
        }
        else if(countReiteration>1){
            sb.append(oldMessage + " (x"+countReiteration+")\n");
        }
        return sb.toString();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
