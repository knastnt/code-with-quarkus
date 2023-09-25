package org.acme;

import io.vertx.core.http.HttpServerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@RequestScoped
public class Child2Resource {
    @Inject
    Child3Resource child3Resource;
    @Inject
    Id1Bean id1Bean;
    private Id2Bean id2Bean;

    @Inject
    HttpServerRequest request;



    @PostConstruct
    void init(){
        System.out.println("init Child2Resource");
    }

    @jakarta.enterprise.inject.Produces
    Id2Bean idBean(){
        id2Bean = new Id2Bean(request.getParam("id2"));
        return id2Bean;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("Child2Resource hello method");
        return "Child2Resource Hello from RESTEasy Reactive: id2=" + id2Bean;
    }

    @Path("/{id3}")
    public Child3Resource childResource(@PathParam("id3") String id3) {
        System.out.println("returning child3Resource");
        request.params().set("id3", id3);
        return child3Resource;
    }
}
