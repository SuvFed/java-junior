package com.acme.edu;

/**
 * Created by Ivan on 01.10.2017.
 */
public interface Message<T>{
    public void accamulate(T message);
    public String createMessage();
}
