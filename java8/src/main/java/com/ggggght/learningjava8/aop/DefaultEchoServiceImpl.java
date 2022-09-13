package com.ggggght.learningjava8.aop;

public class DefaultEchoServiceImpl implements EchoService {
    @Override public String echo(String message) {
        return "[ECHO] %s.".formatted(message);
    }
}
