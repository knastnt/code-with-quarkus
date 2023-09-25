package org.acme;

import java.util.List;

public class Id3Bean {

    private String name;

    public Id3Bean(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Id3Bean{" +
                "name='" + name + '\'' +
                '}';
    }
}
