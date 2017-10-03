package com.acme.edu;

/**
 * Created by Ivan on 03.10.2017.
 */
public class NullMessage implements Message {
    private State state = State.DEFAULT;

    @Override
    public void accamulate(Object message) {

    }

    @Override
    public String createMessage() {
        return null;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Object getMessage() {
        return null;
    }

}
