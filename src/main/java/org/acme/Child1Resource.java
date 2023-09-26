package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.util.List;

@ApplicationScoped
public class Child1Resource {
    @Inject
    Child2Resource child2Resource;
    @Inject
    RequestContextHandler containerRequestContext;


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
