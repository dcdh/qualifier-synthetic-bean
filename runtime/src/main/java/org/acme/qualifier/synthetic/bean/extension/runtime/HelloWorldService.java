package org.acme.qualifier.synthetic.bean.extension.runtime;

import java.util.Objects;

public final class HelloWorldService {
    private final String name;

    public HelloWorldService(final String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String sayHello() {
        return "Hello " + name;
    }
}
