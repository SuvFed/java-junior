package com.acme.edu;

/**
 * Created by Ivan on 02.10.2017.
 */
public class ConsoleSender implements Sender{
    public void sendToNeedPlace(String str){
        System.out.println(str);
    }
}
