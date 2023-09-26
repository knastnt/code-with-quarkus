package org.acme;

import javax.enterprise.context.RequestScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequestScoped
public class RequestContextHandler {
    private final Map<String, Object> params = new ConcurrentHashMap<>();

    public void setProperty(String key, Object value) {
        params.put(key, value);
    }

    public Object getProperty(String key) {
        return params.get(key);
    }
}
