package org.acme;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    @Inject
    Child1Resource child1Resource;


    @PostConstruct
    void init(){
        System.out.println("init GreetingResource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("greeting hello method");
        return "Hello from RESTEasy Reactive";
    }

    @Path("/{id1}")
    public Child1Resource childResource(@PathParam("id1") String id1) {
        System.out.println("returning child1Resource");
        return child1Resource;
    }
}
