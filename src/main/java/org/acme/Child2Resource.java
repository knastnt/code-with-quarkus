package org.acme;

import io.vertx.core.http.HttpServerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestPath;

@Path("/")
@RequestScoped
public class Child2Resource {
    @Inject
    RequestScopedBean rsb;


    @PostConstruct
    void init(){
        System.out.println("Post construct Child2Resource");
    }

    @GET
    public RequestScopedBean child(){
        System.out.println("child2 method");
        return rsb;
    }    @GET

    @Path("/t")
    public String hhhTest(){
        return "Child2Resource patch t";
    }
}
