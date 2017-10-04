package com.acme.edu;

//import java.io.Console;

/**
 * Created by Ivan on 29.09.2017.
 */
class LoggerController {


    private static Message lastMessage;
    private static Message newMessage;


    static void setNewMessage(Message newMessage) {
        LoggerController.newMessage = newMessage;
        if (LoggerController.lastMessage == null){
            LoggerController.lastMessage = newMessage;
        }
    }

    static void changerState(){
        if(LoggerController.newMessage.getState() == LoggerController.lastMessage.getState()) {
            newMessage.accamulate(newMessage.getMessage());
        }
        else {
            Sender consoleSender = new ConsoleSender();
            String string = lastMessage.createMessage();
            if(string!=null){
                consoleSender.sendToNeedPlace(string);
            }
            switch (newMessage.getState()){
                case DEFAULT:
                    IntMessage.setSb(new StringBuilder());
                    IntMessage.setSumm(0);
                    ByteMessage.setSb(new StringBuilder());
                    ByteMessage.setSumm((byte)0);
                    StringMessage.setSb(new StringBuilder());
                case INT:
                    setMessageFieldsState();
                case BYTE:
                    setMessageFieldsState();
                case STRING:
                    IntMessage.setSb(IntMessage.getSb().delete(0, IntMessage.getSb().length()));
                    IntMessage.getSb().append("");
                    IntMessage.setSumm(0);
                    IntMessage.setOverloadPositeveCounter(0);
                    IntMessage.setOverloadNegativeCounter(0);
                    ByteMessage.setSb(ByteMessage.getSb().delete(0, ByteMessage.getSb().length()));
                    ByteMessage.getSb().append("");
                    ByteMessage.setSumm((byte)0);
                    ByteMessage.setOverloadPositeveCounter(0);
                    ByteMessage.setOverloadNegativeCounter(0);
            }

            newMessage.accamulate(newMessage.getMessage());

            lastMessage = newMessage;
            /*IntMessage.setMessage(0);
            IntMessage.setOverloadNegativeCounter(0);
            IntMessage.setOverloadPositeveCounter(0);
            IntMessage.setSb(IntMessage.getSb().delete(0, IntMessage.getSb().length()));
            IntMessage.setSumm(0);
            StringMessage.setSb(IntMessage.getSb().delete(0, IntMessage.getSb().length()));
            StringMessage.setCountReiteration(0);
            StringMessage.setMessage(null);
            StringMessage.setOldMessage(null);*/
        }
    }

    private static void setMessageFieldsState() {
        StringMessage.setSb(StringMessage.getSb().delete(0, StringMessage.getSb().length()));
        StringMessage.getSb().append("");
        StringMessage.setCountReiteration(0);
        StringMessage.setMessage("");
        StringMessage.setOldMessage("");
    }
}
