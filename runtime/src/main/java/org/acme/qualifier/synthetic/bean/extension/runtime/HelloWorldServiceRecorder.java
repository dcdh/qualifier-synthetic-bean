package org.acme.qualifier.synthetic.bean.extension.runtime;

import io.quarkus.arc.SyntheticCreationalContext;
import io.quarkus.runtime.annotations.Recorder;

import java.util.function.Function;

@Recorder
public class HelloWorldServiceRecorder {

    public Function<SyntheticCreationalContext<HelloWorldService>, HelloWorldService> supplier(
            final String name) {
        return new Function<SyntheticCreationalContext<HelloWorldService>, HelloWorldService>() {
            @Override
            public HelloWorldService apply(final SyntheticCreationalContext<HelloWorldService> context) {
                return new HelloWorldService(name);
            }
        };
    }
}
