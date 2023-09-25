package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@RequestScoped
public class Child1Resource {
    @Inject
    Child2Resource child2Resource;
    @Inject
    ContainerRequestContext containerRequestContext;


    @PostConstruct
    void init(){
        System.out.println(Thread.currentThread().getName() + ": init Child1Resource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        System.out.println(Thread.currentThread().getName() + ": Child1Resource hello method");
        return Uni.createFrom().item(() -> "Child1Resource Hello from RESTEasy Reactive: " +
                "\nid1=" + containerRequestContext.getProperty("id1"));
    }

    @Path("/{id2}")
    public Child2Resource childResource(@PathParam("id2") String id2) {
        System.out.println(Thread.currentThread().getName() + ": returning child2Resource");
        containerRequestContext.setProperty("id2", new Id1Bean(id2));
        return child2Resource;
    }
}
