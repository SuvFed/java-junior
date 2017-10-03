package com.acme.edu;

import java.io.Console;

/**
 * Created by Ivan on 29.09.2017.
 */
public class LoggerController {


    private static Message lastMessage;
    private static Message newMessage;


    public static void setLastMessage(Message lastMessage) {
        LoggerController.lastMessage = lastMessage;
    }

    public static void setNewMessage(Message newMessage) {
        LoggerController.newMessage = newMessage;
    }


    public LoggerController(Message newMessage) {
        LoggerController.newMessage = newMessage;
        if (LoggerController.lastMessage == null){
            LoggerController.lastMessage = newMessage;
        }
        this.changerState();
    }

    public void changerState(){
        if(LoggerController.newMessage.getState() == LoggerController.lastMessage.getState()) {
            newMessage.accamulate(newMessage.getMessage());
        }
        else {
            Sender consoleSender = new ConsoleSender();
            consoleSender.sendToNeedPlace(lastMessage.createMessage());
            lastMessage = newMessage;
            newMessage.accamulate(newMessage.getMessage());
            IntMessage.setMessage(0);
            IntMessage.setOverloadNegativeCounter(0);
            IntMessage.setOverloadPositeveCounter(0);
            IntMessage.setSb(IntMessage.getSb().delete(0, IntMessage.getSb().length()));
            IntMessage.setSumm(0);
            StringMessage.setSb(IntMessage.getSb().delete(0, IntMessage.getSb().length()));
            StringMessage.setCountReiteration(0);
            StringMessage.setMessage(null);
            StringMessage.setOldMessage(null);
        }
    }
}
