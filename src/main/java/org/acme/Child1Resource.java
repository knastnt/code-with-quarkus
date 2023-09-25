package org.acme;

import io.vertx.core.http.HttpServerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
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
public class Child1Resource {
    @Inject
    Child2Resource child2Resource;
    @Inject
    HttpServerRequest request;

    //Нельзя использовать @Inject, т.к. приводит к StackOverflow
    private Id1Bean id1Bean;

    @PostConstruct
    void init(){
        System.out.println("init Child1Resource");
    }

    @jakarta.enterprise.inject.Produces
    Id1Bean idBean(){
        //Бин создаётся только тогда, когда его нужно куда-то заинджектить, поэтому внутри этого класса переменную
        // id1Bean использовать нельзя - она может быть не проинициализирована и равна null
        id1Bean = new Id1Bean(request.getParam("id1"));
        return id1Bean;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("Child1Resource hello method");
        return "Child1Resource Hello from RESTEasy Reactive: id1=" + id1Bean;
    }

    @Path("/{id2}")
    public Child2Resource childResource(@PathParam("id2") String id2) {
        System.out.println("returning child2Resource");
        request.params().set("id2", id2);
        return child2Resource;
    }
}
