package com.acme.edu;

/**
 * Created by Ivan on 01.10.2017.
 */
public interface Message<T>{
    void accamulate(T message);
    String createMessage();
    State getState();
    T getMessage();
}
