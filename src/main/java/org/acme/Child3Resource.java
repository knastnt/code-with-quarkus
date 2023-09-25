package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
public class Child3Resource {
    @Inject
    Id1Bean id1Bean;
    @Inject
    Id2Bean id2Bean;
    private Id3Bean id3Bean;

    @Inject
    HttpServerRequest request;

    @PostConstruct
    void init(){
        System.out.println(Thread.currentThread().getName() + ": init Child3Resource");
    }

    @jakarta.enterprise.inject.Produces
    Id3Bean idBean(){
        id3Bean = new Id3Bean(request.getParam("id3"));
        return id3Bean;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        System.out.println(Thread.currentThread().getName() + ": Child3Resource hello method");
        return Uni.createFrom().item(() -> "Child3Resource Hello from RESTEasy Reactive: " +
                "\nid1=" + id1Bean +
                "\nid2=" + id2Bean +
                "\nid3=" + id3Bean);
    }
}
