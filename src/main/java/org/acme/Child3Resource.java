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
public class Child3Resource {


    @PostConstruct
    void init(){
        System.out.println("init Child3Resource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("Child3Resource hello method");
        return "Child3Resource Hello from RESTEasy Reactive";
    }
}
