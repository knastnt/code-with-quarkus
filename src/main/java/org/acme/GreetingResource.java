package org.acme;

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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import java.util.ArrayList;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    @Inject
    Child1Resource child1Resource;

    @Context
    UriInfo uriInfo;

    @Inject
    HttpServerRequest request;


    @PostConstruct
    void init(){
        System.out.println(Thread.currentThread().getName() + " init GreetingResource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println(Thread.currentThread().getName() + ": greeting hello method");
        return "Hello from RESTEasy Reactive";
    }

    @Path("/{id1}")
    public Child1Resource childResource(@PathParam("id1") String id1) {
        System.out.println(Thread.currentThread().getName() + ": returning child1Resource");
        request.params().set("id1", id1);
        return child1Resource;
    }
}
