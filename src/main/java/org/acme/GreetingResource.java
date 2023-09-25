package org.acme;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @PersistenceUnit(unitName = "vs")
    private EntityManagerFactory emf;

    @Inject
    ChildResource childResource;
    @Inject
    Child2Resource child2Resource;


    @PostConstruct
    void init(){
        System.out.println("init GreetingResource");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
//        JPAQuery<Object> detachedQuery = new JPAQuery<>();
//        try (ClosableEntityManager em = new ClosableEntityManager()) {
//            List<Object> fetch = detachedQuery.clone(em.em).fetch();
//        }
        return "Hello from RESTEasy Reactive";
    }

    @Path("/{id}")
    public ChildResource childResource(@PathParam("id") String id) {
        System.out.println("returning childResource");
        ChildResource res = childResource;
//        res.init(id);
        return res;
    }

//    private class ClosableEntityManager implements AutoCloseable {
//
//        private final EntityManager em = emf.createEntityManager();
//
//        @Override
//        public void close() {
//            if (em.isOpen()) {
//                em.close();
//            }
//        }
//    }
}
