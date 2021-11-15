package com.andyadc.samples.exception;

public class SamplesException extends RuntimeException {

    public SamplesException() {
    }

    public SamplesException(String message) {
        super(message);
    }

    public SamplesException(String message, Throwable cause) {
        super(message, cause);
    }

    public SamplesException(Throwable cause) {
        super(cause);
    }
}
