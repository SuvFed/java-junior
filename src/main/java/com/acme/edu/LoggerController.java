package com.acme.edu;

/**
 * Created by Ivan on 29.09.2017.
 */
public class LoggerController {
    private Accumulator currentAccumulator;

    private final Accumulator byteAccumulator;
    private final Accumulator stringAccumulator;
    private final Accumulator intAccumulator;

    public LoggerController(Accumulator byteAccumulator, Accumulator stringAccumulator, Accumulator intAccumulator) {
        this.byteAccumulator = byteAccumulator;
        this.stringAccumulator = stringAccumulator;
        this.intAccumulator = intAccumulator;
    }
}
