package org.acme;

import jakarta.annotation.PostConstruct;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestScopedBean {
    private static final AtomicInteger counter = new AtomicInteger(0);

    int number = counter.incrementAndGet();
    String id;

    public RequestScopedBean(String id) {
        this.id = id;
    }

    @PostConstruct
    void init() {
        System.out.println("Created bean. Id: " + id + ". Counter: " + counter.get() + ". Thread: " + Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        return "RequestScopedBean{" +
                "number=" + number +
                ", id='" + id + '\'' +
                '}';
    }
}
