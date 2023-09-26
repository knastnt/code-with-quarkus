package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
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
public class Child2Resource {
    @Inject
    Child3Resource child3Resource;
    @Inject
    RequestContextHandler containerRequestContext;



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
