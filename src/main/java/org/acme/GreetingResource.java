package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import java.util.ArrayList;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    @Inject
    Child1Resource child1Resource;
    @Inject
    ContainerRequestContext containerRequestContext;


    @PostConstruct
    void init(){
        System.out.println(Thread.currentThread().getName() + " init GreetingResource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        System.out.println(Thread.currentThread().getName() + ": greeting hello method");
        return Uni.createFrom().item(() -> "Hello from RESTEasy Reactive");
    }

    @Path("/{id1}")
    public Child1Resource childResource(@PathParam("id1") String id1) {
        System.out.println(Thread.currentThread().getName() + ": returning child1Resource");
        containerRequestContext.setProperty("id1", new Id1Bean(id1));
        return child1Resource;
    }
}
