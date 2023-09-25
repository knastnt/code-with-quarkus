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

//@Path("/hello/{id}")
@RequestScoped
public class ChildResource {
    @PathParam("id")
    private String id;

    @Inject
    Child2Resource child2Resource;

    @Context
    UriInfo info;

    @Inject
    HttpServerRequest request;

    private RequestScopedBean rsb;


    @PostConstruct
    void init(){
        System.out.println("Post construct ChildResource");
        rsb = new RequestScopedBean(id);
    }

//    @GET
//    public String hhh(@PathParam("id") String id){
//        return "patch: " + request.path() + ". result " + id + "\r\n\r\n" + requestScopedBean.number;
//    }

    @GET
    @jakarta.enterprise.inject.Produces
    public RequestScopedBean get(){
        System.out.println("get");
        return rsb;
    }

    @GET
    @Path("/t")
    public String hhhTest(){
        return "patch t: " + request.path() + ". result " + id + "\r\n\r\n" + rsb.number;
    }

    @GET
    @Path("/child")
    public Child2Resource child2(){
        System.out.println("returning child2Resource");
        return child2Resource;
    }
}
