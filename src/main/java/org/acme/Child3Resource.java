package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class Child3Resource {
    @Inject
    RequestContextHandler containerRequestContext;

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
