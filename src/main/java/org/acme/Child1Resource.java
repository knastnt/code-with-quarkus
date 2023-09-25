package org.acme;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
public class Child1Resource {
    @Inject
    Child2Resource child2Resource;


    @PostConstruct
    void init(){
        System.out.println("init Child1Resource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("Child1Resource hello method");
        return "Child1Resource Hello from RESTEasy Reactive";
    }

    @Path("/{id2}")
    public Child2Resource childResource(@PathParam("id2") String id2) {
        System.out.println("returning child2Resource");
        return child2Resource;
    }
}
