package org.acme;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;

import java.net.URI;

@ApplicationScoped
public class JmsBroker {
    Logger LOG = LoggerFactory.getLogger(JmsBroker.class);

    BrokerService broker;

    public void start(@Observes StartupEvent startupEvent) throws Exception {
        //startBroker();
        startBroker2();
    }

    public void startBroker() throws Exception {
        LOG.info("Starting broker...");
        broker = new BrokerService();
        broker.addConnector("mqtt://localhost:1883");
        broker.setDataDirectory("target");
        broker.start();
        LOG.info("MQTT broker started");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    LOG.info("Shutting down MQTT broker...");
                    broker.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startBroker2() throws Exception {
        LOG.info("Starting broker...");
        //BrokerFactory.createBroker(new URI("broker://()/localhost:5673?persistent=false"), true);

//        String brokerHost = "localhost";
//        String brokerPort = "5673";
//        BrokerService messageBroker = BrokerFactory.createBroker(String.format("broker:tcp://%s:%s", brokerHost, brokerPort));
        BrokerService messageBroker = new BrokerService();
        messageBroker.setPersistent(false);
        messageBroker.setUseJmx(false);
        TransportConnector connector = new TransportConnector();
        connector.setName("amqp");
        connector.setUri(new URI("amqp://0.0.0.0:5673"));

        messageBroker.addConnector(connector);

        messageBroker.start();
    }
}
