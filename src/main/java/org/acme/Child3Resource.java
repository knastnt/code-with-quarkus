package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class Child3Resource {
    @Inject
    ContainerRequestContext containerRequestContext;

    @PostConstruct
    void init(){
        System.out.println(Thread.currentThread().getName() + ": init Child3Resource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        System.out.println(Thread.currentThread().getName() + ": Child3Resource hello method");
        return Uni.createFrom().item(() -> "Child3Resource Hello from RESTEasy Reactive: " +
                "\nid1=" + containerRequestContext.getProperty("id1") +
                "\nid2=" + containerRequestContext.getProperty("id2") +
                "\nid3=" + containerRequestContext.getProperty("id3"));
    }
}
