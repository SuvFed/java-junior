package com.acme.edu;

/**
 * Created by Ivan on 03.10.2017.
 */
public class LoggerFacade {
    public static void log(int message) {
        Message mess = new IntMessage(message);
        new LoggerController(mess);
    }

    public static void close(){
        Message mess = new NullMessage();
        new LoggerController(mess);
    }

    public static void log(String message) {
        Message mess = new StringMessage(message);
        new LoggerController(mess);
    }

}
