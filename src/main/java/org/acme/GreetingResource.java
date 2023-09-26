package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.util.ArrayList;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    @Inject
    Child1Resource child1Resource;
    @Inject
    RequestContextHandler containerRequestContext;


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
