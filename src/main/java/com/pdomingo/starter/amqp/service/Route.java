package com.pdomingo.starter.amqp.service;

public record Route(String exchange, String key) {
    public static Route from(String exchange, String key) {
        return new Route(exchange, key);
    }
}
