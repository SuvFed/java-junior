package com.acme.edu;

/**
 * Created by Ivan on 03.10.2017.
 */
public class LoggerFacade {


    public static void log(int message) {
        Message mess = new IntMessage(message);
        LoggerController.setNewMessage(mess);
        LoggerController.changerState();
    }

    public static void close(){
        Message mess = new NullMessage();
        LoggerController.setNewMessage(mess);
        LoggerController.changerState();
    }

    public static void log(String message) {
        Message mess = new StringMessage(message);
        LoggerController.setNewMessage(mess);
        LoggerController.changerState();
    }

    public static void log(byte message) {
        Message mess = new ByteMessage((byte)message);
        LoggerController.setNewMessage(mess);
        LoggerController.changerState();
    }

}
