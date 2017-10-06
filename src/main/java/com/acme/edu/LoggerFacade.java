package com.acme.edu;

/**
 * Created by Ivan on 03.10.2017.
 */
public class LoggerFacade {


    public static void log(int message) {
        try {
            if (message == 0) throw new IllegalArgumentException("0 is invalid value");
            Message mess = new IntMessage(message);
            LoggerController.setNewMessage(mess);
            LoggerController.changerState();
        } catch (IllegalArgumentException e){
            close();
        }
    }

    public static void close(){
        Message mess = new NullMessage();
        LoggerController.setNewMessage(mess);
        LoggerController.changerState();
    }

    public static void log(String message) {
        try{
            if (message == null) throw new IllegalArgumentException("Null is the invalid value");
            Message mess = new StringMessage(message);
            LoggerController.setNewMessage(mess);
            LoggerController.changerState();
        } catch (IllegalArgumentException e){
            close();
        }

    }

    public static void log(byte message) {
        Message mess = new ByteMessage((byte)message);
        LoggerController.setNewMessage(mess);
        LoggerController.changerState();
    }

}
