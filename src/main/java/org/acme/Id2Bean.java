package org.acme;

import java.util.List;

public class Id2Bean {
    private String name;

    public Id2Bean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Id2Bean{" +
                "name='" + name + '\'' +
                '}';
    }
}
