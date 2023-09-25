package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
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
public class Child2Resource {
    @Inject
    Child3Resource child3Resource;
    @Inject
    ContainerRequestContext containerRequestContext;



    @PostConstruct
    void init(){
        System.out.println(Thread.currentThread().getName() + ": init Child2Resource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        System.out.println(Thread.currentThread().getName() + ": Child2Resource hello method");
        return Uni.createFrom().item(() -> "Child2Resource Hello from RESTEasy Reactive: " +
                "\nid1=" + containerRequestContext.getProperty("id1") +
                "\nid2=" + containerRequestContext.getProperty("id2"));
    }

    @Path("/{id3}")
    public Uni<Child3Resource> childResource(@PathParam("id3") String id3) {
        System.out.println(Thread.currentThread().getName() + ": returning child3Resource");
        containerRequestContext.setProperty("id3", new Id1Bean(id3));
        return Uni.createFrom().item(() -> child3Resource);
    }
}
