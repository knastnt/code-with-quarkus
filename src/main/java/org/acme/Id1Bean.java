package org.acme;

import java.util.List;

public class Id1Bean {
    private String name;

    public Id1Bean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Id1Bean{" +
                "name='" + name + '\'' +
                '}';
    }
}
